package cs3500.animator.controller;

import java.io.IOException;

import cs3500.animator.model.ROAnimationModelImpl;

/**
 * A Controller to make any changes to a AnimationModel in response to an EditView.
 */
public interface AnimationController {

  /**
   * Adds a new Shape to the model.
   * @param name the name of the new shape
   * @param type the type of the new shape
   */
  void addShape(String name, String type);

  /**
   * Removes a Shape from the model.
   * @param shapeName the name of the shape to be removed
   */
  void removeShape(String shapeName);

  /**
   * Adds a new Keyframe to the model.
   * @param shapeName the name of the shape to add a keyframe to
   * @param t the time at which to add the new keyframe
   */
  void addKey(String shapeName, int t);

  /**
   * Saves changes made to an existing keyframe in the model.
   * @param shapeName name of the shape whose keyframe is being changed.
   * @param t time of the keyframe to be edited
   * @param x new x position
   * @param y new y position
   * @param w new width
   * @param h new height
   * @param r new red
   * @param g new green
   * @param b new blue
   */
  void saveKey(String shapeName, int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Removes a keyframe from the model.
   * @param shapeName the name of the shape whose keyframe is being removed.
   * @param t the time at which the keyframe is being removed.
   */
  void removeKey(String shapeName, int t);

  /**
   * Saves a file of the given model as either a .txt or .svg.
   * @param output the location to save to
   * @throws IOException if there is an issue with the given output location
   */
  void save(String output) throws IOException;

  /**
   * Loads a new model into the view from a given .txt file.
   * @param in the location to load from.
   * @throws IOException if there is an issue with the given file location
   */
  void load(String in) throws IOException;

  /**
   * Returns a read-only representation of the model used in the animation.
   * @return - a ROAnimationModel for use by classes that don't need to change the model.
   */
  ROAnimationModelImpl getModel();
}