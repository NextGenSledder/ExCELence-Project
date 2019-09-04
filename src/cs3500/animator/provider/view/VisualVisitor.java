package cs3500.animator.provider.view;

import cs3500.animator.provider.adapter.ImmutableShape;
import java.awt.Graphics;
import java.awt.Point;

/**
 * a {@link ViewVisitor} that draws shapes visually in a pop-up window.
 */
public class VisualVisitor implements ViewVisitor {
  Graphics g;
  Point offset;

  /**
   * Constructs an instance of a VisualVisitor object.
   * @param g a Graphics object capable of drawing.
   * @param offset the top-left corner of the canvas being drawn, AKA the amount shapes must be
   *        repositioned to orient properly on the canvas.
   */
  public VisualVisitor(Graphics g, Point offset) {
    if (g == null || offset == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }
    this.g = g;
    this.offset = offset;
  }

  @Override
  public String visitShape(ImmutableShape s) throws IllegalArgumentException {
    if (s == null) {
      throw new IllegalArgumentException("shape cannot be null");
    }
    return s.accept(this);
  }

  /**
   * Produces a visual for the rectangle in a pop-up window.
   * @param r the rectangle to be drawn.
   * @return a String representation of this View.
   */
  @Override
  public String visitRect(ImmutableShape r) throws IllegalArgumentException {
    if (r == null) {
      throw new IllegalArgumentException("shape cannot be null");
    }
    g.setColor(r.getColor());
    g.fillRect((int) Math.round(r.getPosition().getX() - offset.getX()),
        (int) Math.round((r.getPosition().getY() - offset.getY())),
        (int) Math.round(r.getSize().getWidth()), (int) Math.round(r.getSize().getHeight()));

    return "";
  }

  /**
   * Produces a visual for the ellipse in a pop-up window.
   * @param e the ellipse to be drawn.
   * @return a String representation of this View.
   */
  @Override
  public String visitEllipse(ImmutableShape e) throws IllegalArgumentException {
    if (e == null) {
      throw new IllegalArgumentException("shape cannot be null");
    }
    g.setColor(e.getColor());
    g.fillOval((int) Math.round(e.getPosition().getX() - offset.getX()),
        (int) Math.round((e.getPosition().getY() - offset.getY())),
        (int) Math.round(e.getSize().getWidth()), (int) Math.round(e.getSize().getHeight()));

    return "";
  }
}
