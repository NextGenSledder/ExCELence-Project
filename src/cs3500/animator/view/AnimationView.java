package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;

public interface AnimationView {

  /**
   * Generates a viewable interface.
   */
  void display();

  /**
   * Returns the speed of this Animation.
   * @return an int representing the speed of this animation
   */
  int getSpeed();

  /**
   * Returns the controller that is controlling this view.
   * @return - An AnimationController that contains the model being used for the animation.
   */
  AnimationController getController();
}
