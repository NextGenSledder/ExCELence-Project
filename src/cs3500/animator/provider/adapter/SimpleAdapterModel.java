package cs3500.animator.provider.adapter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.KeyframeAnimationModel;
import cs3500.animator.model.KeyframeAnimationModelImpl;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.KeyframeShape;
import cs3500.animator.model.PropImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.Transformation;
import cs3500.animator.provider.model.SimpleAnimatorModel;
import cs3500.animator.util.AnimationModelAdapter;

/**
 * A model that works with both our and our provider's model aka an adapter model.
 */
public class SimpleAdapterModel implements KeyframeAnimationModel, SimpleAnimatorModel {
  private KeyframeAnimationModelImpl base;

  public SimpleAdapterModel(AnimationModel base) {
    this.base = AnimationModelAdapter.adaptToKeyframeModel(base);
  }

  @Override
  public void addShape(String shapeType, String name) throws IllegalArgumentException {
    this.addShape(new KeyframeShape(name,
            new PropImpl(0, 0, 0 ,0, 0, 0 ,0), shapeType));
  }

  @Override
  public void addShape(Shape s) {
    this.base.addShape(s);
  }

  @Override
  public void removeMotion(String name, int startTime, int endTime)
          throws IllegalArgumentException {
    throw new UnsupportedOperationException("We don't deal with Motions in the EditableView!");
  }

  @Override
  public void moveShape(String name, int startTime, int endTime, Point startPos, Point endPos,
                        Dimension startSize, Dimension endSize, Color startColor, Color endColor)
          throws IllegalArgumentException {
    throw new UnsupportedOperationException("We don't deal with Motions in the EditableView!");
  }

  @Override
  public void addKeyframe(String name, int time) throws IllegalArgumentException {
    this.addKeyframe(name,
            new KeyframeImpl(new PropImpl(0,0,0,0,0,0,0), time));
  }

  @Override
  public void addKeyframe(String name, Keyframe key) {
    this.base.addKeyframe(name, key);
  }

  @Override
  public void editKeyframe(String name, int time, Point pos, Dimension size, Color color) {
    this.addKeyframe(name, new KeyframeImpl(time, pos, size, color));
  }

  @Override
  public List<ImmutableShape> getAnimation() {
    List<ImmutableShape> immutableShapes = new ArrayList<>();
    for (Shape s : this.base.getShapes()) {
      if (s.getType().toString().equalsIgnoreCase(ShapeType.ELLIPSE.toString())) {
        ImmEllipse newEllipse = new ImmEllipse(s);
        for (KeyframeImpl key : s.getKeyframes()) {
          newEllipse.addKeyframe(key);
        }
        immutableShapes.add(newEllipse);
      }
      else if (s.getType().toString().equalsIgnoreCase(ShapeType.RECTANGLE.toString())) {
        ImmRectangle newRectangle = new ImmRectangle(s);
        for (KeyframeImpl key : s.getKeyframes()) {
          newRectangle.addKeyframe(key);
        }
        immutableShapes.add(newRectangle);
      }
    }
    return immutableShapes;
  }

  @Override
  public Point getCanvasPosition() {
    return new Point(this.getCanvasX(), this.getCanvasY());
  }

  @Override
  public Dimension getCanvasSize() {
    return new Dimension(this.getCanvasW(), this.getCanvasH());
  }

  @Override
  public int getAnimationLength() {
    return this.getMaxT();
  }

  @Override
  public void removeKeyframe(String name, int t) {
    this.base.removeKeyframe(name, t);
  }

  @Override
  public void setCanvas(int x, int y, int w, int h) {
    this.base.setCanvas(x, y, w, h);
  }

  @Override
  public void setCanvas() {
    this.base.setCanvas();
  }

  @Override
  public int getCanvasX() {
    return this.base.getCanvasX();
  }

  @Override
  public int getCanvasY() {
    return this.base.getCanvasY();
  }

  @Override
  public int getCanvasW() {
    return this.base.getCanvasW();
  }

  @Override
  public int getCanvasH() {
    return this.base.getCanvasH();
  }

  @Override
  public void addTransformation(String name, Transformation t) {
    this.base.addTransformation(name, t);
  }

  @Override
  public void removeShape(String shapeName) {
    this.base.removeShape(shapeName);
  }

  @Override
  public void removeTransformation(String name, Transformation t) {
    this.base.removeTransformation(name, t);
  }

  @Override
  public void advance(int t) {
    this.base.advance(t);
  }

  @Override
  public String currentView() {
    return this.base.currentView();
  }

  @Override
  public int howManyShapes() {
    return this.base.howManyShapes();
  }

  @Override
  public int getMaxT() {
    return this.base.getMaxT();
  }

  @Override
  public String getShapeType(String shapeName) {
    return this.base.getShapeType(shapeName);
  }

  @Override
  public List<Shape> getShapes() {
    return this.base.getShapes();
  }
}
