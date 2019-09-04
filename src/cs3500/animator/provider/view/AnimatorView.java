package cs3500.animator.provider.view;

import cs3500.animator.provider.adapter.ImmutableShape;
import cs3500.animator.provider.model.Keyframe;

import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * an interface for the View of an Animation. Allows the user of this View to draw the animation
 * based on the rules and specifications provided to the View in its creation.
 */
public interface AnimatorView {

  /**
   * Produces an animation of the specified shapes and of the specified length.
   * @param shapes the immutable shapes to be drawn.
   * @param animationLength the length of the animation being drawn.
   */
  void draw(List<ImmutableShape> shapes, int animationLength);

  /**
   * Set the speed that the animation in the View will move at.
   * @param speed the speed of the animation.
   */
  void setSpeed(int speed);

  /**
   * Refresh the state of the animation at a specified tick.
   * @param tick the tick describing the state of the animation to be drawn next.
   */
  void refresh(int tick);

  /**
   * Update the data and contents of the animation, usually after some external source has
   * manipulated or mutated the model.
   * @param shapes a list of Immutable shapes for the View to animate.
   */
  void updateData(List<ImmutableShape> shapes);

  /**
   * Set the event handlers in the View to the specified ActionListener to behave as specified
   * by the action listener. Not all Views support Listeners and event handling, and may
   * throw an UnsupportedOperationException.
   * @param action the listener that will execute the appropriate actions upon hearing from
   *               an event.
   */
  void setListener(ActionListener action);

  /**
   * Set the List handlers in the View to the specified ListSelectionListeners to behave as
   * specified by the listener. One item in the map is applied for the key "shape",
   * and one is applied for the key "keyframe".Not all Views support Listeners, Selection Lists,
   * and event handling,and may throw an UnsupportedOperationException.
   * @param listener the listener(s) that describe certain behavior in Selection Lists in
   *                 the View.
   */
  void setListSelectionListener(Map<String,ListSelectionListener> listener);

  /**
   * Set the Keyframes keyframes that are to be highlighted for selection and interaction
   * by the user. Not all Views support setting keyframes and Selection Lists,
   * and may throw an UnsupportedOperationException.
   * @param s the Shape whose keyframes are being highlighted.
   */
  void setKeyframes(ImmutableShape s);

  /**
   * Retrieve the Shape selected by a user in the View. Not all Views support selecting and getting
   * shapes or Selection Lists generally, and may throw an UnsupportedOperationException.
   * @return the Immutable Shape selected by the user in the View.
   */
  ImmutableShape getSelectedShape();

  /**
   * Retrieve the Keyframe selected by a user in the View. Not all Views support selecting and
   * getting keyframes, or Selection Lists generally, and may throw an
   * UnsupportedOperationException.
   * @return the keyframe selected by the user in the View.
   */
  Keyframe getSelectedKeyframe();

  /**
   * Retrieve a declaration of a Shape, its type and name, from the user in the View. Not all
   * Views support returning user input or event handling in general, and may throw
   * an UnsupportedOperationException.
   * @return a String that represents a user's input of the declaration of a Shape.
   */
  String getShapeDeclaration();

  /**
   * Retrieve a String that should contain a number representing a specific time in the animation.
   * Not all Views support returning user input or handling user input in general,
   * and may throw an UnsupportedOperationException.
   * @return a String that contains a user's input specifying a time in the animation.
   */
  String getSpecifiedTime();

  /**
   * Retrieve a keyframe comprised of a selected keyframe and user input made to alter it.
   * Not all Views support returning user input or handling user input in general, and may
   * throw an UnsupportedOperationException.
   * @return a keyframe containing user-specified data.
   */
  Keyframe getNewKeyframe();

  /**
   * Retrieves the name of a file specified by the user. Not all Views support returning user input
   * or handling user input in general, and may throw an UnsupportedOperationException.
   * @return a String containing the name of a file inputted by the user.
   */
  String getSpecifiedFile();

  /**
   * Use the View to display an error message, typically regarding incorrect user input. Not all
   * Views support returning user input or handling user input in general, and may
   * throw an UnsupportedOperationException.
   * @param msg an error message to display.
   */
  void displayErrorMessage(String msg);
}
