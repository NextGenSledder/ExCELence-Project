package cs3500.animator;

import static cs3500.animator.util.AnimationReader.parseFile;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl.AnimationModelImplBuilder;
import cs3500.animator.provider.adapter.NewEditableView;
import cs3500.animator.provider.adapter.SimpleAdapterModel;
import cs3500.animator.view.EditView;
import cs3500.animator.view.GraphicView;
import cs3500.animator.view.SVGViewImpl;
import cs3500.animator.view.TableTextViewImpl;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import cs3500.animator.view.AnimationView;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public final class Excellence {

  /**
   * Main method for running our excellent Animator.
   * @param args the type of view, input file, output file, and speed.
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame("FrameDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel emptyLabel = new JLabel();
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);

    try {
      StringBuilder str = new StringBuilder();
      for (String s : args) {
        str.append(s).append("\n");
      }
      Scanner scan = new Scanner(str.toString());

      File in = null;
      String viewType = null;
      Appendable out = System.out;
      int speed = 1;

      while (scan.hasNext()) {
        String cmd = scan.next();
        switch (cmd) {
          case "-in":
            in = new File(scan.next());
            break;
          case "-view":
            viewType = scan.next();
            break;
          case "-out":
            out = new FileWriter(scan.next());
            break;
          case "-speed":
            speed = scan.nextInt();
            break;
          default:
            // do nothing, as it causes no harm to receive extra input
            break;
        }
      }

      Readable readable = new FileReader(in);
      AnimationModelImplBuilder builder = new AnimationModelImplBuilder();
      AnimationModel model = parseFile(readable, builder);
      model.advance(1);
      model.setCanvas();

      AnimationView view;
      switch (viewType) {
        case "text":
          view = new TableTextViewImpl(model, out);
          break;
        case "svg":
          view = new SVGViewImpl(model, speed, out);
          break;
        case "visual":
          view = new GraphicView(model, speed);
          break;
        case "edit":
          view = new EditView(model, speed);
          break;
        case "provider":
          SimpleAdapterModel newModel = new SimpleAdapterModel(model);
          view = new NewEditableView(newModel, speed);
          break;
        default :
          throw new UnsupportedOperationException("unrecognized view type");
      }

      view.display();

      if (out instanceof FileWriter) {
        ((FileWriter) out).flush();
        ((FileWriter) out).close();
      }
    } catch (IOException | NullPointerException | UnsupportedOperationException
            | IllegalArgumentException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(frame,
              e.getMessage(),
              "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }
}