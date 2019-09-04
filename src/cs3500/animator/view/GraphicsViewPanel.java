package cs3500.animator.view;

import cs3500.animator.model.Property;
import cs3500.animator.model.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Represents a Panel containing every Shape in our animation.
 */
public class GraphicsViewPanel extends JPanel {

  private AnimationView view;
  private int currentFrame = 0;
  private boolean canLoop = false;
  private Timer timer;
  private int frameRate;

  /**
   * Constructor for the GraphicsViewPanel.
   *
   * @param view - the controller with the model inside it, created by view classes that use this.
   * @param frameRate - the speed at which the animation will play
   */
  public GraphicsViewPanel(AnimationView view, int frameRate) {
    super();
    if (frameRate < 1) {
      throw new IllegalArgumentException("Can't have a framerate less than 1!");
    }
    this.setBackground(Color.WHITE);

    this.view = view;

    this.setBounds(this.view.getController().getModel().getCanvasX(),
        this.view.getController().getModel().getCanvasY(),
        this.view.getController().getModel().getCanvasW(),
        this.view.getController().getModel().getCanvasH());
    this.timer = new Timer(1000 / frameRate, new ViewListener(this));
    this.frameRate = frameRate;

    this.timer.setActionCommand("advance");
    this.setVisible(true);
  }

  /**
   * Starts the timer, therefore playing the animation.
   */
  void startTimer() {
    this.timer.start();
  }

  /**
   * Returns the Timer used by this panel to be used in listener classes.
   *
   * @return - the Timer used by this panel to allow listeners to manipulate it.
   */
  Timer getTimer() {
    return this.timer;
  }

  /**
   * Returns the speed that the animation is playing at.
   *
   * @return - and int representing the speed of the animation
   */
  int getFrameRate() {
    return this.frameRate;
  }

  /**
   * Sets the current frame of the animation. --> currentFrame is the time that the model is
   * advanced to every tick
   *
   * @param c - an int representing the frame that the model is at.
   */
  void setCurrentFrame(int c) {
    this.currentFrame = c;
  }

  /**
   * Reverses the boolean that determines whether the animation can loop or not.
   */
  void setCanLoop() {
    this.canLoop = !this.canLoop;
  }

  /**
   * Sets the speed of the animation to the given int.
   *
   * @param f - an int representing the new speed of the animation (provided that it is valid)
   */
  void setFrameRate(int f) {
    if ((this.frameRate > 1) && f < this.frameRate) {
      this.frameRate = f;
      this.timer.setDelay(1000 / f);
      this.startTimer();
    }
  }

  /**
   * Returns the current frame of the animation.
   * @return - an int representing the current frame of the animation.
   */
  int getCurrentFrame() {
    return this.currentFrame;
  }

  /**
   * Advances the model, and therefore every shape, to this.currentFrame, and may loop.
   * this.currentFrame increments at every call
   */
  void advanceModel() {
    this.currentFrame++;
    this.view.getController().getModel().advance(currentFrame);
    if (this.currentFrame >= this.view.getController().getModel().getMaxT()) {
      timer.stop();
    }
    if (this.canLoop && this.currentFrame >= this.view.getController().getModel().getMaxT()) {
      this.currentFrame = 0;
      this.startTimer();
    }
    this.repaint();
  }

  /**
   * Paints every shape onto the Panel according to their properties.
   *
   * @param g - The Graphics object being painted.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    for (Shape shape : this.view.getController().getModel().getShapes()) {
      Property props = shape.getProperties();
      g2d.setColor(new Color(props.getColorR(), props.getColorG(), props.getColorB()));
      switch (shape.getType()) {
        case RECTANGLE:
          g2d.fillRect(props.getPosx(), props.getPosy(), props.getWidth(), props.getHeight());
          break;
        case ELLIPSE:
          g2d.fillOval(props.getPosx(), props.getPosy(), props.getWidth(), props.getHeight());
          break;
        default:
          break;
      }
    }
  }
}
