package cs3500.animator.provider.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 * A keyframe is a data representation of a Shape's state at a singular point in time.
 */
public interface Keyframe {

  /**
   * Get the time that this Keyframe describes.
   * @return the time that the object containing this keyframe is at this keyframe's described
   *         position.
   */
  int getTime();

  /**
   * Get the position that this keyframe describes.
   * @return the position that the Shape containing this keyframe is at at this keyframe's time.
   */
  Point getPos();

  /**
   * Get the size that this keyframe describes.
   * @return the size that the Shape containing this keyframe is at at this keyframe's time.
   */
  Dimension getSize();

  /**
   * Get the color that this keyframe describes.
   * @return the color that the Shape containing this keyframe is at at this keyframe's time.
   */
  Color getColor();
}
