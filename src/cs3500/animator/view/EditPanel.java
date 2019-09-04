package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.EditListener;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Shape;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

/**
 * Represents the JPanel which includes all of the actions which can make edits to the model.
 */
public class EditPanel extends JPanel implements ItemListener {

  private AnimationController ctrl;
  private Shape selectedShape;
  private Keyframe selectedKey;
  private final JComboBox<String> keySelector;
  private final JComboBox<String> shapeSelector;
  private final JTextField inField;
  private final JTextField outField;
  private final JTextField tField;
  private final JTextField xField;
  private final JTextField yField;
  private final JTextField wField;
  private final JTextField hField;
  private final JTextField rField;
  private final JTextField gField;
  private final JTextField bField;
  private final JTextField newTField;
  private final JTextField newShapeNameField;
  private final JTextField newShapeTypeField;

  /**
   * Constructs a new EditPanel with a given controller & read-only model.
   *
   * @param ctrl the controller that contains the model and can actually edit the model.
   */
  public EditPanel(AnimationController ctrl) {
    super();
    this.ctrl = ctrl;
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    EditListener listener = new EditListener(this, ctrl);

    // Selector for Shapes
    JPanel shapeSelectorPanel = new JPanel();
    shapeSelectorPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    shapeSelectorPanel.setLayout(new BoxLayout(shapeSelectorPanel, BoxLayout.PAGE_AXIS));
    this.add(shapeSelectorPanel);

    JLabel shapeSelectorDisplay = new JLabel("Select a shape.");
    shapeSelectorPanel.add(shapeSelectorDisplay);
    ArrayList<String> shapes = new ArrayList<>();
    for (Shape s : this.ctrl.getModel().getShapes()) {
      shapes.add(s.getName());
    }
    JComboBox<String> shapeSelector = new JComboBox<>();
    for (String str : shapes) {
      shapeSelector.addItem(str);
    }
    shapeSelector.setActionCommand("selected shape");
    shapeSelector.addItemListener(this);
    shapeSelectorPanel.add(shapeSelector);
    this.shapeSelector = shapeSelector;

    // Selector for Keyframes
    JPanel keySelectorPanel = new JPanel();
    keySelectorPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    keySelectorPanel.setLayout(new BoxLayout(keySelectorPanel, BoxLayout.PAGE_AXIS));
    this.add(keySelectorPanel);

    JLabel keySelectorDisplay = new JLabel("Select a keyframe.");
    keySelectorPanel.add(keySelectorDisplay);
    JComboBox<String> keySelector = new JComboBox<>();
    keySelector.setActionCommand("selected key");
    keySelector.addItemListener(this);
    keySelectorPanel.add(keySelector);
    this.keySelector = keySelector;

    JPanel attributePanel = new JPanel();
    attributePanel
        .setBorder(BorderFactory.createTitledBorder("Edit this keyframe's attributes!"));
    this.add(attributePanel);

    // Text Field for setting time of a keyFrame
    JTextField tField = new JTextField(5);
    tField.setBorder(BorderFactory.createTitledBorder("Time"));
    tField.setEditable(false);
    this.tField = tField;
    attributePanel.add(tField);

    // Text Field for setting x position of a keyFrame
    JTextField xField = new JTextField(5);
    xField.setBorder(BorderFactory.createTitledBorder("X Pos"));
    this.xField = xField;
    attributePanel.add(xField);

    // Text Field for setting y position of a keyFrame
    JTextField yField = new JTextField(5);
    yField.setBorder(BorderFactory.createTitledBorder("Y Pos"));
    this.yField = yField;
    attributePanel.add(yField);

    // Text Field for setting width of a keyFrame
    JTextField wField = new JTextField(5);
    wField.setBorder(BorderFactory.createTitledBorder("Width"));
    this.wField = wField;
    attributePanel.add(wField);

    // Text Field for setting height of a keyFrame
    JTextField hField = new JTextField(5);
    hField.setBorder(BorderFactory.createTitledBorder("Height"));
    this.hField = hField;
    attributePanel.add(hField);

    // Text Field for setting red of a keyFrame
    JTextField rField = new JTextField(5);
    rField.setBorder(BorderFactory.createTitledBorder("Red"));
    this.rField = rField;
    attributePanel.add(rField);

    // Text Field for setting green of a keyFrame
    JTextField gField = new JTextField(5);
    gField.setBorder(BorderFactory.createTitledBorder("Green"));
    this.gField = gField;
    attributePanel.add(gField);

    // Text Field for setting green of a keyFrame
    JTextField bField = new JTextField(5);
    bField.setBorder(BorderFactory.createTitledBorder("Blue"));
    this.bField = bField;
    attributePanel.add(bField);

    // Button for saving changes to keyframe based on attribute fields
    JButton saveKeyButton = new JButton("Save changes");
    saveKeyButton.setActionCommand("save keyframe");
    saveKeyButton.addActionListener(listener);
    attributePanel.add(saveKeyButton);

    // Panel + Field + Button for creating a new keyframe at the given time
    JPanel newKeyPanel = new JPanel();
    newKeyPanel.setBorder(BorderFactory.createTitledBorder("Make a new keyframe!"));
    this.add(newKeyPanel);
    JTextField newKeyTimeField = new JTextField(5);
    newKeyTimeField.setText("time");
    newKeyTimeField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    this.newTField = newKeyTimeField;
    newKeyPanel.add(newKeyTimeField);
    JButton newKeyButton = new JButton("Create keyframe");
    newKeyButton.setActionCommand("add keyframe");
    newKeyButton.addActionListener(listener);
    newKeyPanel.add(newKeyButton);

    // Panel + Text Fields + Button for creating a new Shape
    JPanel newShapePanel = new JPanel();
    newShapePanel.setBorder(BorderFactory.createTitledBorder("Make a new shape!"));
    this.add(newShapePanel);
    JTextField newShapeNameField = new JTextField(10);
    newShapeNameField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    newShapeNameField.setText("name");
    this.newShapeNameField = newShapeNameField;
    newShapePanel.add(newShapeNameField);
    JTextField newShapeTypeField = new JTextField(10);
    newShapeTypeField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    newShapeTypeField.setText("type");
    this.newShapeTypeField = newShapeTypeField;
    newShapePanel.add(newShapeTypeField);
    JButton newShapeButton = new JButton("Create shape");
    newShapeButton.setActionCommand("add shape");
    newShapeButton.addActionListener(listener);
    newShapePanel.add(newShapeButton);

    // Panel + Buttons for removing Shape/Keyframe
    JPanel removePanel = new JPanel();
    removePanel.setBorder(BorderFactory.createTitledBorder("Remove selected items!"));
    this.add(removePanel);
    JButton removeShapeButton = new JButton("Remove shape");
    removeShapeButton.setActionCommand("remove shape");
    removeShapeButton.addActionListener(listener);
    removePanel.add(removeShapeButton);
    JButton removeKeyButton = new JButton("Remove keyframe");
    removeKeyButton.setActionCommand("remove keyframe");
    removeKeyButton.addActionListener(listener);
    removePanel.add(removeKeyButton);

    // Panel + Text Field + Button for loading from a new file
    JPanel loadPanel = new JPanel();
    loadPanel.setBorder(BorderFactory.createTitledBorder("Load a new animation from a text file!"));
    this.add(loadPanel);
    JTextField inField = new JTextField(30);
    inField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    inField.setText("file location");
    this.inField = inField;
    loadPanel.add(inField);
    JButton loadButton = new JButton("Load");
    loadButton.setActionCommand("load");
    loadButton.addActionListener(listener);
    loadPanel.add(loadButton);

    // Panel + Text Field + Button for saving to a file
    JPanel savePanel = new JPanel();
    savePanel
        .setBorder(BorderFactory.createTitledBorder("Save your animation as a .txt or .svg!"));
    this.add(savePanel);
    JTextField outField = new JTextField(30);
    outField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    outField.setText("file location");
    this.outField = outField;
    savePanel.add(outField);
    JButton saveButton = new JButton("Save");
    saveButton.setActionCommand("save");
    saveButton.addActionListener(listener);
    savePanel.add(saveButton);
  }

  /**
   * Returns the Shape that is currently selected.
   *
   * @return the selected Shape
   */
  public Shape getSelectedShape() {
    return this.selectedShape;
  }

  /**
   * Returns the Keyframe that is currently selected.
   *
   * @return the selected Keyframe.
   */
  public Keyframe getSelectedKey() {
    return this.selectedKey;
  }

  /**
   * Returns the new X value for a Keyframe.
   *
   * @return the user provided x value
   */
  public int getChangeX() {
    return Integer.parseInt(this.xField.getText());
  }

  /**
   * Returns the new Y value for a Keyframe.
   *
   * @return the user provided y value
   */
  public int getChangeY() {
    return Integer.parseInt(this.yField.getText());
  }

  /**
   * Returns the new W value for a Keyframe.
   *
   * @return the user provided w value
   */
  public int getChangeW() {
    return Integer.parseInt(this.wField.getText());
  }

  /**
   * Returns the new H value for a Keyframe.
   *
   * @return the user provided h value
   */
  public int getChangeH() {
    return Integer.parseInt(this.hField.getText());
  }

  /**
   * Returns the new R value for a Keyframe.
   *
   * @return the user provided r value
   */
  public int getChangeR() {
    return Integer.parseInt(this.rField.getText());
  }

  /**
   * Returns the new G value for a Keyframe.
   *
   * @return the user provided g value
   */
  public int getChangeG() {
    return Integer.parseInt(this.gField.getText());
  }

  /**
   * Returns the new B value for a Keyframe.
   *
   * @return the user provided b value.
   */
  public int getChangeB() {
    return Integer.parseInt(this.bField.getText());
  }

  /**
   * Returns the time at which to create a new Keyframe.
   *
   * @return the user provided t value.
   */
  public int getNewTime() {
    return Integer.parseInt(this.newTField.getText());
  }

  /**
   * Returns the file location to save to.
   *
   * @return the user provided file location
   */
  public String getOutput() {
    return this.outField.getText();
  }

  /**
   * Returns the file location to load from.
   *
   * @return the user provided file location
   */
  public String getInput() {
    return this.inField.getText();
  }

  /**
   * Returns the name of a new Shape to be created.
   *
   * @return the user provided name
   */
  public String getNewShapeName() {
    return this.newShapeNameField.getText();
  }

  /**
   * Returns the type of a new Shape to be created.
   *
   * @return the user provided type
   */
  public String getNewShapeType() {
    return this.newShapeTypeField.getText();
  }

  @Override
  public void itemStateChanged(ItemEvent e) {

    try {
      int keyTime = Integer.parseInt((String) e.getItem());
      for (Keyframe k : this.selectedShape.getKeyframes()) {
        if (k.getTime() == keyTime) {
          this.selectedKey = k;
        }
      }
      this.tField.setText("");
      this.xField.setText("");
      this.yField.setText("");
      this.wField.setText("");
      this.hField.setText("");
      this.rField.setText("");
      this.gField.setText("");
      this.bField.setText("");

      this.tField.setText(Integer.toString(this.selectedKey.getTime()));
      this.xField.setText(Integer.toString(this.selectedKey.getX()));
      this.yField.setText(Integer.toString(this.selectedKey.getY()));
      this.wField.setText(Integer.toString(this.selectedKey.getW()));
      this.hField.setText(Integer.toString(this.selectedKey.getH()));
      this.rField.setText(Integer.toString(this.selectedKey.getR()));
      this.gField.setText(Integer.toString(this.selectedKey.getG()));
      this.bField.setText(Integer.toString(this.selectedKey.getB()));

      //this.updateKeyDropdown();

    } catch (NumberFormatException n) {
      String shapeName = (String) e.getItem();
      for (Shape s : this.ctrl.getModel().getShapes()) {
        if (s.getName().equals(shapeName)) {
          this.selectedShape = s;
        }
      }

      this.updateKeyDropdown();
    }
  }

  /**
   * Updates the dropdown menu that shows the shapes in the GUI to show all shapes in the model.
   */
  public void updateShapeDropdown() {
    this.shapeSelector.removeAllItems();

    ArrayList<String> shapes = new ArrayList<>();
    for (Shape s : this.ctrl.getModel().getShapes()) {
      shapes.add(s.getName());
    }
    for (String str : shapes) {
      this.shapeSelector.addItem(str);
    }
  }

  /**
   * Updates the dropdown menu that shows keyframes in the GUI to show all keyframes in the model.
   */
  public void updateKeyDropdown() {
    this.keySelector.removeAllItems();

    ArrayList<String> keyframes = new ArrayList<>();
    for (Keyframe k : selectedShape.getKeyframes()) {
      keyframes.add(Integer.toString(k.getTime()));
    }
    for (String str : keyframes) {
      this.keySelector.addItem(str);
    }
  }
}
