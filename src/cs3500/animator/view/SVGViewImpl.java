package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ROAnimationModelImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.Transformation;
import java.io.IOException;

/**
 * Represents a view that shows the information of the animation in an SVG format.
 */
public class SVGViewImpl implements AnimationView {
  private ROAnimationModelImpl model;
  Appendable output;
  private int frameRate;

  /**
   * Constructs a SVG view.
   * @param model the model to produce as an .svg
   * @param speed the framerate at which to run the svg
   * @param output the location to output the file.
   */
  public SVGViewImpl(AnimationModel model, int speed, Appendable output) {
    this.model = new ROAnimationModelImpl(model);
    this.output = output;
    if (speed < 1) {
      throw new IllegalArgumentException("Can't have a speed that is less than 1fps!");
    }
    this.frameRate = speed;
  }

  /**
   * Describes a Shape in SVG format.
   * @param s - the Shape to be described in SVG format
   * @return a String representing a Shape in SVG format
   */
  private String svgShape(Shape s) {
    StringBuilder svgShape = new StringBuilder();
    String xval = "";
    String yval = "";
    String wval = "";
    String hval = "";
    if (s.getType().equals(ShapeType.RECTANGLE)) {
      xval = "x";
      yval = "y";
      wval = "width";
      hval = "height";
    } else if (s.getType().equals(ShapeType.ELLIPSE)) {
      xval = "cx";
      yval = "cy";
      wval = "rx";
      hval = "ry";
    }

    svgShape.append("<").append(s.getType().toString().toLowerCase()).append(" id=\"")
            .append(s.getName()).append("\" ").append(xval).append("=\"")
            .append(s.getProperties().getPosx()).append("\" ").append(yval).append("=\"")
            .append(s.getProperties().getPosy()).append("\" ").append(wval).append("=\"")
            .append(s.getProperties().getWidth()).append("\" ").append(hval).append("=\"")
            .append(s.getProperties().getHeight()).append("\" fill=\"rgb(")
            .append(s.getProperties().getColorR()).append(",")
            .append(s.getProperties().getColorG()).append(",")
            .append(s.getProperties().getColorB()).append(")\" ")
            .append("visibility=\"visible\" >");

    for (Transformation t : s.getTransformations()) {
      svgShape.append(svgTransformation(t, s.getType()));
    }
    svgShape.append("\n</").append(s.getType().toString().toLowerCase()).append(">");

    return svgShape.toString();
  }

  /**
   * Describes a Transformation in SVG format with an <animate></animate> tag.
   * @param t - The Transformation to be described
   * @param type - The type of Shape the Transformation is changing
   * @return A String representing the Transformation in SVG format with all changed properties
   *         listed in separate tags
   */
  private String svgTransformation(Transformation t, ShapeType type) {
    StringBuilder svgTrans = new StringBuilder();
    String attributeName = "";
    if (t.getChangesX()) {
      svgTrans.append("\n\t<animate attributeType=\"xml\" begin=\"")
              .append((double)(t.getStartT() / this.frameRate))
              .append("\" dur=\"")
              .append((t.getEndT() / (double)this.frameRate)
                      - (t.getStartT() / (double)this.frameRate))
              .append("s\" ");
      if (type.equals(ShapeType.RECTANGLE)) {
        attributeName = "x";
      }
      else if (type.equals(ShapeType.ELLIPSE)) {
        attributeName = "cx";
      }
      svgTrans.append("attributeName=\"").append(attributeName).append("\" from=\"")
              .append(t.getStartState().getPosx()).append("\" to=\"")
              .append(t.getEndState().getPosx()).append("\" ").append("fill=\"freeze\" />");
    }
    if (t.getChangesY()) {
      svgTrans.append("\n\t<animate attributeType=\"xml\" begin=\"")
              .append((double)(t.getStartT() / this.frameRate))
              .append("\" dur=\"")
              .append((t.getEndT() / (double)this.frameRate)
                      - (t.getStartT() / (double)this.frameRate))
              .append("s\" ");
      if (type.equals(ShapeType.RECTANGLE)) {
        attributeName = "y";
      }
      else if (type.equals(ShapeType.ELLIPSE)) {
        attributeName = "cy";
      }
      svgTrans.append("attributeName=\"").append(attributeName).append("\" from=\"")
              .append(t.getStartState().getPosy()).append("\" to=\"")
              .append(t.getEndState().getPosy()).append("\" ").append("fill=\"freeze\" />");
    }
    if (t.getChangesW()) {
      svgTrans.append("\n\t<animate attributeType=\"xml\" begin=\"")
              .append((double)(t.getStartT() / this.frameRate))
              .append("\" dur=\"")
              .append((t.getEndT() / (double)this.frameRate)
                      - (t.getStartT() / (double)this.frameRate))
              .append("s\" ");
      if (type.equals(ShapeType.RECTANGLE)) {
        attributeName = "width";
      }
      else if (type.equals(ShapeType.ELLIPSE)) {
        attributeName = "rx";
      }
      svgTrans.append("attributeName=\"").append(attributeName).append("\" from=\"")
              .append(t.getStartState().getWidth()).append("\" to=\"")
              .append(t.getEndState().getWidth()).append("\" ").append("fill=\"freeze\" />");
    }
    if (t.getChangesH()) {
      svgTrans.append("\n\t<animate attributeType=\"xml\" begin=\"")
              .append((double)(t.getStartT() / this.frameRate))
              .append("\" dur=\"")
              .append((t.getEndT() / (double)this.frameRate)
                      - (t.getStartT() / (double)this.frameRate))
              .append("s\" ");
      if (type.equals(ShapeType.RECTANGLE)) {
        attributeName = "height";
      }
      else if (type.equals(ShapeType.ELLIPSE)) {
        attributeName = "ry";
      }
      svgTrans.append("attributeName=\"").append(attributeName).append("\" from=\"")
              .append(t.getStartState().getHeight()).append("\" to=\"")
              .append(t.getEndState().getHeight()).append("\" ").append("fill=\"freeze\" />");
    }
    if (t.getChangesR() || t.getChangesG() || t.getChangesB()) {
      svgTrans.append("\n\t<animate attributeType=\"xml\" begin=\"")
              .append((double)(t.getStartT() / this.frameRate))
              .append("\" dur=\"")
              .append((t.getEndT() / (double)this.frameRate)
                      - (t.getStartT() / (double)this.frameRate))
              .append("s\" ").append("attributeName=\"fill\" from=\"rgb(")
              .append(t.getStartState().getColorR()).append(",")
              .append(t.getStartState().getColorG()).append(",")
              .append(t.getStartState().getColorB()).append(")\" ").append(" to=\"rgb(")
              .append(t.getEndState().getColorR()).append(",").append(t.getEndState().getColorG())
              .append(",").append(t.getEndState().getColorB()).append(")\"")
              .append(" fill=\"freeze\" ").append("/>");
    }
    return svgTrans.toString();
  }

  @Override
  public void display() {
    try {
      output.append("<svg width=" + "\"").append(String.valueOf(model.getCanvasX())).append("\" ")
              .append("height=").append("\"").append(String.valueOf(model.getCanvasY()))
              .append("\" ").append("version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");
      for (Shape s : model.getShapes()) {
        output.append("\n").append(svgShape(s));
      }
      output.append("\n</svg>");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getSpeed() {
    return this.frameRate;
  }

  @Override
  public AnimationController getController() {
    throw new UnsupportedOperationException("SVGView has no Controller!");
  }
}