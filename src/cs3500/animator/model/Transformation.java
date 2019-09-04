package cs3500.animator.model;

/**
 * Represents a transformation that will be applied to a shape.
 */
public interface Transformation extends Comparable {
  /**
   * Returns a property that would be a result of applying this transformation.
   * @param time the timeframe within this Transformation that we want to apply to.
   * @return The changed Property.
   */
  Property apply(int time);

  /**
   * Returns this Transformation's start time.
   */
  int getStartT();

  /**
   * Returns this Transformation's end time.
   */
  int getEndT();

  /**
   * Returns true if this transformation's start and end states have different x values.
   * @return a boolean representing if this transformation changes x values over its duration
   */
  boolean getChangesX();

  /**
   * Returns true if this transformation's start and end states have different y values.
   * @return a boolean representing if this transformation changes y values over its duration
   */
  boolean getChangesY();

  /**
   * Returns true if this transformation's start and end states have different width values.
   * @return a boolean representing if this transformation changes width values over its duration
   */
  boolean getChangesW();

  /**
   * Returns true if this transformation's start and end states have different height values.
   * @return a boolean representing if this transformation changes height values over its duration
   */
  boolean getChangesH();

  /**
   * Returns true if this transformation's start and end states have different r color values.
   * @return a boolean representing if this transformation changes r color values over its duration
   */
  boolean getChangesR();

  /**
   * Returns true if this transformation's start and end states have different g color values.
   * @return a boolean representing if this transformation changes g color values over its duration
   */
  boolean getChangesG();

  /**
   * Returns true if this transformation's start and end states have different b color values.
   * @return a boolean representing if this transformation changes b color values over its duration
   */
  boolean getChangesB();

  /**
   * Returns the Properties that will be the result of this Transformation.
   * @return Property representing this.endProperties
   */
  Property getEndState();

  /**
   * Returns the Properties that will be at the start of this Transformation.
   * @return Property representing this.startProperties
   */
  Property getStartState();
}