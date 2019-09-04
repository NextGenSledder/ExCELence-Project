package cs3500.animator.util;

import static cs3500.animator.util.AnimationReader.parseFile;
import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl.AnimationModelImplBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

public class ReadAndBuildTest {
  File file;
  Readable readable;
  AnimationBuilder<AnimationModel> builder;
  AnimationModel model;

  void initializeToh() throws IOException {
    this.file = new File("C:\\Users\\River\\OneDrive\\Desktop\\Animation Inputs\\toh-3.txt");
    this.readable = new FileReader(file);
    this.builder  = new AnimationModelImplBuilder();
    this.model = parseFile(readable, builder);
  }

  @Test
  public void testHanoi() throws IOException {
    this.initializeToh();

    model.advance(3);
    assertEquals("disk1 190 180 20 30 0 49 90\n"
            + "disk2 167 210 65 30 6 247 41\n"
            + "disk3 145 240 110 30 11 45 175\n", model.currentView());
    model.advance(102);
    assertEquals("disk1 340 50 20 30 0 49 90\n"
            + "disk2 317 240 65 30 6 247 41\n"
            + "disk3 145 240 110 30 11 45 175\n", model.currentView());
    model.advance(267);
    assertEquals("disk1 490 180 20 30 0 255 0\n"
            + "disk2 467 210 65 30 0 255 0\n"
            + "disk3 445 240 110 30 0 255 0\n", model.currentView());
  }
}