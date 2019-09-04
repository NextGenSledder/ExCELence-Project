package cs3500.animator.util;


import cs3500.animator.model.AShape;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.KeyframeAnimationModelImpl;
import cs3500.animator.model.KeyframeShape;
import cs3500.animator.model.Shape;
import cs3500.animator.model.Transformation;

/**
 * Represents an interface for an Adapter that changes one type of an animation model to another.
 */
public class AnimationModelAdapter {

  /**
   * Takes in an AnimationModel and returns a Model that uses Transformations.
   * @param model - The model to be adapted.
   * @return - An AnimationModelImpl that contains the same shapes with the same information as
   *           the given Model just with keyframes zipped up into transformations.
   */
  public static AnimationModelImpl adaptToTransModel(AnimationModel model) {
    AnimationModelImpl adaptedModel = new AnimationModelImpl();

    for (Shape s : model.getShapes()) {
      adaptedModel.addShape(new AShape(s.getName(), s.getProperties(), s.getType().toString()));
      for (Transformation t : s.getTransformations()) {
        adaptedModel.addTransformation(s.getName(), t);
      }
    }
    adaptedModel.setCanvas();
    return adaptedModel;
  }

  /**
   * Takes in an AnimationModel and returns a Model that uses Keyframes.
   * @param model - The model to be adapted.
   * @return - A KeyframeAnimationModelImpl that contains the same shapes with the same
   *           information as the given Model just with transformations unzipped into keyframes.
   */
  public static KeyframeAnimationModelImpl adaptToKeyframeModel(AnimationModel model) {
    KeyframeAnimationModelImpl adaptedModel = new KeyframeAnimationModelImpl();
    for (Shape s : model.getShapes()) {
      adaptedModel.addShape(new KeyframeShape(s.getName(), s.getProperties(),
              s.getType().toString()));
      for (Keyframe k : s.getKeyframes()) {
        adaptedModel.addKeyframe(s.getName(), k);
      }
    }
    adaptedModel.setCanvas();
    return adaptedModel;
  }
}