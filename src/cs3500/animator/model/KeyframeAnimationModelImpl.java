package cs3500.animator.model;


/**
 * Represents a Model that uses Keyframes to represent important properties of an Animation.
 */
public class KeyframeAnimationModelImpl extends AnimationModelImpl implements
    KeyframeAnimationModel {

  public KeyframeAnimationModelImpl() {
    super();
  }

  @Override
  public void addTransformation(String name, Transformation t) {
    throw new UnsupportedOperationException("Can't add Transformation's to a Model "
        + "that uses Keyframes!");
  }

  @Override
  public void removeTransformation(String name, Transformation t) {
    throw new UnsupportedOperationException("Can't remove Transformation's to a Model "
        + "that uses Keyframes!");
  }

  @Override
  public void addKeyframe(String name, Keyframe key) {
    for (Shape s : this.shapes) {
      if (s.getName().equalsIgnoreCase(name)) {
        s.addKeyframe((KeyframeImpl) key);
      }
    }
  }

  @Override
  public void removeKeyframe(String name, int t) {
    Keyframe keyToRemove = null;

    for (Shape s : this.shapes) {
      if (s.getName().equalsIgnoreCase(name)) {
        for (Keyframe key : s.getKeyframes()) {
          if (key.getTime() == t) {
            keyToRemove = key;
          }
        }
      }
      s.removeKeyframe(keyToRemove);
    }
  }
}