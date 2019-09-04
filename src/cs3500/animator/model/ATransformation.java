package cs3500.animator.model;

/**
 * Represents an abstract class for transformation. INVARIANT: no null fields. INVARIANT: endTime
 * must always be greater than startTime and both must be positive.
 */
public class ATransformation implements Transformation {

  private final Property startProperties;
  private final Property endProperties;
  private int startTime;
  private int endTime;

  /**
   * The constructor for ATransformation, taking in...
   *
   * @param startProperties - the properties of the Shape being transformed before it is
   *     transformed
   * @param endProperties - the properties that this Transformation will end up with
   * @param startTime - the time this Transformation begins
   * @param endTime - the time this Transformation ends
   * @throws IllegalArgumentException if startProperties is null or endProperties is null or if
   *     endTime is less than startTime or if either startTime or endTime are less than 0
   */
  protected ATransformation(Property startProperties, Property endProperties, int startTime,
      int endTime) {
    if (startProperties == null || endProperties == null) {
      throw new IllegalArgumentException("Can't have null fields");
    }
    if (endTime < startTime || endTime < 0 || startTime < 0) {
      throw new IllegalArgumentException("The timeframe of this Transformation doesn't work!");
    }
    this.startProperties = startProperties;
    this.endProperties = endProperties;
    this.endTime = endTime;
    this.startTime = startTime;
  }

  @Override
  public Property apply(int t) {
    if (t >= this.startTime && t <= this.endTime) {
      double deltaT = this.endTime - this.startTime;
      int howCloseToA = this.endTime - t;
      int howCloseToB = t - this.startTime;
      return new PropImpl(
          (int) ((this.startProperties.getPosx() * (howCloseToA / deltaT))
                      + (this.endProperties.getPosx() * (howCloseToB / deltaT))),
          (int) ((this.startProperties.getPosy() * (howCloseToA / deltaT))
                      + (this.endProperties.getPosy() * (howCloseToB / deltaT))),
          (int) ((this.startProperties.getWidth() * (howCloseToA / deltaT))
                      + (this.endProperties.getWidth() * (howCloseToB / deltaT))),
          (int) ((this.startProperties.getHeight() * (howCloseToA / deltaT))
                      + (this.endProperties.getHeight() * (howCloseToB / deltaT))),
          (int) ((this.startProperties.getColorR() * (howCloseToA / deltaT))
                      + (this.endProperties.getColorR() * (howCloseToB / deltaT))),
          (int) ((this.startProperties.getColorG() * (howCloseToA / deltaT))
                      + (this.endProperties.getColorG() * (howCloseToB / deltaT))),
          (int) ((this.startProperties.getColorB() * (howCloseToA / deltaT))
                      + (this.endProperties.getColorB() * (howCloseToB / deltaT))));
    } else {
      throw new IllegalArgumentException("Given time not in bounds of this Transformation!");
    }
  }

  @Override
  public int getStartT() {
    return this.startTime;
  }

  @Override
  public int getEndT() {
    return this.endTime;
  }

  @Override
  public boolean getChangesX() {
    return this.endProperties.getPosx() != this.startProperties.getPosx();
  }

  @Override
  public boolean getChangesY() {
    return this.endProperties.getPosy() != this.startProperties.getPosy();
  }

  @Override
  public boolean getChangesW() {
    return this.endProperties.getWidth() != this.startProperties.getWidth();
  }

  @Override
  public boolean getChangesH() {
    return this.endProperties.getHeight() != this.startProperties.getHeight();
  }

  @Override
  public boolean getChangesR() {
    return this.endProperties.getColorR() != this.startProperties.getColorR();
  }

  @Override
  public boolean getChangesG() {
    return this.endProperties.getColorG() != this.startProperties.getColorG();
  }

  @Override
  public boolean getChangesB() {
    return this.endProperties.getColorB() != this.startProperties.getColorB();
  }

  @Override
  public Property getEndState() {
    return this.endProperties;
  }

  @Override
  public Property getStartState() {
    return this.startProperties;
  }

  @Override
  public String toString() {
    return this.startTime + " " + this.startProperties.toString() + "     " + this.endTime + " "
            + this.endProperties.toString();
  }

  @Override
  public int compareTo(Object o) {
    if (o == null) {
      throw new IllegalArgumentException("cannot compare a null to a Transformation");
    }
    if (!(o instanceof ATransformation)) {
      throw new ClassCastException("cannot compare this object to a Transformation");
    }
    ATransformation trans = (ATransformation) o;
    if (this.getStartT() < trans.getStartT()) {
      return -1;
    } else if (this.getStartT() == trans.getStartT()) {
      return 0;
    } else {
      return -1;
    }
  }
}