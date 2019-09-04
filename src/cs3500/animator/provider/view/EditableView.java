package cs3500.animator.provider.view;

import static javax.swing.BoxLayout.PAGE_AXIS;

import cs3500.animator.provider.adapter.ImmutableShape;
import cs3500.animator.model.KeyframeImpl;
import cs3500.animator.provider.model.Keyframe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

/**
 * An AnimatorView that represents an animation that allows the User to edit and interact with
 * the animation as it plays, either by interacting with its play or manipulating the animation
 * itself.
 */
public class EditableView extends JFrame implements AnimatorView {

  private AnimationPanel animationPanel;
  private JButton start;
  private JButton stop;
  private JButton reset;
  private JButton increment;
  private JButton decrement;
  private JButton loop;
  private JButton addShape;
  private JButton removeShape;
  private JButton addKF;
  private JButton removeKF;
  private JButton editKF;
  private JButton saveFile;
  private JButton loadFile;
  private JTextField shapeInput;
  private JTextField timeInput;
  private JTextField editX;
  private JTextField editY;
  private JTextField editW;
  private JTextField editH;
  private JTextField editR;
  private JTextField editG;
  private JTextField editB;
  private JTextField fileNameContainer;
  private Point canvasPosition;
  private JList<ImmutableShape> shapeList;
  private JList<Keyframe> keyframeList;

  /**
   * Create an instance of an EditableView object.
   * @param pos the top-left corner of the canvas.
   * @param size the size of the canvas.
   */
  public EditableView(Point pos, Dimension size) {
    super();

    if (pos == null || size == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }

    this.canvasPosition = pos;
    this.setTitle("Animate!");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //the container for all other panels and button containers, to keep things organized
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, PAGE_AXIS));

    JScrollPane containerScroll = new JScrollPane(container);

    //the panel that contains the animation
    animationPanel = new AnimationPanel();
    animationPanel.setOffset(pos);
    animationPanel.setSize(size);
    animationPanel.setPreferredSize(size);

    container.add(new JScrollPane(animationPanel));

    //The button panel with the playback controls
    JPanel playbackButtonPanel = new JPanel();
    playbackButtonPanel.setBorder(BorderFactory.createTitledBorder("Playback Controls"));
    playbackButtonPanel.setLayout(new BoxLayout(playbackButtonPanel, BoxLayout.LINE_AXIS));

    start = new JButton("Play");
    start.setActionCommand("start");
    playbackButtonPanel.add(start);

    stop = new JButton("Pause");
    stop.setActionCommand("pause");
    playbackButtonPanel.add(stop);

    reset = new JButton("Restart");
    reset.setActionCommand("restart");
    playbackButtonPanel.add(reset);

    increment = new JButton("Increase Speed");
    increment.setActionCommand("increment");
    playbackButtonPanel.add(increment);

    decrement = new JButton("Decrease Speed");
    decrement.setActionCommand("decrement");
    playbackButtonPanel.add(decrement);

    loop = new JButton("Toggle Loop");
    loop.setActionCommand("loop");
    playbackButtonPanel.add(loop);

    container.add(playbackButtonPanel);

    //The panel with the ability to select Shapes and Keyframes
    JPanel selectionPanel = new JPanel();
    selectionPanel.setBorder(
        BorderFactory.createTitledBorder("Shape [name] [type] / Keyframe [t x y w h r g b]"));
    selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.X_AXIS));

    shapeList = new JList<>();
    shapeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    shapeList.setCellRenderer(new ShapeRender());
    selectionPanel.add(new JScrollPane(shapeList));

    keyframeList = new JList<>();
    keyframeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    keyframeList.setCellRenderer(new KeyframeRender());
    selectionPanel.add(new JScrollPane(keyframeList));

    container.add(selectionPanel);

    //the panel with the ability to edit the animation, add and remove shapes and keyframes
    JPanel animationEditPanel = new JPanel();
    animationEditPanel.setBorder(
        BorderFactory.createTitledBorder(
            "For Add Shape: \"ShapeType ShapeName\" / For Add Keyframe: \"Time\" "));
    animationEditPanel.setLayout(new BoxLayout(animationEditPanel, BoxLayout.LINE_AXIS));

    addShape = new JButton("Add Shape");
    addShape.setActionCommand("add shape");
    animationEditPanel.add(addShape);
    shapeInput = new JTextField();
    animationEditPanel.add(shapeInput);

    removeShape = new JButton("Remove Shape");
    removeShape.setActionCommand("remove shape");
    animationEditPanel.add(removeShape);

    addKF = new JButton("Add Keyframe");
    addKF.setActionCommand("add keyframe");
    animationEditPanel.add(addKF);
    timeInput = new JTextField();
    animationEditPanel.add(timeInput);

    removeKF = new JButton("Remove Keyframe");
    removeKF.setActionCommand("remove keyframe");
    animationEditPanel.add(removeKF);

    //the panel that gives the ability to edit keyframes
    JPanel keyframeEditPanel = new JPanel();
    keyframeEditPanel.setBorder(BorderFactory.createTitledBorder("Edit Values: x y w h r g b"));
    keyframeEditPanel.setLayout(new BoxLayout(keyframeEditPanel, BoxLayout.LINE_AXIS));

    editKF = new JButton("Edit Selected Keyframe");
    editKF.setActionCommand("edit keyframe");
    keyframeEditPanel.add(editKF);

    editX = new JTextField();
    editY = new JTextField();
    editW = new JTextField();
    editH = new JTextField();
    editR = new JTextField();
    editG = new JTextField();
    editB = new JTextField();

    keyframeEditPanel.add(editX);
    keyframeEditPanel.add(editY);
    keyframeEditPanel.add(editW);
    keyframeEditPanel.add(editH);
    keyframeEditPanel.add(editR);
    keyframeEditPanel.add(editG);
    keyframeEditPanel.add(editB);

    container.add(animationEditPanel);
    container.add(keyframeEditPanel);

    //the panel that gives the ability to save and load animations
    JPanel filePanel = new JPanel();
    filePanel.setBorder(
        BorderFactory.createTitledBorder("Specify filepath to save to (svg or txt) or load from"));
    filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.LINE_AXIS));

    saveFile = new JButton("Save Animation");
    saveFile.setActionCommand("save animation");
    filePanel.add(saveFile);

    loadFile = new JButton("Load Animation");
    loadFile.setActionCommand("load animation");
    filePanel.add(loadFile);

    fileNameContainer = new JTextField();
    filePanel.add(fileNameContainer);

    container.add(filePanel);



    this.add(containerScroll);

    pack();
  }

  @Override
  public void draw(List<ImmutableShape> shapes, int animationLength) {

    animationPanel.setShapes(shapes);

    animationPanel.setOffset(canvasPosition);

    //for the list of shapes

    DefaultListModel<ImmutableShape> shapeData = new DefaultListModel<>();

    for (ImmutableShape s : shapes) {
      shapeData.addElement(s);
    }
    shapeList.setModel(shapeData);

    this.setVisible(true);

    refresh(0);
  }

  /**
   * The EditableView does not use this method, as its time is maintained by the controller.
   * @param speed the speed of the animation.
   */
  @Override
  public void setSpeed(int speed) {
    //EditableView does not need to keep track of time, but attempting to set its time
    //should not be noteworthy.
  }


  @Override
  public void refresh(int tick) {
    animationPanel.setTime(tick);

    repaint();
  }

  /**
   * Update the model data in this View to change the course of the animation.
   * @param shapes a list of Immutable shapes for the View to animate.
   */
  @Override
  public void updateData(List<ImmutableShape> shapes) {
    animationPanel.setShapes(shapes);

    DefaultListModel<ImmutableShape> shapeData = new DefaultListModel<>();

    for (ImmutableShape s : shapes) {
      shapeData.addElement(s);
    }
    shapeList.setModel(shapeData);
  }

  /**
   * Set each button to having the specified ActionListener. The ActionListener will execute
   * actions based on user input provided from the View.
   * @param action the listener that will execute the appropriate actions upon hearing from
   */
  @Override
  public void setListener(ActionListener action) {
    start.addActionListener(action);
    stop.addActionListener(action);
    reset.addActionListener(action);
    increment.addActionListener(action);
    decrement.addActionListener(action);
    loop.addActionListener(action);
    addShape.addActionListener(action);
    removeShape.addActionListener(action);
    addKF.addActionListener(action);
    removeKF.addActionListener(action);
    editKF.addActionListener(action);
    saveFile.addActionListener(action);
    loadFile.addActionListener(action);
  }

  /**
   * Sets the ListSelectionListeners specifically for the JLists in this View.
   * @param listener the listener(s) that describe certain behavior in Selection Lists in
   */
  @Override
  public void setListSelectionListener(Map<String, ListSelectionListener> listener) {
    if (listener.containsKey("shape")) {
      shapeList.addListSelectionListener(listener.get("shape"));
    }

    if (listener.containsKey("keyframe")) {
      keyframeList.addListSelectionListener(listener.get("keyframe"));
    }
  }

  /**
   * Determines which Keyframes are going to be displayed in the Keyframe JList, based on
   * the Shape selected in the other JList.
   * @param s the Shape whose keyframes are being highlighted.
   */
  @Override
  public void setKeyframes(ImmutableShape s) {
    DefaultListModel<Keyframe> keyframeData = new DefaultListModel<>();

    for (Keyframe k : s.getKeyframes()) {
      keyframeData.addElement(k);
    }

    keyframeList.setModel(keyframeData);
  }

  /**
   * Gets the shape selected by the user in the View. Allows the Controller to interact directly
   * with what the User is pressing on and dealing with.
   * @return an Immutable Shape selected from the Shape JList.
   */
  @Override
  public ImmutableShape getSelectedShape() {
    return shapeList.getSelectedValue();
  }

  /**
   * Gets the keyframe selected by the user in the View. Allows the Controller to interact
   * directly with what the User is pressing on and dealing with, and lets the make corresponding
   * actions in the Model.
   * @return a Keyframe selected from the Keyframe JList by the User.
   */
  @Override
  public Keyframe getSelectedKeyframe() {
    return keyframeList.getSelectedValue();
  }

  /**
   * Retrieve the String inputted by the User that should contain a declaration for a Shape,
   * containing the Shape's type and name, in that order. Errors are handled by the Controller
   * or user of this class.
   * @return the String inputted by the user into the shapeInput text field.
   */
  @Override
  public String getShapeDeclaration() {
    String text = shapeInput.getText();
    shapeInput.setText("");
    return text;
  }

  /**
   * Retrieve the String inputted by the User that should contain a specified time. Errors are
   * handled by the Controller or user of this class.
   * @return the String inputted by the user into the timeInput text field.
   */
  @Override
  public String getSpecifiedTime() {
    String text = timeInput.getText();
    timeInput.setText("");
    return text;
  }

  /**
   * Retrieve a keyframe that is composed of the currently selected keyframe and any edits
   * made by the user.
   * @return a keyframe composed of a selected keyframe and user inputs.
   * @throws IllegalArgumentException if no keyframe was selected.
   */
  @Override
  public Keyframe getNewKeyframe() throws IllegalArgumentException {
    //need to make some decisions about how I want to handle errors at different points
    Keyframe k = keyframeList.getSelectedValue();
    if (k == null) {
      throw new IllegalArgumentException("no keyframe selected");
    }

    Point newPos = k.getPos();
    Dimension newSize = k.getSize();
    Color newColor = k.getColor();

    try {
      if (!editX.getText().equals("")) {
        newPos.setLocation(Integer.parseInt(editX.getText()), newPos.getY());
      }
      if (!editY.getText().equals("")) {
        newPos.setLocation(newPos.getX(), Integer.parseInt(editY.getText()));
      }
      if (!editW.getText().equals("")) {
        newSize.setSize(Integer.parseInt(editW.getText()), newSize.getHeight());
      }
      if (!editH.getText().equals("")) {
        newSize.setSize(newSize.getWidth(), Integer.parseInt(editH.getText()));
      }
      if (!editR.getText().equals("")) {
        newColor = new Color(Integer.parseInt(editR.getText()), newColor.getGreen(),
            newColor.getBlue());
      }
      if (!editG.getText().equals("")) {
        newColor = new Color(newColor.getRed(), Integer.parseInt(editG.getText()),
            newColor.getBlue());
      }
      if (!editB.getText().equals("")) {
        newColor = new Color(newColor.getRed(), newColor.getGreen(),
            Integer.parseInt(editB.getText()));
      }
    } catch (NumberFormatException e) {
      displayErrorMessage("Number read incorrectly. Try again.");
    }

    return new KeyframeImpl(k.getTime(), newPos, newSize, newColor);
  }

  /**
   * Retrieve the name of a file inputted by the User. This filename can be used for any purpose,
   * and is parsed by the controller/user of the View.
   * @return a String that contains the user's input of a name of a file.
   */
  @Override
  public String getSpecifiedFile() {
    return fileNameContainer.getText();
  }

  /**
   * Display an error message in this View. The message that the error displays is customizable.
   * Opens a pop-up window containing the error message.
   * @param msg an error message to display.
   */
  @Override
  public void displayErrorMessage(String msg) {
    JOptionPane.showMessageDialog(this, msg);
  }


  /**
   * Draws an item in the Selection List of Immutable Shapes.
   */
  public class ShapeRender extends JLabel implements ListCellRenderer<ImmutableShape> {
    public ShapeRender() {
      setOpaque(true);
    }

    /**
     * Draws the details of a Shape in its corresponding JList.
     * @param list the JList itself.
     * @param value the impacted shape.
     * @param index the index of the shape in the list.
     * @param isSelected whether or not the shape has been selected.
     * @param cellHasFocus whether or not the cell has focus.
     * @return a Component that can be drawn in the JList.
     */
    public Component getListCellRendererComponent(JList<? extends ImmutableShape> list,
        ImmutableShape value,
        int index,
        boolean isSelected,
        boolean cellHasFocus) {

      setText((new SimpleTextVisitor()).visitShape(value));

      Color background;
      Color foreground;

      if (isSelected) {
        background = Color.BLUE;
        foreground = Color.WHITE;
      } else {
        background = Color.WHITE;
        foreground = Color.BLACK;
      }

      setBackground(background);
      setForeground(foreground);

      return this;
    }
  }

  /**
   * Draws an item in the Selection List of Keyframes.
   */
  public class KeyframeRender extends JLabel implements ListCellRenderer<Keyframe> {
    public KeyframeRender() {
      setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Keyframe> list, Keyframe value,
        int index, boolean isSelected, boolean cellHasFocus) {

      setText(String.format("%d %d %d %d %d %d %d %d", value.getTime(),
          (int) value.getPos().getX(), (int) value.getPos().getY(),
          (int) value.getSize().getWidth(), (int) value.getSize().getHeight(),
          value.getColor().getRed(), value.getColor().getGreen(), value.getColor().getBlue()));

      Color background;
      Color foreground;

      if (isSelected) {
        background = Color.BLUE;
        foreground = Color.WHITE;
      } else {
        background = Color.WHITE;
        foreground = Color.BLACK;
      }

      setBackground(background);
      setForeground(foreground);

      return this;
    }
  }
}