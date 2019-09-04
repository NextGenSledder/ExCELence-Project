package cs3500.animator.model;

/**
 * Represents the properties of a shape.
 * INVARIANT: all color-related fields must be between 0 and 255 inclusive.
 * INVARIANT: all size-related fields must be at least 1.
 */
public class PropImpl implements Property {
  private int posx;
  private int posy;
  private int width;
  private int height;
  private int colorR;
  private int colorG;
  private int colorB;

  /**
   * The constructor for Property, taking in...
   * @param x - the x coordinate of this property
   * @param y - the y coordinate of this property
   * @param w - the width of this property
   * @param h - the height of this property
   * @param r - the red color value for this property
   * @param g - the green color value for this property
   * @param b - the blue color value for this property
   * @throws IllegalArgumentException if w or h aren't positive OR if the rgb values aren't between
   *                                  0 and 255
   */
  public PropImpl(int x, int y, int w, int h, int r, int g, int b) {
    if (w < 0 || h < 0) {
      throw new IllegalArgumentException("Size must be non-negative!");
    }
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Color values must be between 0 and 255");
    }
    this.posx = x;
    this.posy = y;
    this.width = w;
    this.height = h;
    this.colorR = r;
    this.colorG = g;
    this.colorB = b;
  }

  @Override
  public int getPosx() {
    return this.posx;
  }

  @Override
  public int getPosy() {
    return this.posy;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getColorR() {
    return this.colorR;
  }

  @Override
  public int getColorG() {
    return this.colorG;
  }

  @Override
  public int getColorB() {
    return this.colorB;
  }

  @Override
  public String toString() {
    return this.posx + " " + this.posy + " " + this.width + " "
        + this.height + " " + this.colorR + " " + this.colorG + " " + this.colorB;
  }

  @Override
  public int hashCode() {
    return (int) (Math.random() * 100);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Property)) {
      return false;
    }

    Property prop = (Property) obj;
    return this.getPosx() == prop.getPosx()
        && this.getPosy() == prop.getPosy()
        && this.getWidth() == prop.getWidth()
        && this.getHeight() == prop.getHeight()
        && this.getColorR() == prop.getColorR()
        && this.getColorG() == prop.getColorG()
        && this.getColorB() == prop.getColorB();
  }
}