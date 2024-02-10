package org.example;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Ball {

  private final Component canvas;
  private static final int XSIZE = 20;
  private static final int YSIZE = 20;

  @Data
  @AllArgsConstructor
  private static class Coordinate {

    private int x;
    private int y;
  }

  private Coordinate coordinate;

  private int dx = 2;
  private int dy = 2;


  public Ball(Component c) {
    this.canvas = c;
    coordinate = generateRandomCoordinate();
  }

  public void draw(Graphics2D g2) {
    g2.setColor(Color.darkGray);
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

  private Coordinate generateRandomCoordinate() {
    if (ThreadLocalRandom.current().nextBoolean()) {
      return new Coordinate(0, randomBoundedInt(YSIZE + 500));
    } else {
      return new Coordinate(randomBoundedInt(XSIZE + 500), 0);
    }
  }

  private int randomBoundedInt(int bound) {
    return ThreadLocalRandom.current().nextInt(bound);
  }

}
