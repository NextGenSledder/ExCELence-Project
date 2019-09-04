package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.AnimationModel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Represents an animation view that displays the animation into a JFrame.
 */
public class GraphicView extends JFrame implements AnimationView {
  private GraphicsViewPanel p;
  private AnimationController ctrl;

  /**
   * Constructs the graphical view.
   * @param model the model to be rendered.
   * @param frameRate the speed at which to play the animation
   */
  public GraphicView(AnimationModel model, int frameRate) {
    super();
    if (frameRate < 1) {
      throw new IllegalArgumentException("Can't have a framerate less than 1!");
    }
    this.setTitle("ExCELlence");
    this.setBounds(0, 0, model.getCanvasW(), model.getCanvasH());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    p = new GraphicsViewPanel(this, frameRate);
    this.add(p, BorderLayout.CENTER);

    JScrollPane scrollBar = new JScrollPane(p);
    scrollBar.setBounds(model.getCanvasX(), model.getCanvasY(),
            model.getCanvasW(), model.getCanvasH());

    this.add(scrollBar);
    this.setVisible(true);
  }

  /**
   * When display is called, a the Timer object starts, allowing repaint to be called.
   */
  @Override
  public void display() {
    this.p.startTimer();
  }

  @Override
  public int getSpeed() {
    return this.p.getFrameRate();
  }

  @Override
  public AnimationController getController() {
    return this.ctrl;
  }
}