package cs3500.animator.provider.adapter;

import cs3500.animator.model.KeyframeShape;
import cs3500.animator.model.Property;
import cs3500.animator.provider.view.ViewVisitor;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

/**
 * A Shape object use for provider code.
 * Uses methods that the providers have for shapes (tweening, visitor methods...)
 */
public abstract class ImmutableShape extends KeyframeShape {

  /**
   * The constructor for AShape, taking in...
   *
   * @param name - the name of this shape
   * @param startProperty - the properties of this shape
   * @param type - the type of shape
   * @throws IllegalArgumentException if parameters are null or the type given isn't supported
   */
  public ImmutableShape(String name, Property startProperty, String type) {
    super(name, startProperty, type);
  }

  public abstract String accept(ViewVisitor visitor);

  /**
   * Advances the shape to the given time.
   * @param time the time the shape's properties to the given time.
   */
  public void setShape(double time) {
    this.advance((int) time);
  }

  /**
   * Gets the Position of this shape in the form of a Point object.
   * @return a Point object representing the position of the shape.
   */
  public Point getPosition() {
    int x = this.properties.getPosx();
    int y = this.properties.getPosy();
    return new Point(x, y);
  }

  /**
   * Gets the size of this shape in the form of a Dimension object.
   * @return a Dimension object with the dimensions of this shape.
   */
  public Dimension getSize() {
    int w = this.properties.getWidth();
    int h = this.properties.getHeight();
    return new Dimension(w, h);
  }

  /**
   * Gets the color properties of this shape in the form of a Color object.
   * @return a Color object with the rgb values of this shape.
   */
  public Color getColor() {
    int r = this.properties.getColorR();
    int g = this.properties.getColorG();
    int b = this.properties.getColorB();
    return new Color(r, g, b);
  }
}