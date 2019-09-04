package cs3500.animator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AnimationModelTests {

  private AnimationModel model = new AnimationModelImpl();

  private Property rKey0;
  private Property rKey1;
  private Property rKey3;
  private Property rKey4;

  private Transformation rTrans1;
  private Transformation rTrans2;
  private Transformation rTrans3;
  private Transformation rTrans4;
  private Transformation rTrans5;

  private Transformation cTrans1;
  private Transformation cTrans2;
  private Transformation cTrans3;
  private Transformation cTrans4;

  private Shape r;
  private Shape c;

  @Before
  public void initialize() {
    AnimationModel model = new AnimationModelImpl();
    model.setCanvas(200, 70, 360, 360);

    rKey0 = new PropImpl(200, 200, 50, 100, 255, 0, 0);
    rKey1 = new PropImpl(10, 200, 50, 100, 255, 0, 0);
    Property rKey2 = new PropImpl(300, 300, 50, 100, 255, 0, 0);
    rKey3 = new PropImpl(300, 300, 25, 100, 255, 0, 0);
    rKey4 = new PropImpl(200, 200, 25, 100, 255, 0, 0);

    Property cKey0 = new PropImpl(440, 70, 60, 30, 0, 0, 255);
    Property cKey1 = new PropImpl(440, 70, 120, 60, 0, 0, 255);
    Property cKey2 = new PropImpl(440, 370, 120, 60, 0, 0, 255);
    Property cKey3 = new PropImpl(440, 370, 120, 60, 0, 255, 0);

    rTrans1 = new ATransformation(rKey0, rKey1, 1, 10);
    rTrans2 = new ATransformation(rKey1, rKey2, 10, 50);
    rTrans3 = new ATransformation(rKey2, rKey2, 50, 51);
    rTrans4 = new ATransformation(rKey2, rKey3, 51, 70);
    rTrans5 = new ATransformation(rKey3, rKey4, 70, 100);

    cTrans1 = new ATransformation(cKey0, cKey1, 6, 50);
    cTrans2 = new ATransformation(cKey1, cKey2, 50, 70);
    cTrans3 = new ATransformation(cKey2, cKey3, 70, 80);
    cTrans4 = new ATransformation(cKey3, cKey3, 80, 100);

    r = new AShape("R", rKey0, "rectangle");
    c = new AShape("C", cKey0, "ellipse");
  }

  /**
   * This first tests make sure all of these are true:
   * - the cs3500.animator.model can add Shapes.
   * - the cs3500.animator.model can add Transformations --> Shapes can add Transformations.
   * - AnimationModel.tableTextView works and updates whenever a Transformation is added.
   * - the cs3500.animator.model can advance to times and have the properties of every Shape
   *   in this.shapes
   *   change depending on the time and the Transformations in Shape's list of Transformations.
   * - the Overridden toString methods all work properly.
   */
  @Test
  public void testModelAddingAndAdvancing() {
    this.model.addShape(r);
    model.addShape(c);


    model.addTransformation("R", rTrans1);
    model.addTransformation("C", cTrans1);
    model.addTransformation("R", rTrans2);
    model.addTransformation("C", cTrans2);
    model.addTransformation("R", rTrans3);
    model.addTransformation("C", cTrans3);
    model.addTransformation("R", rTrans4);
    model.addTransformation("C", cTrans4);
    model.addTransformation("R", rTrans5);

    assertEquals("R 200 200 50 100 255 0 0", r.toString());
    assertEquals("C 440 70 60 30 0 0 255", c.toString());
    model.advance(10);
    assertEquals("R 10 200 50 100 255 0 0\n"
            + "C 440 70 73 36 0 0 255\n", model.currentView());
    assertEquals("R 10 200 50 100 255 0 0", r.toString());
    //since C is in the middle of a transformation,
    //the properties of C are between the start and end states
    assertEquals("C 440 70 73 36 0 0 255", c.toString());
    model.advance(50);
    assertEquals("R 300 300 50 100 255 0 0\n"
            + "C 440 70 120 60 0 0 255\n", model.currentView());
    assertEquals("R 300 300 50 100 255 0 0", r.toString());
    assertEquals("C 440 70 120 60 0 0 255", c.toString());
    model.advance(70);
    assertEquals("R 300 300 25 100 255 0 0\n"
            + "C 440 370 120 60 0 0 255\n", model.currentView());
    assertEquals("R 300 300 25 100 255 0 0", r.toString());
    assertEquals("C 440 370 120 60 0 0 255", c.toString());
    model.advance(100);
    assertEquals("R 200 200 25 100 255 0 0\n"
            + "C 440 370 120 60 0 255 0\n", model.currentView());
    assertEquals("R 200 200 25 100 255 0 0", r.toString());
    assertEquals("C 440 370 120 60 0 255 0", c.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullShape() {
    model.addShape(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullTransformation() {
    model.addTransformation(null, rTrans1);
  }

  @Test
  public void testAddTransformationToAShapeNotInList() {
    model.addTransformation("R", rTrans1);
    assertEquals(0, model.howManyShapes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAdvanceToNegativeTime() {
    model.advance(-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingTransformations1() {
    r.addTransformation(rTrans1);
    r.addTransformation(rTrans1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeConstructorNullName() {
    Shape death = new AShape(null, rKey0, "rectangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeConstructorNullProperties() {
    Shape doom = new AShape("Cody", null, "ellipse");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeConstructorNullType() {
    Shape destruction = new AShape("Cody", rKey0, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeConstructorWrongType() {
    Shape weirdOne = new AShape("Cody", rKey0, "doodad");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTransformationConstructorTime1() {
    Transformation fromLiveToDie = new ATransformation(rKey0, rKey1, 10, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTransformationConstructorTime2() {
    Transformation fromLiveToDie = new ATransformation(rKey0, rKey1, -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTransformationConstructorTime3() {
    Transformation fromLiveToDie = new ATransformation(rKey0, rKey1, 20, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTransformationConstructorNullProperties() {
    Transformation fromLiveToDie = new ATransformation(null, null,
            10, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPropertyConstructorBadRed1() {
    Property p = new PropImpl(10,10,10,10, 900, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPropertyConstructorBadRed2() {
    Property p = new PropImpl(10,10,10,10, -10, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPropertyConstructorBadGreen1() {
    Property p = new PropImpl(10,10,10,10, 10, 990, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPropertyConstructorBadGreen2() {
    Property p = new PropImpl(10,10,10,10, 10, -990, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPropertyConstructorBadBlue1() {
    Property p = new PropImpl(10,10,10,10, 90, 90, 1000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPropertyConstructorBadBlue2() {
    Property p = new PropImpl(10,10,10,10, 90, 90, -1000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPropertyConstructorBadWidth() {
    Property p = new PropImpl(10,10,-10,10, 90, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPropertyConstructorBadHeight() {
    Property p = new PropImpl(10,10,10,-10, 0, 0, 0);
  }

  @Test
  public void testValidConstructors() {
    assertEquals("R", r.getName());
    assertEquals(ShapeType.RECTANGLE, r.getType());

    assertEquals(200, rKey0.getPosx());
    assertEquals(200, rKey0.getPosy());
    assertEquals(50, rKey0.getWidth());
    assertEquals(100, rKey0.getHeight());
    assertEquals(255, rKey0.getColorR());
    assertEquals(0, rKey0.getColorG());
    assertEquals(0, rKey0.getColorB());

    assertEquals(10, rTrans1.getEndT());
    assertEquals(1, rTrans1.getStartT());
    assertEquals("10 200 50 100 255 0 0", rTrans1.getEndState().toString());
    assertEquals("200 200 50 100 255 0 0", rTrans1.getStartState().toString());
    assertFalse(rTrans1.getChangesR() || rTrans1.getChangesG() || rTrans1.getChangesB());
    assertFalse(rTrans1.getChangesW() || rTrans1.getChangesH());
    assertTrue(rTrans1.getChangesX() || rTrans1.getChangesY());
  }

  @Test
  public void testApply() {
    assertEquals(new PropImpl(10, 200, 50, 100, 255, 0, 0),
            rTrans1.apply(10));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyTransformationOutOfBounds() {
    rTrans1.apply(100);
  }

  @Test
  public void testPropertyEquals() {
    assertFalse(rKey0.equals(rKey1));
    assertTrue(rKey0.equals(rKey0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddingInvalidTransformation1() {
    r.addTransformation(rTrans1);
    r.addTransformation(new ATransformation(rKey0, rKey1, 4, 10));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddingInvalidTransformation2() {
    r.addTransformation(rTrans1);
    r.addTransformation(new ATransformation(rKey0, rKey1, 9, 14));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddingInvalidTransformation3() {
    r.addTransformation(rTrans1);
    r.addTransformation(new ATransformation(rKey3, rKey4, 10, 14));
  }

  @Test
  public void testGetShapeType() {
    this.model.addShape(r);
    model.addShape(c);

    assertEquals("RECT", model.getShapeType("R"));
    assertEquals("ELLIPSE", model.getShapeType("C"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNonexistantShapeType() {
    model.getShapeType("line2");
  }

  @Test
  public void testSetCanvas() {
    AnimationModelImpl model = new AnimationModelImpl();

    Property initialProps = new PropImpl(0, 0, 100, 100, 255, 255, 255);
    Property endProps = new PropImpl(100, 100, 100, 100, 255, 255, 255);

    Shape testCanvasShape = new AShape("test", initialProps, "rectangle");
    model.addShape(testCanvasShape);

    Transformation testCanvasTrans = new ATransformation(initialProps, endProps,
            0, 1);
    model.addTransformation("test", testCanvasTrans);

    model.setCanvas();

    assertEquals(200, model.getCanvasW());

    assertEquals(200, model.getCanvasH());
  }
}