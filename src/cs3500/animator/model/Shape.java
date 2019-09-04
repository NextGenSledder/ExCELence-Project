package cs3500.animator.model;

import java.util.List;

/**
 * Represents a Shape to be animated.
 */
public interface Shape {
  /**
   * Returns the list of Transformations for this Shape.
   * @return the list of Transformations for this Shape
   */
  List<Transformation> getTransformations();

  /**
   * Returns the list of Keyframes for this Shape.
   * @return the list of Keyframes for this Shape
   */
  List<KeyframeImpl> getKeyframes();

  /**
   * Returns the current properties of this Shape.
   * @return the current properties of this Shape.
   */
  Property getProperties();

  /**
   * Returns the name of this Shape.
   * @return the name of this Shape
   */
  String getName();

  /**
   * Returns what kind of Shape this is.
   * @return the type of this Shape
   */
  ShapeType getType();

  /**
   * Adds a transformation to this Shape's list of Transformations.
   * @param t - The Transformation to be added
   * @throws IllegalArgumentException if multiple Transformations on this shape overlap
   */
  void addTransformation(Transformation t) throws IllegalArgumentException;

  /**
   * Removes a transformation from this Shape's list of Transformations.
   * @param t - The Transformation to be added
   */
  void removeTransformation(Transformation t);

  /**
   * Adds a Keyframe to this Shape's list of Keyframes.
   * @param k - The Keyframe to be added.
   * @throws IllegalArgumentException is the given Keyframe shares its time with another in the
   *                                  shape's list of keyframes.
   */
  void addKeyframe(KeyframeImpl k);

  /**
   * Removes a Keyframe from this Shape's list of Keyframes.
   * @param k - The Keyframe to be added
   */
  void removeKeyframe(Keyframe k);

  /**
   * Mutate this Shape to advance to the given time based on the Transformations it will undergo.
   * @param t - the time that the shape will be mutated to.
   * @throws IllegalArgumentException if t is negative
   */
  void advance(int t) throws IllegalArgumentException;

  /**
   * Returns the time that this Shape stops animating.
   * That is: the end of its last Transformation / Keyframe
   * @return an int representing the time when the Shape stops having its properties altered.
   */
  int getEndT();
}