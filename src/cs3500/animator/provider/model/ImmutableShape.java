package cs3500.animator.provider.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import cs3500.animator.provider.view.ViewVisitor;

/**
 * An interface for the operations on a Shape object that do not mutate that shape.
 * Represents the interface that non-Model classes can interact with.
 */
public interface ImmutableShape {

  /**
   * Set this ImmutableShape's state to its state at a given time.
   * @param time the time that the ImmutableShape will occupy.
   */
  void setShape(double time);

  /**
   * Get the current Position of the ImmutableShape as a
   * Point object describing its x and y coordinate.
   * See {@link Point}.
   * @return a Point object of the ImmutableShape's position.
   */
  Point getPosition();

  /**
   * Get the current Size of the ImmutableShape as a Dimension object describing its
   * width and height. See {@link Dimension}.
   * @return a Dimension object of the Shape's size.
   */
  Dimension getSize();

  /**
   * Get the current color of the ImmutableShape as a Color object describing its color in terms of
   * red, green and blue. See {@link Color}.
   * @return a Color object of the ImmutableShape's color.
   */
  Color getColor();

  /**
   * Return the name and unique ID of this ImmutableShape.
   * @return the name of this shape.
   */
  String getName();

  /**
   * Return the time that this ImmutableShape is currently in.
   * @return the ImmutableShape's current time that its values currently describe.
   */
  int getTime();

  /**
   * Return a list of all Motions of this object to describe its full trajectory through
   * its lifespan.
   * @return a List of the Motions contained in this ImmutableShape object.
   */
  List<Motion> getMotions();

  /**
   * Returns a list of all Keyframes of this object to describe its full trajectory through
   * its lifespan.
   * @return a List of the Keyframes that represent the motion of this ImmutableShape.
   */
  List<Keyframe> getKeyframes();

  /**
   * Accept a Visitor that can draw this Shape based on the Shape's information.
   * See {@link ViewVisitor}.
   * @param v a ViewVisitor object that is capable of drawing the Shape in some form.
   * @return information regarding the drawing of this Shape.
   */
  String accept(ViewVisitor v);

}
