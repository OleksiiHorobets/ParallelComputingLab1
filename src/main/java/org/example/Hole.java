package org.example;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Hole {

  private static final int RADIUS = 20;

  private final Component canvas;
  private final double relativeX;
  private final double relativeY;

  private Point countAbsoluteCoords() {
    int x = (int) (canvas.getWidth() * relativeX);
    int y = (int) (canvas.getHeight() * relativeY);
    return new Point(x, y);
  }

  public void draw(Graphics2D g2) {
    var absoluteCoords = countAbsoluteCoords();
    g2.setColor(Color.black);
    g2.fill(new Ellipse2D.Double(
        (double) absoluteCoords.x - RADIUS,
        (double) absoluteCoords.y - RADIUS,
        RADIUS * 2d,
        RADIUS * 2d
    ));
  }

  public boolean isCaptured(int x, int y, int r) {
    var holeCenter = countAbsoluteCoords();
    var distance = Point2D.distanceSq(x, y, holeCenter.x, holeCenter.y);

    return distance < (RADIUS + r) * (RADIUS + r);
  }

}
