package cs3500.animator.model;

import java.util.List;

/**
 * Represents a Model that is READ-ONLY. That is, it can only do things that don't change its data.
 */
public class ROAnimationModelImpl implements AnimationModel {
  private final AnimationModel actual;

  public ROAnimationModelImpl(AnimationModel actual) {
    this.actual = actual;
  }

  /**
   * Adds the given transformation to the Shape with the given name.
   *
   * @param name - the name of the Shape the transformation will be added to
   * @param t - the Transformation to be added
   * @throws IllegalArgumentException if name is null
   */
  @Override
  public void addTransformation(String name, Transformation t) {
    throw new UnsupportedOperationException("cannot add a transformation in a read-only model");
  }

  /**
   * Removes a given Shape from this.shapes.
   *
   * @param shapeName - the shape to be removed
   */
  @Override
  public void removeShape(String shapeName) {
    throw new UnsupportedOperationException("cannot remove a shape in a read-only model");
  }

  /**
   * Removes a given Transformation from the Shape with the given name.
   *
   * @param name the name of the shape to remove this transformation from
   * @param t the transformation to remove
   */
  @Override
  public void removeTransformation(String name, Transformation t) {
    throw new UnsupportedOperationException("cannot remove a transformation in a read-only model");
  }

  /**
   * Returns this model's Canvas Width.
   *
   * @return this model's canvas width
   */
  @Override
  public int getCanvasW() {
    return this.actual.getCanvasW();
  }

  /**
   * Returns this model's Canvas Height.
   *
   * @return this model's canvas height
   */
  @Override
  public int getCanvasH() {
    return this.actual.getCanvasH();
  }

  /**
   * Sets this Model's canvas to the given parameters.
   *
   * @param x x origin position
   * @param y y origin position
   * @param w canvas width
   * @param h canvas height
   */
  @Override
  public void setCanvas(int x, int y, int w, int h) {
    throw new UnsupportedOperationException("cannot set the Canvas of a read-only model");
  }

  /**
   * Sets this Model's canvas to be the biggest necessary to view the entire animation.
   */
  @Override
  public void setCanvas() {
    throw new UnsupportedOperationException("cannot set the Canvas of a read-only model");
  }

  /**
   * Returns this model's Canvas X origin position.
   *
   * @return this model's canvas x origin position
   */
  @Override
  public int getCanvasX() {
    return this.actual.getCanvasX();
  }

  /**
   * Returns this model's Canvas Y origin position.
   *
   * @return this model's canvas y origin position
   */
  @Override
  public int getCanvasY() {
    return this.actual.getCanvasY();
  }

  /**
   * Adds a Shape to this.shapes.
   *
   * @param s - the Shape to be added
   * @throws IllegalArgumentException if Shape s is null
   */
  @Override
  public void addShape(Shape s) {
    throw new UnsupportedOperationException(
            "cannot add a transformation in a read-only cs3500.animator.model");
  }

  /**
   * Advance every Shape in this.shapes to the given time.
   *
   * @param t - the time that each shape will be advanced to
   * @throws IllegalArgumentException if t is negative
   */
  @Override
  public void advance(int t) {
    actual.advance(t);
  }

  /**
   * Returns the current state of every shape.
   *
   * @return a String describing a shape's type, name, and current properties, at this time.
   */
  @Override
  public String currentView() {
    return actual.currentView();
  }

  /**
   * Returns how many shapes are currently held by this Model.
   *
   * @return The number of shapes.
   */
  @Override
  public int howManyShapes() {
    return actual.howManyShapes();
  }

  /**
   * Returns the greatest time amongst any of this Model's Shape's Transformations's Properties.
   *
   * @return the greatest time.
   */
  @Override
  public int getMaxT() {
    return actual.getMaxT();
  }

  /**
   * Returns the type of a shape of a given name.
   *
   * @return the shape's type
   */
  @Override
  public String getShapeType(String shapeName) {
    return actual.getShapeType(shapeName);
  }

  @Override
  public List<Shape> getShapes() {
    return actual.getShapes();
  }
}