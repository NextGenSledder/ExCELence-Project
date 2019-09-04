package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.util.AnimationBuilder;

/**
 * Represents the cs3500.animator.model for an animation creation software.
 */
public class AnimationModelImpl implements AnimationModel {

  protected List<Shape> shapes;
  private int canvasW;
  private int canvasH;
  private int canvasX;
  private int canvasY;

  /**
   * Construct an empty AnimationModelImpl with a default canvas size.
   */
  public AnimationModelImpl() {
    this.shapes = new ArrayList<>();
    this.canvasW = 0;
    this.canvasH = 0;
    this.canvasX = 0;
    this.canvasY = 0;
  }

  @Override
  public void setCanvas(int x, int y, int w, int h) {
    this.canvasW = x;
    this.canvasH = y;
    this.canvasX = w;
    this.canvasY = h;
  }

  @Override
  public void setCanvas() {
    int biggestXW = 0;
    int biggestYH = 0;
    for (Shape s : this.shapes) {
      for (Transformation t : s.getTransformations()) {
        int xw1 = t.getStartState().getPosx() + t.getStartState().getWidth();
        int yh1 = t.getStartState().getPosx() + t.getStartState().getWidth();
        int xw2 = t.getStartState().getPosx() + t.getStartState().getWidth();
        int yh2 = t.getStartState().getPosx() + t.getStartState().getWidth();

        if (xw1 > biggestXW) {
          biggestXW = xw1;
        }
        if (yh1 > biggestYH) {
          biggestYH = yh1;
        }
        if (xw2 > biggestXW) {
          biggestXW = xw2;
        }
        if (yh2 > biggestYH) {
          biggestYH = yh1;
        }
      }
    }
    this.canvasX = 0;
    this.canvasY = 0;
    this.canvasW = biggestXW;
    this.canvasH = biggestYH;
  }

  /**
   * Returns the width of the canvas of the animation.
   *
   * @return an int representing the width
   */
  public int getCanvasW() {
    return this.canvasW;
  }

  public int getCanvasH() {
    return this.canvasH;
  }

  public int getCanvasX() {
    return this.canvasX;
  }

  public int getCanvasY() {
    return this.canvasY;
  }

  @Override
  public void addShape(Shape s) {
    if (s == null) {
      throw new IllegalArgumentException("Can't add a null shape!");
    }
    this.shapes.add(s);
  }

  @Override
  public void addTransformation(String name, Transformation t) {
    if (name == null) {
      throw new IllegalArgumentException("Can't add a Transformation to a null shape");
    }
    for (Shape shape : this.shapes) {
      if (shape.getName().equalsIgnoreCase(name)) {
        shape.addTransformation(t);
      }
    }
  }

  @Override
  public void removeShape(String shapeName) {
    for (int i = 0; i < this.shapes.size(); i++) {
      if (this.shapes.get(i).getName().equalsIgnoreCase(shapeName)) {
        this.shapes.remove(i);
      }
    }
  }

  @Override
  public void removeTransformation(String name, Transformation t) {
    if (name == null) {
      // why bother throwing an exception when remove is expected to do nothing with bogus data?
      return;
    }
    for (Shape shape : this.shapes) {
      if (shape.getName().equalsIgnoreCase(name)) {
        shape.removeTransformation(t);
      }
    }
  }

  @Override
  public void advance(int t) {
    if (t < 0) {
      throw new IllegalArgumentException("Can't advance to negative time!");
    }
    for (Shape s : this.shapes) {
      s.advance(t);
    }
  }

  @Override
  public String currentView() {
    StringBuilder output = new StringBuilder();
    for (Shape shape : this.shapes) {
      output.append(shape.toString()).append("\n");
    }
    return output.toString();
  }

  @Override
  public int howManyShapes() {
    return this.shapes.size();
  }

  @Override
  public int getMaxT() {
    int maxT = 0;
    for (Shape s : this.shapes) {
      if (s.getEndT() > maxT) {
        maxT = s.getEndT();
      }
    }
    return maxT;
  }

  @Override
  public String getShapeType(String shapeName) {
    for (Shape shape : this.shapes) {
      if (shape.getName().equals(shapeName)) {
        return shape.getType().toString();
      }
    }
    throw new IllegalArgumentException("Given shape could not be found");
  }

  @Override
  public List<Shape> getShapes() {
    List<Shape> copies = ((List) ((ArrayList) this.shapes).clone());
    return copies;
  }

  /**
   * Represents a builder that constructs an AnimationModel.
   */
  public static class AnimationModelImplBuilder implements AnimationBuilder<AnimationModel> {

    AnimationModel model = new AnimationModelImpl();

    @Override
    public AnimationModel build() {
      return model;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      Shape shape = new AShape(name, new PropImpl(0, 0, 0, 0, 0, 0, 0), type);
      this.model.addShape(shape);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2,
        int g2, int b2) {
      Property prop1 = new PropImpl(x1, y1, w1, h1, r1, g1, b1);
      Property prop2 = new PropImpl(x2, y2, w2, h2, r2, g2, b2);
      Transformation trans = new ATransformation(prop1, prop2, t1, t2);
      model.addTransformation(name, trans);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
        int h, int r, int g, int b) {
      throw new UnsupportedOperationException(
          "Builder operates with solely addMotion and does not utilize addKeyFrame at this time");
    }
  }
}