package cs3500.animator.controller;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.KeyframeAnimationModel;
import cs3500.animator.model.KeyframeAnimationModelImpl;
import cs3500.animator.view.EditPanel;
import cs3500.animator.view.EditView;
import cs3500.animator.view.GraphicsViewPanel;
import cs3500.animator.view.ViewListener;

import org.junit.Before;
import org.junit.Test;

public class ControllerTests {
  AnimationController ctrl;
  KeyframeAnimationModel model;

  EditListener editListener;


  @Before
  public void constructTestables() {
    KeyframeAnimationModel model = new KeyframeAnimationModelImpl();
    EditView view = new EditView(model, 1);
    AnimationController ctrl = new AnimationControllerImpl(model, view);



    this.model = model;
    this.ctrl = ctrl;

    EditListener editListener = new EditListener(new EditPanel(ctrl), ctrl);

    ViewListener viewListener = new ViewListener(new GraphicsViewPanel(view, 1));
  }

  @Test
  public void controllerMutatesModel() {
    ctrl.addShape("box", "rectangle");
    ctrl.addShape("oval", "ellipse");

    assertEquals(model.getShapes().get(0).getName(), "box");
    assertEquals(model.getShapes().get(1).getName(), "oval");
    assertEquals(model.howManyShapes(), 2);

    ctrl.removeShape("box");
    assertEquals(model.getShapes().get(0).getName(), "oval");
    assertEquals(model.howManyShapes(), 1);

    ctrl.addKey("oval", 10);
    ctrl.saveKey("oval", 10, 5, 5, 5, 15, 0, 255, 0);
    ctrl.addKey("oval", 20);
    ctrl.saveKey("oval", 20, 30, 30, 15, 5, 125, 0, 125);

    assertEquals(model.getShapes().get(0).getKeyframes().size(), 2);

    ctrl.removeKey("oval", 10);

    assertEquals(model.getShapes().get(0).getKeyframes().size(), 1);
  }
}
