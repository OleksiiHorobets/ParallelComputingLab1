package org.example;

import jakarta.annotation.PostConstruct;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BallCanvas extends JPanel {

  private final List<Ball> balls = new ArrayList<>();
  private List<Hole> holes;

  private int deadCounter;

  private JLabel deadCounterLabel;


  public void add(Ball b) {
    this.balls.add(b);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    drawHoles(g2);
    drawBalls(g2);
    updateScore();
  }

  private void updateScore() {
    log.info("---------Score updated to: {}", deadCounter);
    deadCounterLabel.setText(String.valueOf(deadCounter));
  }

  private void drawBalls(Graphics2D graphics2D) {
    for (int i = balls.size() - 1; i >= 0; i--) {
      Ball ball = balls.get(i);
      ball.setIsAlive(holes);

      if (!ball.isAlive()) {
        balls.remove(i);
        deadCounter++;
      } else {
        ball.draw(graphics2D);
      }
    }
  }

  private void drawHoles(Graphics2D graphics2D) {
    holes.forEach(hole -> hole.draw(graphics2D));
  }

  @PostConstruct
  public void init() {
    holes = initHoles();
    deadCounterLabel = initJLabel();
    this.add(deadCounterLabel);
  }

  private JLabel initJLabel() {
    JLabel jLabel = new JLabel("0");
    jLabel.setFont(new Font("Verdana", Font.BOLD, 18));
    jLabel.setForeground(Color.RED);
    return jLabel;
  }

  private List<Hole> initHoles() {
    return List.of(
        new Hole(this, 0.0, 0.0),
        new Hole(this, 0.5, 0.0),
        new Hole(this, 1.0, 0.0),
        new Hole(this, 0.0, 1.0),
        new Hole(this, 0.5, 1.0),
        new Hole(this, 1.0, 1.0)
    );
  }
}
