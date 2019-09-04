package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ROAnimationModelImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.model.Transformation;
import java.io.IOException;

/**
 * Represents a cs3500.animator.view that outputs all motions and what each motion is changing for a
 * shape.
 */
public class TableTextViewImpl implements AnimationView {
  private ROAnimationModelImpl model;
  Appendable output;

  public TableTextViewImpl(AnimationModel model, Appendable output) {
    this.model = new ROAnimationModelImpl(model);
    this.output = output;
  }

  @Override
  public void display() {
    try {
      output.append("canvas ").append(
          Integer.toString(model.getCanvasX())).append(" ").append(
          Integer.toString(model.getCanvasY())).append(" ").append(
          Integer.toString(model.getCanvasW())).append(" ").append(
          Integer.toString(model.getCanvasH())).append("\n");

      for (Shape shape : model.getShapes()) {
        output.append("shape ").append(shape.getName()).append(" ")
            .append(shape.getType().toString()).append("\n");
        for (Transformation t : shape.getTransformations()) {
          output.append("motion ").append(shape.getName()).append(" ")
              .append(t.toString()).append("\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getSpeed() {
    return -1;
    //Returns -1 since this is a useless method for TableTextView
  }

  @Override
  public AnimationController getController() {
    throw new UnsupportedOperationException("TableTextView has no Controller");
  }
}