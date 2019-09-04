package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Represents a point in time where a Shape has certain properties.
 */
public class KeyframeImpl implements cs3500.animator.provider.model.Keyframe, Keyframe {

  private Property property;
  private int time;

  /**
   * Constructs a keyframe with the given properties at the given time.
   * @param property the properties of this keyframe
   * @param time the time it takes place
   */
  public KeyframeImpl(Property property, int time) {
    this.property = property;
    this.time = time;
  }

  /**
   * Constructs a keyframe using Points, Dimensions, and Colors from .awt.
   * @param time the time it takes place
   * @param newPos the x and y values of this Keyframe's properties
   * @param newSize the w and h values of this Keyframe's properties
   * @param newColor the r, g, and b values of this Keyframe's properties
   */
  public KeyframeImpl(int time, Point newPos, Dimension newSize, Color newColor) {
    this.time = time;
    this.property = new PropImpl((int) newPos.getX(), (int) newPos.getY(), (int) newSize.getWidth(),
            (int) newSize.getHeight(), newColor.getRed(), newColor.getGreen(), newColor.getBlue());
  }

  @Override
  public Point getPos() {
    return new Point(this.property.getPosx(), this.property.getPosy());
  }

  @Override
  public Dimension getSize() {
    return new Dimension(this.property.getWidth(), this.property.getHeight());
  }

  @Override
  public Color getColor() {
    return new Color(this.property.getColorR(), this.property.getColorG(),
        this.property.getColorB());
  }

  @Override
  public int getTime() {
    return this.time;
  }

  @Override
  public Property getProperty() {
    return this.property;
  }

  @Override
  public int getX() {
    return this.property.getPosx();
  }

  @Override
  public int getY() {
    return this.property.getPosy();
  }

  @Override
  public int getW() {
    return this.property.getWidth();
  }

  @Override
  public int getH() {
    return this.property.getHeight();
  }

  @Override
  public int getR() {
    return this.property.getColorR();
  }

  @Override
  public int getG() {
    return this.property.getColorG();
  }

  @Override
  public int getB() {
    return this.property.getColorB();
  }

  @Override
  public String toString() {
    return this.getTime() + " " + this.getProperty().toString();
  }

  @Override
  public int compareTo(Keyframe o) {
    if (o == null) {
      throw new IllegalArgumentException("can't compare a null to a keyframe!");
    }
    if (!(o instanceof KeyframeImpl)) {
      throw new ClassCastException("can't compare this object to a Keyframe");
    }
    KeyframeImpl key = (KeyframeImpl) o;
    return Integer.compare(this.getTime(), key.getTime());
  }
}