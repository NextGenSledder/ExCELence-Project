package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Listener that makes changes to the GraphicsViewPanel playing the animation.
 * ---> the only changes that are made have to do with the timer and looping-ability
 */
public class ViewListener implements ActionListener {
  private GraphicsViewPanel panel;

  /**
   * Constructor for the Listener.
   * @param p - The GraphicsPanel that is altered by the listener.
   */
  public ViewListener(GraphicsViewPanel p) {
    this.panel = p;
  }

  /**
   * Occurs whenever the user wants to pause, resume, restart the animation.
   * (or change its ability to loop)
   * @param e - the command to be used.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "play":
        this.panel.startTimer();
        break;
      case "pause":
        this.panel.getTimer().stop();
        break;
      case "restart":
        this.panel.setCurrentFrame(0);
        this.panel.startTimer();
        break;
      case "setloop":
        this.panel.setCanLoop();
        break;
      case "advance":
        this.panel.advanceModel();
        break;
      case "increase":
        this.panel.setFrameRate(panel.getFrameRate() + 1);
        break;
      case "decrease":
        this.panel.setFrameRate(panel.getFrameRate() - 1);
        break;
      default:
    }
  }
}
