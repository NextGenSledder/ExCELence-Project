package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.AnimationControllerImpl;
import cs3500.animator.model.AnimationModel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * An AnimationView implementation that shows the animation while allowing the user to make edits.
 * The user can add / remove shapes AND keyframes of shapes, can alter animation speed, or make the
 * animation loop.
 */
public class EditView extends JFrame implements AnimationView {
  private GraphicsViewPanel graphics;
  private AnimationController ctrl;

  /**
   * Constructor for the editView, contains a model to adapt and a framerate to set the speed.
   * @param model - the model to be viewed.
   * @param frameRate - the speed of the animation in the view
   */
  public EditView(AnimationModel model, int frameRate) {
    this.ctrl = new AnimationControllerImpl(model, this);

    graphics = new GraphicsViewPanel(this, frameRate);

    this.add(graphics, BorderLayout.SOUTH);

    JPanel editsAndButtons = new JPanel();

    ButtonPanel buttons = new ButtonPanel(graphics, model.getMaxT());
    editsAndButtons.add(buttons, BorderLayout.EAST);

    EditPanel edits = new EditPanel(ctrl);
    editsAndButtons.add(edits, BorderLayout.WEST);

    JScrollPane scrollBar = new JScrollPane(graphics);
    scrollBar.setBounds(model.getCanvasX(), model.getCanvasY(),
            model.getCanvasW(), model.getCanvasH());

    this.add(editsAndButtons, BorderLayout.NORTH);
    this.add(scrollBar);

    this.setBackground(Color.BLACK);
    this.setBounds(0,0, (model.getCanvasW() * 2) + 125, model.getCanvasH() * 2);

    this.setVisible(true);
  }

  @Override
  public void display() {
    this.graphics.startTimer();
  }

  /**
   * Returns the speed of this animation by looking into the GraphicsViewPanel.
   * @return - an int representing the speed of the animation
   */
  public int getSpeed() {
    return this.graphics.getFrameRate();
  }

  @Override
  public AnimationController getController() {
    return this.ctrl;
  }
}
