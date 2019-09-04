package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cs3500.animator.util.AnimationModelAdapter;

public class ShapeZippingTests {

  /**
   * Tests that the model adapter works.
   * Creates a KeyframeModel and a TransformationModel and checks that they have the same info
   * if they are adapted to be the same type of model.
   */
  @Test
  public void testShapeZipping() {
    //make a transformation and a set of keyframes that represent that same change in an animation
    AShape shape1 = new AShape("Steven",
            new PropImpl(0, 0, 0, 0, 0, 0, 0), "rectangle");

    Transformation trans1 = new ATransformation(
            new PropImpl(10, 10, 10, 10, 10, 10, 10),
            new PropImpl(20, 20, 20, 20, 20, 20, 20), 0, 1);

    KeyframeShape keyShape1 = new KeyframeShape("Bob",
            new PropImpl(0, 0, 0, 0, 0, 0, 0), "rectangle");

    Keyframe key1 = new KeyframeImpl(
            new PropImpl(10, 10, 10, 10, 10, 10, 10), 0);
    Keyframe key2 = new KeyframeImpl(
            new PropImpl(20, 20, 20, 20, 20, 20, 20), 1);

    AnimationModelImpl model = new AnimationModelImpl();

    model.addShape(shape1);
    model.addTransformation("Steven", trans1);

    KeyframeAnimationModelImpl keyModel = new KeyframeAnimationModelImpl();

    keyModel.addShape(keyShape1);
    keyModel.addKeyframe("Bob", key1);
    keyModel.addKeyframe("Bob", key2);

    String keyModelInfo = key1.toString() + key2.toString();

    KeyframeAnimationModelImpl modelNowIsKey =
            new AnimationModelAdapter().adaptToKeyframeModel(model);

    StringBuilder modelNowKey = new StringBuilder();
    for (Shape s : modelNowIsKey.getShapes()) {
      for (Keyframe k : s.getKeyframes()) {
        modelNowKey.append(k.toString());
      }
    }
    String modelFromTransToKey = modelNowKey.toString();

    assertEquals(keyModelInfo, modelFromTransToKey);
  }
}
