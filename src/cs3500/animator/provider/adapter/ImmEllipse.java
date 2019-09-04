package cs3500.animator.provider.adapter;

import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.provider.view.ViewVisitor;

/**
 * An Immutable Shape object that is used to differentiate each shapeType in the provider's code.
 * Used to call different methods in visitor classes.
 */
public class ImmEllipse extends ImmutableShape {

  /**
   * The constructor for ImmEllipse, taking in...
   *
   * @param s - the shape being taken in and adapted.
   * @throws IllegalArgumentException if parameters are null or the type given isn't supported
   */
  public ImmEllipse(Shape s) {
    super(s.getName(), s.getProperties(), s.getType().toString());
    if (s.getType() == ShapeType.RECTANGLE) {
      throw new IllegalArgumentException("Can't have a Rectangle be an ImmEllipse!");
    }
  }

  @Override
  public String accept(ViewVisitor visitor) {
    return visitor.visitEllipse(this);
  }
}
