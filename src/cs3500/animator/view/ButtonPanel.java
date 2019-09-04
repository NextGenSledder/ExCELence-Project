package cs3500.animator.view;

import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class ButtonPanel extends JPanel implements ChangeListener {

  private GraphicsViewPanel p;
  public JSlider slider;

  ButtonPanel(GraphicsViewPanel p, int maxTime) {
    super();

    this.p = p;

    ViewListener listener = new ViewListener(p);

    //Panel to edit the Speed of an Animation with Buttons
    JPanel speedPanel = new JPanel();
    speedPanel.setBorder(BorderFactory.createTitledBorder("Change speed!"));

    //Decrease speed on an Animation by 1
    JButton decreaseSpeed = new JButton(" - ");
    decreaseSpeed.setActionCommand("decrease");
    decreaseSpeed.addActionListener(listener);
    speedPanel.add(decreaseSpeed);

    //Increase speed on an Animation by 1
    JButton increaseSpeed = new JButton(" + ");
    increaseSpeed.setActionCommand("increase");
    increaseSpeed.addActionListener(listener);
    speedPanel.add(increaseSpeed);

    this.add(speedPanel);

    //Button for starting an Animation
    JButton startButton = new JButton("Start");
    startButton.setActionCommand("play");
    startButton.addActionListener(listener);
    this.add(startButton);

    //Button for pausing an Animation
    JButton pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("pause");
    pauseButton.addActionListener(listener);
    this.add(pauseButton);

    //Button for resuming an Animation
    JButton resumeButton = new JButton("Resume");
    resumeButton.setActionCommand("play");
    resumeButton.addActionListener(listener);
    this.add(resumeButton);

    //Button for restarting an Animation
    JButton restartButton = new JButton("Restart");
    restartButton.setActionCommand("restart");
    restartButton.addActionListener(listener);
    this.add(restartButton);

    //Button for looping an Animation
    JToggleButton loopButton = new JToggleButton("Loop");
    loopButton.setActionCommand("setloop");
    loopButton.addActionListener(listener);
    this.add(loopButton);

    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, maxTime ,0);
    slider.addChangeListener(this);
    slider.setSnapToTicks(true);
    this.slider = slider;
    this.add(slider);

    this.setVisible(true);
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();

    if (source.getValueIsAdjusting()) {
      int newTime = source.getValue();
      this.p.setCurrentFrame(newTime);
      this.p.advanceModel();
    }
  }
}
