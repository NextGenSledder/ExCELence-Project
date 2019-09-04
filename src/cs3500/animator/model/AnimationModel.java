package cs3500.animator.model;

import java.util.List;

/**
 * Represents the Model of our Animation software. It contains all of the shapes in the animation.
 * Changes to the shapes can be added or removed.
 */
public interface AnimationModel {

  /**
   * Sets this Model's canvas to the given parameters.
   * @param x x origin position
   * @param y y origin position
   * @param w canvas width
   * @param h canvas height
   */
  void setCanvas(int x, int y, int w, int h);

  /**
   * Sets this Model's canvas to be the biggest necessary to view the entire animation.
   */
  void setCanvas();

  /**
   * Returns this model's Canvas X origin position.
   * @return this model's canvas x origin position
   */
  int getCanvasX();

  /**
   * Returns this model's Canvas Y origin position.
   * @return this model's canvas y origin position
   */
  int getCanvasY();

  /**
   * Returns this model's Canvas Width.
   * @return this model's canvas width
   */
  int getCanvasW();

  /**
   * Returns this model's Canvas Height.
   * @return this model's canvas height
   */
  int getCanvasH();

  /**
   * Adds a Shape to this.shapes.
   * @param s - the Shape to be added
   * @throws IllegalArgumentException if Shape s is null
   */
  void addShape(Shape s);

  /**
   * Adds the given transformation to the Shape with the given name.
   * @param t - the Transformation to be added
   * @param name - the name of the Shape the transformation will be added to
   * @throws IllegalArgumentException if name is null
   */
  void addTransformation(String name, Transformation t);

  /**
   * Removes a given Shape from this.shapes.
   * @param shapeName - the name of the shape to be removed
   */
  void removeShape(String shapeName);

  /**
   * Removes a given Transformation from the Shape with the given name.
   * @param name the name of the shape to remove this transformation from
   * @param t the transformation to remove
   */
  void removeTransformation(String name, Transformation t);

  /**
   * Advance every Shape in this.shapes to the given time.
   * @param t - the time that each shape will be advanced to
   * @throws IllegalArgumentException if t is negative
   */
  void advance(int t);

  /**
   * Returns the current state of every shape.
   * @return a String describing a shape's type, name, and current properties, at this time.
   */
  String currentView();

  /**
   * Returns how many shapes are currently held by this Model.
   * @return The number of shapes.
   */
  int howManyShapes();

  /**
   * Returns the greatest time amongst any of this Model's Shape's Transformations's Properties.
   * @return the greatest time.
   */
  int getMaxT();

  /**
   * Returns the type of a shape of a given name.
   * @return the shape's type
   */
  String getShapeType(String shapeName);

  /**
   * Returns a list containing copies of every shape animated in this cs3500.animator.model.
   * @return A List of Shapes that are all copies of Shapes that are used in this animation.
   */
  List<Shape> getShapes();
}