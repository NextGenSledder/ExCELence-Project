package cs3500.animator.provider.model;


import java.awt.Point;

import java.awt.Dimension;

import java.awt.Color;

import java.util.List;

import cs3500.animator.provider.adapter.ImmutableShape;

/**
 * An interface for a Shape object. A shape can transform in different ways, described by the motion
 * objects passed to it.
 */
public interface Shape {

  /**
   * motion a Shape based on the directions provided by a {@link Motion} object.
   *
   * @param obj the motion object describing how the shape will motion.
   * @throws IllegalArgumentException if the motion is invalid.
   */
  void move(Motion obj) throws IllegalArgumentException;

  /**
   * Set the shape's position to a particular time in its existence. Used to then query
   * for its fields and information at that specified time.
   * @param time the time of the shape's position being requested.
   */
  void setShape(double time);

  //MAINTAINS THE INVARIANT THAT ALL TIME IS COVERED: THEREFORE, ONLY FRONT OR BACK.

  /**
   * Removes a Motion from the Shape. Only removes the motion if the removal maintains
   * the continuity between the Motion's time and position—-no teleporting. See {@link Motion}.
   * @param startTime the start time of the Motion to be removed.
   * @param endTime the end time of the motion to be removed.
   */
  void removeMotion(int startTime, int endTime);

  /**
   * Adds a keyframe to the motion of this Shape. See {@link Keyframe}.
   * @param time the time of the added keyframe.
   * @throws IllegalArgumentException if arguments are invalid.
   */
  void addKeyframe(int time) throws IllegalArgumentException;

  /**
   * Removes a keyframe from the motion of this Shape. See {@link Keyframe}.
   * @param time the time of the removed keyframe.
   * @throws IllegalArgumentException if arguments are invalid.
   */
  void removeKeyframe(int time) throws IllegalArgumentException;

  /**
   * Edits a keyframe in the motion of this Shape. See {@link Keyframe}.
   * @param time the time of the edited keyframe.
   * @param pos the new Position at the specified time.
   * @param size the new Size at the specified time.
   * @param color the new Color at the specified time.
   */
  void editKeyframe(int time, Point pos, Dimension size, Color color);

  /**
   * Get the current Position of the Shape as a Point object describing its x and y coordinate.
   * See {@link Point}.
   * @return a Point object of the Shape's position.
   */
  Point getPosition();

  /**
   * Get the current Size of the Shape as a Dimension object describing its width and height.
   * See {@link Dimension}.
   * @return a Dimension object of the Shape's size.
   */
  Dimension getSize();

  /**
   * Get the current color of the Shape as a Color object describing its color in terms of
   * red, green and blue. See {@link Color}.
   * @return a Color object of the Shape's color.
   */
  Color getColor();

  /**
   * Return the name and unique ID of this Shape.
   * @return the name of this shape.
   */
  String getName();

  /**
   * Return the time that this Shape is currently in.
   * @return the Shape's current time that its values currently describe.
   */
  int getTime();

  /**
   * Return a list of all Motions of this object to describe its full trajectory through
   * its lifespan.
   * @return a List of the Motions contained in this Shape object.
   */
  List<Motion> getMotions();

  /**
   * Returns a list of Keyframes that represent the motion of this object. These keyframes
   * are consistent with the Motions of this object.
   * @return a list of keyframes that represent the motion of this Shape.
   */
  List<Keyframe> getKeyframes();

  /**
   * Produces an immutable version of this Shape to be used outside of the Model.
   * @return an Immutable version of the Shape to prevent mutation outside of the model.
   */
  ImmutableShape immutable();
}
