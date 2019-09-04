package cs3500.animator.view;

import org.junit.Test;

import javax.swing.event.ChangeEvent;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.KeyframeAnimationModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Runs tests for the scrubbing extra credit.
 */
public class ScrubbingTests {

  @Test
  public void testScrubbing() {

    AnimationModel model = new KeyframeAnimationModelImpl();
    model.setCanvas(10, 10, 10, 10);

    EditView view = new EditView(model, 10);

    GraphicsViewPanel graphicsPanel = new GraphicsViewPanel(view, 10);

    ButtonPanel buttonPanel = new ButtonPanel(graphicsPanel, 100);

    assertEquals(0, buttonPanel.slider.getValue());

    buttonPanel.slider.setValue(10);
    buttonPanel.stateChanged(new ChangeEvent(buttonPanel.slider));

    assertEquals(10, buttonPanel.slider.getValue());
  }
}
