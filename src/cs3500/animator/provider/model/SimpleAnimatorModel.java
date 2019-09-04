package cs3500.animator.provider.model;

import java.awt.Point;

import java.awt.Dimension;

import java.awt.Color;
import java.util.List;

import cs3500.animator.provider.adapter.ImmutableShape;
import cs3500.animator.model.Shape;
import cs3500.animator.model.Keyframe;

/**
 * A Model for a Simple Animation. Contains the information for all the Shapes in the animation and
 * all the movements that occur for each Shape.
 */
public interface SimpleAnimatorModel {

  /**
   * Adds a shape of specified name and type to the model. Examples of shape types include
   * 'rectangle' and 'ellipse'. See {@link Shape}.
   * @param shapeType the type of shape to construct (e.g. rectangle, ellipse).
   * @param name the name/unique ID of the specified shape.
   * @throws IllegalArgumentException if the arguments are invalid.
   */
  void addShape(String shapeType, String name) throws IllegalArgumentException;

  /**
   * Removes the specified shape from the model, assuming a shape with that name exists.
   * @param name the name of the unique shape being removed.
   * @throws IllegalArgumentException if the argument is invalid.
   */
  void removeShape(String name) throws IllegalArgumentException;

  //Exactly what removeMotion should look like depends on how it needs to be used.
  //This is a temporary implementation until further instruction on the use of removeMotion
  //is provided.
  /**
   * Remove a Motion object with the specified start and end times from a shape with the specified
   * name from the model, assuming the shape and the motion exists. See {@link Motion}.
   * @param name the unique name/ID of the shape.
   * @param startTime the start time of the Motion object being removed.
   * @param endTime the end time of the Motion object being removed.
   * @throws IllegalArgumentException if the arguments are invalid.
   */
  void removeMotion(String name, int startTime, int endTime) throws IllegalArgumentException;

  /**
   * Give a specified Shape instructions to move from the specified start location to the
   * specified end location at the provided start and end times.
   * @param name the name of the shape to be moved.
   * @param startTime the start time of the motion of the shape.
   * @param endTime the end time of the motion of the shape.
   * @param startPos the initial position of the Shape at the beginning of the motion.
   * @param endPos the ending position of the Shape at the end of the motion.
   * @param startSize the starting size of the Shape at the beginning of the motion.
   * @param endSize the ending size of the Shape at the beginning of the motion.
   * @param startColor the starting color of the shape at the beginning of the motion.
   * @param endColor the ending color of the shape at the beginning of the motion.
   * @throws IllegalArgumentException if any of the arguments are invalid.
   */
  void moveShape(String name, int startTime, int endTime, Point startPos, Point endPos,
      Dimension startSize, Dimension endSize, Color startColor, Color endColor)
      throws IllegalArgumentException;

  /**
   * Adds a keyframe to the animation of a specified Shape. Does this by calculating the
   * shape's position at a certain time and splitting a Motion to create a start and end state
   * at the specified time. See {@link Shape} and {@link Keyframe}.
   * @param name the name of the shape to which a keyframe is being added.
   * @param time the time of the keyframe being added to the shape.
   */
  void addKeyframe(String name, int time) throws IllegalArgumentException;

  /**
   * Removes a keyframe from the animation of a specified Shape. Does this by checking for
   * start/end states of Motions at the specified time. See {@link Shape} and {@link Keyframe}.
   * @param name the name of the Shape from which a keyframe is being removed.
   * @param time the time of the keyframe being removed.
   */
  void removeKeyframe(String name, int time);

  /**
   * Edits a keyframe in a specified Shape. Does this by checking for start/end states of Motions
   * at start/end states of Motions at the specified time. See {@link Shape}
   * and {@link Keyframe}.
   * @param name the name of the shape being edited.
   * @param time the time of the keyframe being edited.
   * @param pos the new Position of the shape at the keyframe being altered.
   * @param size the new Size of the shape at the keyframe being altered.
   * @param color the new Color of the shape at the keyframe being altered.
   */
  void editKeyframe(String name, int time, Point pos, Dimension size, Color color);


  /**
   * returns a list of the Shapes contained in the Model.
   * @return a list of {@link ImmutableShape} that the make up the Animation in this Model.
   *         Immutable so that the View and Controller cannot muck around with the model data.
   */
  List<ImmutableShape> getAnimation();

  /**
   * Returns a {@link Point} of the top-left corner of the canvas of the model.
   * @return the {@link Point} of the starting position of the canvas.
   */
  Point getCanvasPosition();

  /**
   * Returns a {@link Dimension} of the canvas of the model, width and height.
   * @return the {@link Dimension} that describes the width and height of the canvas.
   */
  Dimension getCanvasSize();

  /**
   * Return the total length of the animation of the Model.
   * @return the total length of the animation contained in this Model.
   */
  int getAnimationLength();

}
