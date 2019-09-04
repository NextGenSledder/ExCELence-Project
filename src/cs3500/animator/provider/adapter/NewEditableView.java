package cs3500.animator.provider.adapter;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.provider.controller.AnimatorController;
import cs3500.animator.provider.view.EditableView;
import cs3500.animator.view.AnimationView;
import java.awt.Dimension;
import java.awt.Point;

public class NewEditableView extends EditableView implements AnimationView {
  private int speed;
  private AnimatorController ctrl;

  /**
   * Create an instance of an EditableView object.
   */
  public NewEditableView(AnimationModel model, int speed) {
    super(new Point(model.getCanvasX(), model.getCanvasY()),
        new Dimension(model.getCanvasW(), model.getCanvasH()));
    this.speed = speed;

    this.ctrl = new ProviderController(new SimpleAdapterModel(model), this);
  }

  @Override
  public void display() {
    this.ctrl.animate();
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public AnimationController getController() {
    return null;
  }
}
