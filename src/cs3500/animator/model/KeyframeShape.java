package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyframeShape extends AShape {

  private List<KeyframeImpl> keyframes;

  /**
   * The constructor for KeyframeShape, which calls super.
   *
   * @param name - the name of this shape
   * @param startProperty - the properties of this shape
   * @param type - the type of shape
   * @throws IllegalArgumentException if parameters are null or the type given isn't supported
   */
  public KeyframeShape(String name, Property startProperty, String type) {
    super(name, startProperty, type);
    this.keyframes = new ArrayList<>();
  }

  @Override
  public List<Transformation> getTransformations() {
    List<Transformation> transformations = new ArrayList<>();
    for (int i = 0; i < this.keyframes.size() - 1; i++) {
      transformations.add(new ATransformation(this.keyframes.get(i).getProperty(),
              this.keyframes.get(i + 1).getProperty(),
              this.keyframes.get(i).getTime(), this.keyframes.get(i + 1).getTime()));
    }
    return transformations;
  }


  @Override
  public void addTransformation(Transformation t) {
    throw new UnsupportedOperationException("KeyframeShapes cannot add Transformations!");
  }

  @Override
  public void removeTransformation(Transformation t) {
    throw new UnsupportedOperationException("KeyframeShapes cannot remove Transformations!");
  }

  @Override
  public void advance(int t) throws IllegalArgumentException {
    if (t < 0) {
      throw new IllegalArgumentException("time given is out of range");
    }
    if (this.keyframes.size() == 0) {
      return;
      //throw new IllegalArgumentException("this shape has no keyframes");
    }

    for (Keyframe key : this.keyframes) {
      if (key.getTime() == t) {
        this.properties = key.getProperty();
      }
    }
    if (t > this.getEndT()) {
      for (Keyframe key : this.keyframes) {
        if (key.getTime() == this.getEndT()) {
          this.properties = key.getProperty();
        }
      }
    } else if (t < this.getStartT()) {
      this.properties = new PropImpl(0,0,0,0,0,0,0);
    } else {
      Keyframe prevKey = this.keyframes.get(0);
      Keyframe nextKey = this.keyframes.get(this.keyframes.size() - 1);

      for (Keyframe key : this.keyframes) {
        if (key.getTime() > prevKey.getTime() && key.getTime() < t) {
          prevKey = key;
        }
        if (key.getTime() < nextKey.getTime() && key.getTime() > t) {
          nextKey = key;
        }
      }
      Transformation trans = new ATransformation(prevKey.getProperty(), nextKey.getProperty(),
              prevKey.getTime(), nextKey.getTime());
      this.properties = trans.apply(t);
    }
  }

  /**
   * Returns the time of the earliest keyframe in this Shape.
   * @return the earliest time
   */
  private int getStartT() {
    int startT = this.getEndT();
    for (Keyframe k : this.keyframes) {
      if (k.getTime() < startT) {
        startT = k.getTime();
      }
    }
    return startT;
  }

  @Override
  public int getEndT() {
    int endT = 0;
    for (Keyframe k : this.keyframes) {
      if (k.getTime() > endT) {
        endT = k.getTime();
      }
    }
    return endT;
  }

  @Override
  public void addKeyframe(KeyframeImpl k) {
    for (int i = 0; i < this.keyframes.size(); i++) {
      if (this.keyframes.get(i).getTime() == k.getTime()) {
        this.keyframes.remove(this.keyframes.get(i));
      }
    }
    this.keyframes.add(k);
    Collections.sort(this.keyframes);
  }

  @Override
  public void removeKeyframe(Keyframe k) {
    this.keyframes.remove(k);
  }

  @Override
  public List<KeyframeImpl> getKeyframes() {
    return this.keyframes;
  }
}