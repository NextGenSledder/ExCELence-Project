package cs3500.animator.provider.model;

import java.awt.Point;

import java.awt.Dimension;

import java.awt.Color;


/**
 * A representation of a movement from one location to another. Allows the user to query for
 * information regarding the Motion, such as start and end time of the motion and the properties
 * of the Motion at certain times.
 */
public interface Motion {
  //Nothing in this interface can be mutated. Thus, exposing Motions is safe.

  /**
   * returns the starting time of the Motion object, the time when this Motion begins
   * in the animation.
   * @return the starting time of the Motion.
   */
  int getStartTime();

  /**
   * returns the ending time of the Motion object, the time when this Motion ends in the
   * animation.
   * @return the ending time of the Motion.
   */
  int getEndTime();

  /**
   * Get the position of this Motion at a specified time. Position is a Point, which stores
   * the X and Y coordinate of the shape's position. See {@link Point}.
   * @param time a time for this Motion to calcuate its position for.
   * @return a Point of the Motion's location at a specified time.
   */
  Point getPosition(double time);

  /**
   * Get the size of this Motion at a specified time. Size is a Dimension, which stores
   * the width and height of the Shape at the given time. See {@link Dimension}.
   * @param time a time for this Motion to calculate its size for.
   * @return a Dimension of the Motion's size at a specified time.
   */
  Dimension getSize(double time);

  /**
   * Get the color of this Motion at a specified time. A Color object stores the color of the
   * object in RGB form. See {@link Color}.
   * @param time a time for this Motion to calculate its color for.
   * @return a Color of the Motion's Color at a specified time.
   */
  Color getColor(double time);
}
