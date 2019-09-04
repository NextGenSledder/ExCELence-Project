package cs3500.animator.provider.adapter;

import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.provider.view.ViewVisitor;

/**
 * An immutable Shape object that is used for visitors with the provider code.
 */
public class ImmRectangle extends ImmutableShape {
  /**
   * The constructor for ImmRectangle, taking in...
   *
   * @param s - the shape being taken in and adapted to the provider's code
   * @throws IllegalArgumentException if parameters are null or the type given isn't supported
   */
  public ImmRectangle(Shape s) {
    super(s.getName(), s.getProperties(), s.getType().toString());
    if (s.getType() == ShapeType.ELLIPSE) {
      throw new IllegalArgumentException("Can't have a Rectangle be an ImmEllipse!");
    }
  }

  @Override
  public String accept(ViewVisitor visitor) {
    return visitor.visitRect(this);
  }
}
