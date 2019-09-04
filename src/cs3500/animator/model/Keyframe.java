package cs3500.animator.model;

public interface Keyframe extends Comparable<Keyframe> {
  /**
   * Returns the time this KeyFrame takes place at.
   *
   * @return an integer representing the time this keyframe occurs at.
   */
  int getTime();

  /**
   * Returns the properties of a shape at this Keyframe.
   *
   * @return - A Property object representing the properties of a Shape at this.time.
   */
  Property getProperty();

  /**
   * Returns the x-position of a Shape at this Keyframe.
   *
   * @return - an int representing the x-position of a Shape at this Keyframe.
   */
  int getX();

  /**
   * Returns the y-position of a Shape at this Keyframe.
   *
   * @return - an int representing the y-position of a Shape at this Keyframe.
   */
  int getY();

  /**
   * Returns the width of a Shape at this Keyframe.
   *
   * @return - an int representing the width of a Shape at this Keyframe.
   */
  int getW();

  /**
   * Returns the height of a Shape at this Keyframe.
   *
   * @return - an int representing the height of a Shape at this Keyframe.
   */
  int getH();

  /**
   * Returns the red rgb value of a Shape at this Keyframe.
   *
   * @return - an int representing the red rgb of a Shape at this Keyframe.
   */
  int getR();

  /**
   * Returns the green rgb value of a Shape at this Keyframe.
   *
   * @return - an int representing the green rgb of a Shape at this Keyframe.
   */
  int getG();

  /**
   * Returns the blue rgb value of a Shape at this Keyframe.
   *
   * @return - an int representing the blue rgb of a Shape at this Keyframe.
   */
  int getB();
}
