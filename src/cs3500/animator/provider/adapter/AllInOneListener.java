package cs3500.animator.provider.adapter;

import cs3500.animator.model.AShape;
import cs3500.animator.model.PropImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * A general-purpose listener object for the provider's view. Not divided into multiple classes
 * since the Provider's EditableView has a setListener method that takes in ONE listener, not
 * multiple.
 */
public class AllInOneListener implements ActionListener, ListSelectionListener {

  private NewEditableView view;
  private Timer timer;
  private int currentFrame = 1;
  private boolean loops = false;
  private ProviderController ctrl;
  private int speed;

  AllInOneListener(NewEditableView view, ProviderController ctrl, int startSpeed) {
    this.view = view;
    if (startSpeed <= 0) {
      throw new IllegalArgumentException("speed can't be zero!");
    }
    this.timer = new Timer(1000 / startSpeed, this);
    this.timer.setActionCommand("tick");
    this.ctrl = ctrl;
    this.speed = startSpeed;
  }

  /**
   * Invoked when an action occurs.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      switch (e.getActionCommand()) {
        case "tick":
          currentFrame++;
          this.ctrl.advanceToTime(currentFrame);
          if (this.ctrl.model.getAnimationLength() <= currentFrame) {
            this.timer.stop();
          }
          if (this.ctrl.model.getAnimationLength() <= currentFrame && this.loops) {
            this.currentFrame = 1;
            this.timer.start();
          }

          break;
        case "start":
          this.timer.start();
          break;
        case "pause":
          this.timer.stop();
          break;
        case "restart":
          this.currentFrame = 1;
          break;
        case "loop":
          this.loops = !this.loops;
          break;
        case "increment":
          this.speed++;
          this.timer.setDelay(1000 / this.speed);
          break;
        case "decrement":
          this.speed--;
          this.timer.setDelay(1000 / this.speed);
          break;
        case ("add shape"):
          this.ctrl.addShapeToModel();
          break;
        case ("remove shape"):
          this.ctrl.removeShapeFromModel();
          try {
            this.view.setKeyframes(
                new ImmRectangle(new AShape(
                    "", new PropImpl(0, 0, 0, 0, 0, 0, 0), "rectangle")));
          } catch (NullPointerException error) {
            // intentionally left blank: this is basically a hacky way to clear our provider's list
          }
          break;
        case ("add keyframe"):
          this.ctrl.addKeyframeToModel();
          break;
        case ("edit keyframe"):
          this.ctrl.editKeyframe();
          break;
        case ("remove keyframe"):
          this.ctrl.removeKeyframeFromModel();
          break;
        case ("save animation"):
          //didn't implement because it is extra-credit that is not counted for this hw
          break;
        case ("load animation"):
          //didn't implement because it is extra-credit that is not counted for this hw
          break;
        default:
          throw new UnsupportedOperationException("Unexpected edit command");
      }
      this.ctrl.advanceToTime(currentFrame);
    } catch (Throwable error) {
      view.displayErrorMessage(error.getMessage());
      error.printStackTrace();
    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    try {
      JList source = (JList) e.getSource();

      if (source.getSelectedValue() != null) {
        ImmutableShape item = (ImmutableShape) source.getSelectedValue();

        this.timer.stop();
        this.view.setKeyframes(item);
        this.view.refresh(this.currentFrame);
      }
    } catch (Throwable error) {
      view.displayErrorMessage(error.getMessage());
      error.printStackTrace();
    }
  }
}
