package org.example;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ball {

  private static final int XSIZE = 20;
  private static final int YSIZE = 20;

  private final Component canvas;
  private final Point coordinate;
  private final Color color;
  private int dx = 2;
  private int dy = 2;

  private boolean isAlive = true;

  public Ball(Component component, Color color) {
    this.canvas = component;
    this.color = color;
    coordinate = new Point(10, 20);
//    coordinate = generateRandomCoordinate();
  }

  public void draw(Graphics2D g2) {
    g2.setColor(color);
    g2.fill(new Ellipse2D.Double(coordinate.x, coordinate.y, XSIZE, YSIZE));
  }

  public void move() {
    coordinate.x += dx;
    coordinate.y += dy;

    if (coordinate.x < 0) {
      coordinate.x = 0;
      dx = -dx;
    }
    if (coordinate.x + XSIZE >= this.canvas.getWidth()) {
      coordinate.x = this.canvas.getWidth() - XSIZE;
      dx = -dx;
    }
    if (coordinate.y < 0) {
      coordinate.y = 0;
      dy = -dy;
    }
    if (coordinate.y + YSIZE >= this.canvas.getHeight()) {
      coordinate.y = this.canvas.getHeight() - YSIZE;
      dy = -dy;
    }
    this.canvas.repaint();
  }

  private Point generateRandomCoordinate() {
    if (ThreadLocalRandom.current().nextBoolean()) {
      return new Point(0, randomBoundedInt(YSIZE + 500));
    } else {
      return new Point(randomBoundedInt(XSIZE + 500), 0);
    }
  }

  private int randomBoundedInt(int bound) {
    return ThreadLocalRandom.current().nextInt(bound);
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setIsAlive(List<Hole> holes) {
    isAlive = holes.stream()
        .noneMatch(
            hole -> hole.isCaptured(coordinate.x + XSIZE / 2, coordinate.y + YSIZE / 2, XSIZE / 2));
    log.info("Is alive: {}", isAlive);
//    alive = holes.stream()
//        .allMatch(hole -> hole.isCaptured(x + XSIZE / 2, y + YSIZE / 2, XSIZE / 2));

  }
}
