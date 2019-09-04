package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KeyframeModelTests {
  Property noProps = new PropImpl(0,0,0,0,0,0,0);
  Property prop1 = new PropImpl(100,100,100,100,255,0,0);
  Property prop2 = new PropImpl(100,100,50,50,0,255,0);

  Keyframe key0 = new KeyframeImpl(noProps, 0);

  KeyframeShape shape1 = new KeyframeShape("R", noProps, "rectangle");

  KeyframeAnimationModelImpl model = new KeyframeAnimationModelImpl();


  @Test
  public void testAddingAndRemovingShapes() {
    assertEquals(0, model.getShapes().size());
    model.addShape(shape1);
    assertEquals(1, model.getShapes().size());
    model.removeShape("R");
    assertEquals(0, model.getShapes().size());
  }

  @Test
  public void testAddingAndRemovingKeyframes() {
    assertEquals(0, shape1.getKeyframes().size());
    model.addShape(shape1);
    model.addKeyframe("R", key0);
    assertEquals(1, shape1.getKeyframes().size());
    model.removeKeyframe("R", 0);
    assertEquals(0, shape1.getKeyframes().size());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddingTransformations() {
    model.addShape(shape1);
    model.addTransformation("R", new ATransformation(prop1, prop2, 1, 10));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveTransformations() {
    model.addShape(shape1);
    model.removeTransformation("R", new ATransformation(prop1, prop2,
            1, 10));
  }
}
