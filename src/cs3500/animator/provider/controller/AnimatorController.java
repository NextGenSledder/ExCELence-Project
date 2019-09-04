package cs3500.animator.provider.controller;

/**
 * The interface representing a controller for an ExCELlence animation.
 * Provides instructions to a view for drawing animations and to models for
 * commands and instructions provided by the user.
 */
public interface AnimatorController {

  /**
   * Produce the animation. Begins the functionality of the controller, at which point the
   * controller takes over.
   */
  void animate();

  /**
   * Set the speed that the Controller will be playing the animation at.
   * @param speed the desired speed of the animation within the Controller.
   */
  void setSpeed(int speed);
}
