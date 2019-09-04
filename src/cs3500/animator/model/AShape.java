package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic class for a Shape. INVARIANT: no null fields. INVARIANT:
 * this.transformations has no Transformations that overlap in time. (this is still unsupported
 * w/out combineTransformations) INVARIANT: this.transformations is either empty or has
 * Transformations without gaps in the time between them.
 */
public class AShape implements Shape {

  private List<Transformation> transformations;
  protected Property properties;
  protected String name;
  protected ShapeType type;

  /**
   * The constructor for AShape, taking in...
   *
   * @param name - the name of this shape
   * @param startProperty - the properties of this shape
   * @param type - the type of shape
   * @throws IllegalArgumentException if parameters are null or the type given isn't supported
   */
  public AShape(String name, Property startProperty, String type) {
    if (name == null || startProperty == null || type == null) {
      throw new IllegalArgumentException("No null types for AShape");
    }
    this.name = name;
    this.properties = startProperty;
    this.transformations = new ArrayList<>();

    type = type.toLowerCase();

    switch (type) {
      case "rectangle":
        this.type = ShapeType.RECTANGLE;
        break;
      case "ellipse":
        this.type = ShapeType.ELLIPSE;
        break;
      default:
        throw new IllegalArgumentException("Unsupported shape type given");
    }
  }

  @Override
  public List<Transformation> getTransformations() {
    return this.transformations;
  }

  @Override
  public Property getProperties() {
    return this.properties;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public void addTransformation(Transformation newTrans) {
    if (this.transformations.size() == 0) {
      if (newTrans.getStartT() != 0) {
        Property invisible = new PropImpl(0, 0, 0, 0, 0, 0, 0);
        Transformation nonexistent = new ATransformation(invisible, invisible,
            0, newTrans.getStartT() - 1);
        Transformation teleport = new ATransformation(invisible, newTrans.getStartState(),
            newTrans.getStartT() - 1, newTrans.getStartT());
        this.transformations.add(nonexistent);
        this.transformations.add(teleport);
      }
      this.transformations.add(newTrans);
    } else {
      Transformation lastTrans = this.transformations.get(this.transformations.size() - 1);
      if (!(lastTrans.getEndState().equals(newTrans.getStartState()))) {
        System.out.println(lastTrans.toString());
        System.out.println(newTrans.toString());
        throw new IllegalArgumentException(
            "new Transformation's start does not agree with our current ends");
      }

      if (lastTrans.getEndT() == newTrans.getStartT()) {
        this.transformations.add(newTrans);
      } else if (lastTrans.getEndT() < newTrans.getStartT()) {
        Transformation doesNothing = new ATransformation(lastTrans.getEndState(),
            newTrans.getStartState(), lastTrans.getEndT(), newTrans.getStartT());
        this.transformations.add(doesNothing);
        this.transformations.add(newTrans);
      }
    }
  }

  @Override
  public String toString() {
    return this.name + " " + this.properties.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Property)) {
      return false;
    }

    Shape shape = (Shape) obj;
    return this.transformations.equals(shape.getTransformations())
        && this.properties.equals(shape.getProperties())
        && this.name.equals(shape.getName())
        && this.type.equals(shape.getType());
  }

  @Override
  public int hashCode() {
    return (int) (Math.random() * 100);
  }

  @Override
  public void advance(int t) {
    if (t < 0) {
      throw new IllegalArgumentException("Can't advance to a negative time!");
    }
    boolean inRange = false;
    for (Transformation trans : this.transformations) {
      if (t >= trans.getStartT() && t < trans.getEndT()) {
        inRange = true;
        this.setProperty(trans.apply(t));
      }
    }
    int bestEndTime = 0;
    if (!inRange) {
      for (Transformation trans : this.transformations) {
        if (trans.getEndT() >= bestEndTime && trans.getEndT() <= t) {
          bestEndTime = trans.getEndT();
        }
      }
      for (Transformation trans : this.transformations) {
        if (trans.getEndT() == bestEndTime) {
          this.setProperty(trans.getEndState());
        }
      }
    }
  }

  @Override
  public int getEndT() {
    int endT = 0;
    for (Transformation t : this.transformations) {
      if (t.getEndT() > endT) {
        endT = t.getEndT();
      }
    }
    return endT;
  }

  @Override
  public void removeTransformation(Transformation t) {
    this.transformations.remove(t);
  }

  @Override
  public void addKeyframe(KeyframeImpl k) {
    throw new UnsupportedOperationException("Can't add keyframes to a Shape that "
        + "uses Transformations!");
  }

  @Override
  public void removeKeyframe(Keyframe k) {
    throw new UnsupportedOperationException("Can't remove keyframes to a Shape that "
        + "uses Transformations!");
  }

  @Override
  public List<KeyframeImpl> getKeyframes() {
    List<KeyframeImpl> keyframes = new ArrayList<>();
    for (int i = 0; i < this.transformations.size(); i++) {
      if (i == 0) {
        keyframes.add(new KeyframeImpl(this.transformations.get(i).getStartState(),
            this.transformations.get(i).getStartT()));
      }
      keyframes.add(new KeyframeImpl(this.transformations.get(i).getEndState(),
          this.transformations.get(i).getEndT()));
    }
    return keyframes;
  }

  /**
   * Sets this Property to be the given property.
   *
   * @param prop the new PropImpl to assign
   */
  private void setProperty(Property prop) {
    this.properties = prop;
  }
}