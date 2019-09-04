package cs3500.animator.controller;

import static cs3500.animator.util.AnimationReader.parseFile;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl.AnimationModelImplBuilder;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.KeyframeAnimationModel;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.model.KeyframeShape;
import cs3500.animator.model.PropImpl;
import cs3500.animator.model.Property;
import cs3500.animator.model.ROAnimationModelImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.util.AnimationModelAdapter;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.EditView;
import cs3500.animator.view.SVGViewImpl;
import cs3500.animator.view.TableTextViewImpl;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Basic Implementation of an AnimationController, which makes changes to a KeyFrameAnimationModel
 *     at the beck and call of an EditView.
 */
public class AnimationControllerImpl implements AnimationController {
  private KeyframeAnimationModel model;
  private AnimationView view;

  /**
   * Constructs an AnimationControllerImpl with the given model & view.
   * @param model the model to make edits to
   * @param view the view prompting edits from the user
   */
  public AnimationControllerImpl(AnimationModel model, AnimationView view) {
    this.model = AnimationModelAdapter.adaptToKeyframeModel(model);
    this.view = view;
  }

  @Override
  public void addShape(String name, String type) {
    Property prop = new PropImpl(0, 0, 0, 0, 0, 0, 0);
    Shape shape = new KeyframeShape(name, prop, type);

    model.addShape(shape);
  }

  @Override
  public void removeShape(String shapeName) {
    model.removeShape(shapeName);
  }

  @Override
  public void addKey(String shapeName, int t) {
    Property prop = new PropImpl(0, 0, 0, 0, 0, 0, 0);
    for (Shape shape : model.getShapes()) {
      if (shape.getName().equals(shapeName)) {
        shape.advance(t);
        prop = shape.getProperties();
      }
    }
    Keyframe key = new KeyframeImpl(prop, t);
    model.addKeyframe(shapeName, key);
  }

  @Override
  public void saveKey(String shapeName, int t, int x, int y, int w, int h, int r, int g, int b) {
    Property prop = new PropImpl(x, y, w, h, r, g, b);
    Keyframe key = new KeyframeImpl(prop, t);
    model.addKeyframe(shapeName, key);
  }

  @Override
  public void removeKey(String shapeName, int t) {
    model.removeKeyframe(shapeName, t);
  }

  @Override
  public void save(String output) throws IOException {
    AnimationView view;
    FileWriter writer = new FileWriter(output);

    if (output.endsWith(".txt")) {
      view = new TableTextViewImpl(model, writer);
    } else if (output.endsWith(".svg")) {
      view = new SVGViewImpl(model, this.view.getSpeed(), writer);
    } else {
      throw new IllegalArgumentException("Cannot save to that file type");
    }

    view.display();

    writer.flush();
    writer.close();
  }

  @Override
  public void load(String in) throws IOException {

    Readable readable = new FileReader(in);
    AnimationModelImplBuilder builder = new AnimationModelImplBuilder();
    AnimationModel newModel = parseFile(readable, builder);
    newModel.advance(1);

    new EditView(newModel, this.view.getSpeed());
  }

  @Override
  public ROAnimationModelImpl getModel() {
    return new ROAnimationModelImpl(this.model);
  }
}