package cs3500.animator.provider.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.event.ListSelectionListener;

import cs3500.animator.provider.controller.AnimatorController;
import cs3500.animator.provider.model.Keyframe;
import cs3500.animator.provider.model.SimpleAnimatorModel;

/**
 * A Controller class that implement's the provider's Controller interface. takes in our adapted
 * model and view classes.
 */
public class ProviderController implements AnimatorController {

  private NewEditableView view;
  SimpleAnimatorModel model;

  ProviderController(SimpleAnimatorModel model, NewEditableView view) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void animate() {
    AllInOneListener listener = new AllInOneListener(this.view, this, this.view.getSpeed());
    this.view.setListener(listener);

    Map<String, ListSelectionListener> selectionMap = new HashMap<>();
    selectionMap.put("shape", listener);
    // intentionally didn't add a "keyframe" listener, since clicking a keyframe from the list
    // shouldn't change anything.
    this.view.setListSelectionListener(selectionMap);
    this.view.draw(this.model.getAnimation(), this.model.getAnimationLength());
  }

  @Override
  public void setSpeed(int speed) {
    // intentionally left blank since the controller doesn't store the speed in this implementation.
  }

  /**
   * Advances the model to the current frame, and draws it in the view.
   *
   * @param currentFrame - the frame to be advanced to.
   */
  void advanceToTime(int currentFrame) {
    this.view.updateData(this.model.getAnimation());
    this.view.refresh(currentFrame);
  }

  /**
   * Adds a new shape with the type and name received from the view text area.
   */
  void addShapeToModel() {
    try {
      Scanner scanner = new Scanner(this.view.getShapeDeclaration());
      scanner.useDelimiter(" ");

      String type = scanner.next();

      if (!(type.equalsIgnoreCase("rectangle")
          || type.equalsIgnoreCase("ellipse"))) {
        this.view.displayErrorMessage("invalid shape type " + type);
        return;
      }

      String name = scanner.next();

      for (ImmutableShape s : this.model.getAnimation()) {
        if (name.equalsIgnoreCase(s.getName())) {
          this.view.displayErrorMessage("can't have two shapes with the same name");
          return;
        }
      }

      System.out.println("type = " + type + "  name = " + name);

      this.model.addShape(type, name);
      this.view.updateData(this.model.getAnimation());

    } catch (Throwable t) {
      this.view.displayErrorMessage("Invalid input, type in TYPE NAME");
    }
  }

  /**
   * Removes the selected shape from the model.
   */
  void removeShapeFromModel() {
    if (this.view.getSelectedShape() == null) {
      this.view.displayErrorMessage("Must select a shape before removing it!");
    } else {
      this.model.removeShape(this.view.getSelectedShape().getName());
      this.view.updateData(this.model.getAnimation());
    }
  }

  /**
   * Adds a keyframe to the selected shape at the given time.
   */
  void addKeyframeToModel() {
    try {
      int time = Integer.parseInt(this.view.getSpecifiedTime());
      if (time <= 0) {
        throw new IllegalArgumentException("Need a positive int for time");
      }
      this.model.addKeyframe(this.view.getSelectedShape().getName(), time);
      this.view.updateData(this.model.getAnimation());
    } catch (IllegalArgumentException e) {
      this.view.displayErrorMessage("need a positive integer for time to add a keyframe");
    }
  }

  /**
   * Removes the selected keyframe from the selected shape.
   */
  void removeKeyframeFromModel() {
    if (this.view.getSelectedKeyframe() == null) {
      this.view.displayErrorMessage("Must select a keyframe before removing it");
    } else {
      if (this.view.getSelectedShape() == null) {
        this.view.displayErrorMessage("Keyframe is part of a shape that no longer exists");
      } else {
        this.model.removeKeyframe(this.view.getSelectedShape().getName(),
            this.view.getSelectedKeyframe().getTime());
        this.view.updateData(this.model.getAnimation());
      }
    }
  }

  /**
   * Edits the selected Keyframe of the view to have the new properties specified in text area.
   */
  void editKeyframe() {
    if (this.view.getSelectedShape() == null) {
      this.view.displayErrorMessage(
          "Selected Keyframe is part of a shape that no longer exists");
    } else {
      if (this.view.getSelectedKeyframe() == null) {
        this.view.displayErrorMessage("No keyframe selected");
      } else {
        int time = this.view.getSelectedKeyframe().getTime();
        Keyframe k = this.view.getNewKeyframe();
        this.model.removeKeyframe(this.view.getSelectedShape().getName(), time);
        this.model.editKeyframe(this.view.getSelectedShape().getName(), k.getTime(), k.getPos(),
            k.getSize(), k.getColor());
      }
    }
  }
}
