package cs3500.animator.controller;

import cs3500.animator.view.EditPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


/**
 * An ActionListener for any actions which might want to make changes to the model.
 */
public class EditListener implements ActionListener {

  private final EditPanel speaker;
  private final AnimationController ctrl;

  /**
   * Creates a EditListener who listens to components from a given panel and calls on a given
   * controller.
   *
   * @param speaker the JPanel which sets this as its listener
   * @param ctrl the controller to pass commands to
   */
  public EditListener(EditPanel speaker, AnimationController ctrl) {
    this.speaker = speaker;
    this.ctrl = ctrl;
  }

  /**
   * Invoked when an action occurs (when the user wants to add / remove a shape / keyframe. --> or
   * when the user wants to save the animation to a new file or load in a new one.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      int t = -1;
      String shapeName = "";
      String cmd = e.getActionCommand();
      if (speaker.getSelectedShape() != null) {
        shapeName = speaker.getSelectedShape().getName();
      }

      if (speaker.getSelectedKey() != null) {
        t = speaker.getSelectedKey().getTime();
      }

      switch (cmd) {
        case ("add shape"):
          String newShapeName = speaker.getNewShapeName();
          String newShapeType = speaker.getNewShapeType();
          ctrl.addShape(newShapeName, newShapeType);
          this.speaker.updateShapeDropdown();
          break;
        case ("remove shape"):
          ctrl.removeShape(shapeName);
          this.speaker.updateShapeDropdown();
          break;
        case ("add keyframe"):
          int newT = speaker.getNewTime();
          ctrl.addKey(shapeName, newT);
          this.speaker.updateKeyDropdown();
          break;
        case ("save keyframe"):
          int x = speaker.getChangeX();
          int y = speaker.getChangeY();
          int w = speaker.getChangeW();
          int h = speaker.getChangeH();
          int r = speaker.getChangeR();
          int g = speaker.getChangeG();
          int b = speaker.getChangeB();
          ctrl.saveKey(shapeName, t, x, y, w, h, r, g, b);
          this.speaker.updateKeyDropdown();
          break;
        case ("remove keyframe"):
          ctrl.removeKey(shapeName, t);
          this.speaker.updateKeyDropdown();
          break;
        case ("save"):
          String out = speaker.getOutput();
          ctrl.save(out);
          break;
        case ("load"):
          String in = speaker.getInput();
          ctrl.load(in);
          break;
        default:
          throw new UnsupportedOperationException("Unexpected edit command");
      }
    } catch (Throwable error) {
      JOptionPane.showMessageDialog(speaker,
          error.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}