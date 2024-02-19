package org.example;

import jakarta.annotation.PostConstruct;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BounceFrame extends JFrame {

  private final BallCanvas canvas;
  public static final int WIDTH = 800;
  public static final int HEIGHT = 400;

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

    createButton("Start Blue", addNewBallToCanvas(Color.BLUE, Thread.MIN_PRIORITY), buttonPanel);
    createButton("Start Red", addNewBallToCanvas(Color.RED, Thread.MAX_PRIORITY), buttonPanel);
    createButton("Priority Test", priorityTest(), buttonPanel);
    createButton("Join Test", joinTest(), buttonPanel);
    createButton("Stop", e -> System.exit(0), buttonPanel);

    return buttonPanel;
  }

  private ActionListener joinTest() {
    return e -> new Thread(this::runTestJoin).start();
  }


  private ActionListener priorityTest() {
    return event -> {
      createNewBall(Color.RED, Thread.MAX_PRIORITY);
      IntStream.rangeClosed(0, 500)
          .forEach(x -> createNewBall(Color.BLUE, Thread.MIN_PRIORITY));
    };
  }


  private void createButton(String buttonText, ActionListener actionListener, JPanel buttonPanel) {
    JButton startButton = new JButton(buttonText);
    startButton.addActionListener(actionListener);
    buttonPanel.add(startButton);
  }


  private ActionListener addNewBallToCanvas(Color color, int threadPriority) {
    return e -> createNewBall(color, threadPriority);
  }

  private void createNewBall(Color color, int threadPriority) {
    Ball b = new Ball(canvas, color);
    canvas.add(b);

    BallThread thread = new BallThread(b);
    thread.setPriority(threadPriority);
    thread.start();
    log.info("New thread started = {}", thread.getName());
  }


  @PostConstruct
  public void initBounceFrame() {
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @SneakyThrows
  private void runTestJoin() {
    var blueBall = new Ball(canvas, Color.BLUE);
    var redBall = new Ball(canvas, Color.RED);

    canvas.add(blueBall);
    canvas.add(redBall);

    Thread blueBallThread = new BallThread(blueBall);
    Thread redBallThread = new BallThread(redBall);

    blueBallThread.start();
    blueBallThread.join();
    redBallThread.start();
  }
}
