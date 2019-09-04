package cs3500.animator.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;
import javax.swing.event.ListSelectionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;

import java.util.List;

import cs3500.animator.provider.adapter.ImmutableShape;
import cs3500.animator.provider.model.Keyframe;

/**
 * Implements the AnimatorView and represents a Visual animation. Produces a pop-up window
 * that displays the animation in Motion. Extends {@link JFrame} to produce visuals
 * using the Java Swing library.
 */
public class VisualView extends JFrame implements AnimatorView {

  //the panes and panels in the View.
  private AnimationPanel animationPanel;

  //the specification of the View provided by data from the Model.
  private Point canvasPosition;
  //keeps track of the total number of elapsed ticks. For calculating specific points in time

  /**
   * Produces a Visual View according to the specified data: specifies the data of the animation
   * being drawn, the speed of the animation, the length of the animation, and the size of the
   * screen.
   * @param canvasPosition the top-left corner of the canvas of the animation.
   * @param canvasSize the size of the screen displaying the animation.
   * @throws IllegalArgumentException if any of the arguments are null or invalid.
   */
  public VisualView(Point canvasPosition, Dimension canvasSize) throws IllegalArgumentException {
    super();

    if (canvasPosition == null || canvasSize == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    //set the fields to their appropriate values
    this.canvasPosition = canvasPosition;
    //the current time keeps track of elapsed ticks

    this.setTitle("Animate!");
    this.setSize(canvasSize);
    this.setPreferredSize(canvasSize);
    this.setLocation(canvasPosition);
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Set the animation panel to fill the screen and add scroll panels to view whole animation
    animationPanel = new AnimationPanel();
    animationPanel.setSize(canvasSize);
    animationPanel.setPreferredSize(canvasSize);
    this.add(animationPanel);

    this.add(new JScrollPane(animationPanel), BorderLayout.SOUTH);

    this.add(new JScrollPane(animationPanel), BorderLayout.WEST);


  }

  @Override
  public void draw(List<ImmutableShape> shapes, int animationLength) {
    //set the shapes for the panel to draw.
    animationPanel.setShapes(shapes);
    //set the offset of the panel to draw the shapes in the proper location.
    animationPanel.setOffset(canvasPosition);
    //Show the animation (turns out if you don't show it it doesn't show)
    this.setVisible(true);

    refresh(0);

    //Although the Timer might be more appropriate in the controller, without knowing the
    //specifications of the Controller, we decided to leave the Timer out for now,
    //intending to move it by adding another method to the View interface (refresh) that the
    //Controller can always call on ticks.
  }

  @Override
  public void setSpeed(int speed) {
    //this view does not care about its own time. It draws frames, and the controller
    //will draw through each individual image
  }

  /**
   * Refreshes the animation and sets the states of the shapes within to the specified tick.
   * @param tick the tick describing the state of the animation to be drawn next.
   */
  @Override
  public void refresh(int tick) {
    animationPanel.setTime(tick);

    repaint();
  }

  /**
   * VisualView does not support this operation.
   * @param shapes a list of Immutable shapes for the View to animate.
   * @throws UnsupportedOperationException VisualView cannot update data.
   */
  @Override
  public void updateData(List<ImmutableShape> shapes) {
    throw new UnsupportedOperationException("VisualView cannot update data");
  }

  /**
   * VisualView does not support this operation.
   * @param action the listener that will execute the appropriate actions upon hearing from
   * @throws UnsupportedOperationException VisualView cannot set listeners.
   */
  @Override
  public void setListener(ActionListener action) {
    throw new UnsupportedOperationException("VisualView cannot set listeners");
  }

  /**
   * VisualView does not support this operation.
   * @param listener the listener(s) that describe certain behavior in Selection Lists in
   * @throws UnsupportedOperationException VisualView cannot set listeners.
   */
  @Override
  public void setListSelectionListener(Map<String, ListSelectionListener> listener) {
    throw new UnsupportedOperationException("VisualView cannot set listeners");
  }

  /**
   * VisualVew does not support this operation.
   * @param s the Shape whose keyframes are being highlighted.
   * @throws UnsupportedOperationException VisualView cannot set keyframes.
   */
  @Override
  public void setKeyframes(ImmutableShape s) {
    throw new UnsupportedOperationException("VisualView cannot set keyframes.");
  }

  /**
   * VisualView does not support this operation.
   * @return nothing.
   * @throws UnsupportedOperationException VisualView cannot select shapes.
   */
  @Override
  public ImmutableShape getSelectedShape() {
    throw new UnsupportedOperationException("VisualView cannot select shapes");
  }

  /**
   * VisualView does not support this operation.
   * @return nothing.
   * @throws UnsupportedOperationException VisualView cannot select keyframes.
   */
  @Override
  public Keyframe getSelectedKeyframe() {
    throw new UnsupportedOperationException("VisualView cannot select keyframes");
  }

  /**
   * VisualView does not support this operation.
   * @return nothing.
   * @throws UnsupportedOperationException VisualView cannot get shape declarations.
   */
  @Override
  public String getShapeDeclaration() {
    throw new UnsupportedOperationException("VisualView cannot get shape declarations");
  }

  /**
   * VisualView does not support this operation.
   * @return nothing.
   * @throws UnsupportedOperationException VisualView cannot get specified time.
   */
  @Override
  public String getSpecifiedTime() {
    throw new UnsupportedOperationException("VisualView cannot get specified time");
  }

  /**
   * VisualView does not support this operation.
   * @return nothing.
   * @throws UnsupportedOperationException VisualView cannot get new keyframe.
   */
  @Override
  public Keyframe getNewKeyframe() {
    throw new UnsupportedOperationException("VisualView cannot get new keyframe");
  }

  /**
   * VisualView does not support this operation.
   * @return nothing.
   * @throws UnsupportedOperationException VisualView cannot get specified file.
   */
  @Override
  public String getSpecifiedFile() {
    throw new UnsupportedOperationException("VisualView cannot get specified file");
  }

  /**
   * VisualView does not support this operation.
   * @param msg an error message to display.
   * @throws UnsupportedOperationException VisualView cannot display error messages.
   */
  @Override
  public void displayErrorMessage(String msg) {
    throw new UnsupportedOperationException("VisualView cannot display error messages");
  }

}
