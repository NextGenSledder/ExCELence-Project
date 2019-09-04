package cs3500.animator.provider.view;

import cs3500.animator.provider.adapter.ImmutableShape;

/**
 * A Visitor that draws the specified shape according to the type of Visitor. Follows the
 * Visitor design pattern.
 */
public interface ViewVisitor {
  //The purpose of this class is to decouple shapes from the animations produced to represent them.
  //A Shape should not draw itself because that tightly couples the Model and the View.
  //Producing the animation in the View itself would require using 'this instanceof Rectangle'
  //and similar syntax. By using the Visitor Pattern, both problems are solved.

  /**
   * Visits a shape and draws it according to the rules of the specific ViewVisitor.
   * @param s the shape to be drawn.
   * @return a String that represents the Shape's form in the type of View the visitor draws.
   * @throws IllegalArgumentException if the Shape is invalid or null.
   */
  String visitShape(ImmutableShape s) throws IllegalArgumentException;

  /**
   * Visits a Rectangle and draws it according to the rules of the specific ViewVisitor.
   * @param r the rectangle to be drawn.
   * @return a String that represents the Rectangle's form in the type of View the visitor draws.
   * @throws IllegalArgumentException if the rectangle is invalid or null.
   */
  String visitRect(ImmutableShape r) throws IllegalArgumentException;

  /**
   * Visits an Ellipse and draws it according to the rules of the specific ViewVisitor.
   * @param e the ellipse to be drawn.
   * @return a String that represents the Ellipse's form in the type of View the visitor draws.
   * @throws IllegalArgumentException if the ellipse is invalid or null.
   */
  String visitEllipse(ImmutableShape e) throws IllegalArgumentException;

}
