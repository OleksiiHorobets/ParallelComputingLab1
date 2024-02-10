package org.example;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;

@Component
public class BallCanvas extends JPanel {

  private final List<Ball> balls = new ArrayList<>();

  public void add(Ball b) {
    this.balls.add(b);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    for (int i = 0; i < balls.size(); i++) {
      Ball b = balls.get(i);
      b.draw(g2);
    }
  }
}
