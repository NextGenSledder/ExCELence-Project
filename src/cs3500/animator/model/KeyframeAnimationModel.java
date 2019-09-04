package cs3500.animator.model;

public interface KeyframeAnimationModel extends AnimationModel {

  /**
   * Adds the given Keyframe from a Shape in this Model's list of shapes with the given name.
   * @param name - the name of the shape the keyframe will be added to.
   * @param key - the Keyframe to be added.
   */
  void addKeyframe(String name, Keyframe key);

  /**
   * Removes the given Keyframe from a Shape in this Model's list of shapes with the given name.
   * @param name - the name of the shape the keyframe will be added to.
   * @param t - the time of the Keyframe to be removed
   */
  void removeKeyframe(String name, int t);
}