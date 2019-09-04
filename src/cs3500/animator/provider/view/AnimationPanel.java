package cs3500.animator.provider.view;

import cs3500.animator.provider.adapter.ImmutableShape;
import java.util.List;
import javax.swing.JPanel;

import java.awt.Graphics;

import java.util.ArrayList;

import java.awt.Point;

/**
 * A panel for the VisualView, that draws all of the shapes contained in the animation.
 */
public class AnimationPanel extends JPanel {

  private List<ImmutableShape> shapes;
  private Point offset;

  /**
   * Creates an instance of an AnimationPanel.
   */
  public AnimationPanel() {
    shapes = new ArrayList<>();
    offset = new Point(0, 0);
  }

  /**
   * Sets the shapes of the AnimationPanel to draw.
   * @param shapes the shapes to be drawn.
   */
  public void setShapes(List<ImmutableShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Set the shapes to the tick/particular time of the animation to be drawn.
   * @param time the tick being drawn.
   */
  //perhaps the time exception should be thrown here?
  public void setTime(double time) {
    for (ImmutableShape s : shapes) {
      s.setShape(time);
    }
  }

  /**
   * set the offset of the AnimationPanel.
   * @param p a Point, representing the top-left corner of the canvas, by which the shapes will
   *        be pushed in their drawing.
   */
  public void setOffset(Point p) {
    this.offset = p;
  }

  /**
   * Draws the shapes using the specifications of the Visual View onto the pop-up window.
   * @param g a graphics object used for drawing.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    ViewVisitor v = new VisualVisitor(g, offset);

    for (ImmutableShape s : shapes) {
      v.visitShape(s);
    }
  }
}
