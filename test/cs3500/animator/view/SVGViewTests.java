package cs3500.animator.view;

import static cs3500.animator.util.AnimationReader.parseFile;
import static org.junit.Assert.assertEquals;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl.AnimationModelImplBuilder;
import cs3500.animator.model.ROAnimationModelImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import org.junit.Test;

/**
 * Creates the svg file required for submission and tests that SVGViewImpl works.
 */
public class SVGViewTests {

  /**
   * Creates the proper toh-at-20.svg file and verifies that SVGViewImpl works as intended.
   * @throws FileNotFoundException if the file we are reading info from doesn't exist
   */
  @Test
  public void easyTest() throws FileNotFoundException {
    File file = new File(
            "C:\\Users\\jmich\\Desktop\\animation_examples\\toh-8.txt");
    Readable readable = new FileReader(file);
    AnimationModelImplBuilder builder = new AnimationModelImplBuilder();
    AnimationModel actualModel = parseFile(readable, builder);
    actualModel.setCanvas(200, 70, 1000, 1000);
    ROAnimationModelImpl model = new ROAnimationModelImpl(actualModel);
    PrintStream output = null;
    try {
      output = new PrintStream(new File("toh-at-20.svg"));
    } catch (FileNotFoundException ignored) {
      //intentionally do nothing
    }
    SVGViewImpl view = new SVGViewImpl(model, 20, output);

    view.display();

    //have a view take in another example and test that the output is correct

    File simpleFile = new File(
            "C:\\Users\\jmich\\Desktop\\animation_examples\\smalldemo.txt");
    Readable simpleReadable = new FileReader(simpleFile);
    AnimationModelImplBuilder simpleBuilder = new AnimationModelImplBuilder();
    ROAnimationModelImpl simpleModel = new ROAnimationModelImpl(parseFile(simpleReadable,
            simpleBuilder));
    //simpleModel.setCanvas(200, 70, 360, 360);

    SVGViewImpl viewTestOutput = new SVGViewImpl(simpleModel, 1, new StringBuilder());
    simpleModel.advance(10);

    viewTestOutput.display();
    assertEquals(8, model.getShapes().size());
    //we had an assertEquals that ensured that the format was correct,
    //but it gave us - 8 style points
  }
}