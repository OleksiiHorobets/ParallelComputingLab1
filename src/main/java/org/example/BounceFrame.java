package org.example;

import jakarta.annotation.PostConstruct;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BounceFrame extends JFrame {

  private final BallCanvas canvas;
  public static final int WIDTH = 450;
  public static final int HEIGHT = 350;

  public BounceFrame(BallCanvas ballCanvas) {
    log.info("In Frame Thread name = {}", Thread.currentThread().getName());
    this.canvas = ballCanvas;
    configureFrame();
  }

  private void configureFrame() {
    this.setSize(WIDTH, HEIGHT);
    this.setTitle("Bounce program");
    Container content = this.getContentPane();
    content.add(this.canvas, BorderLayout.CENTER);

    JPanel buttonPanel = createButtonPanel();
    content.add(buttonPanel, BorderLayout.SOUTH);
  }

  private JPanel createButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.lightGray);

    createStartButton(buttonPanel);
    createStopButton(buttonPanel);

    return buttonPanel;
  }


  private void createStopButton(JPanel buttonPanel) {
    JButton buttonStop = createButton("Stop", e -> System.exit(0));
    buttonPanel.add(buttonStop);
  }

  private void createStartButton(JPanel buttonPanel) {
    var buttonStart = createButton("Start", this::addNewBallToCanvas);
    buttonPanel.add(buttonStart);
  }


  private JButton createButton(String buttonText, ActionListener actionListener) {
    JButton startButton = new JButton(buttonText);
    startButton.addActionListener(actionListener);
    return startButton;
  }


  private void addNewBallToCanvas(ActionEvent event) {
    Ball b = new Ball(canvas);
    canvas.add(b);

    BallThread thread = new BallThread(b);
    thread.start();
    log.info("New thread started = {}", thread.getName());
  }


  @PostConstruct
  public void initBounceFrame() {
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
    log.info("Thread name = " + Thread.currentThread().getName());
  }

}
