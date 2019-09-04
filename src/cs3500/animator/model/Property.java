package cs3500.animator.model;

/**
 * A class to bundle the properties of a shape.
 */
public interface Property {

  /**
   * Returns this.posx
   * @return an int representing this.posx
   */
  int getPosx();

  /**
   * Returns this.posy
   * @return an int representing this.posy
   */
  int getPosy();

  /**
   * Returns this.width
   * @return an int representing this.width
   */
  int getWidth();

  /**
   * Returns this.height
   * @return an int representing this.height
   */
  int getHeight();

  /**
   * Returns this.colorR
   * @return an int representing this.colorR
   */
  int getColorR();

  /**
   * Returns this.colorG
   * @return an int representing this.colorG
   */
  int getColorG();

  /**
   * Returns this.colorB
   * @return an int representing this.colorB
   */
  int getColorB();
}
