package org.example;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BallThread extends Thread {

  private final Ball ball;

  @Override
  @SneakyThrows
  public void run() {

    for (int i = 0; i < 10_000; i++) {
      ball.move();
      log.info("Thread name = {}", Thread.currentThread().getName());
      Thread.sleep(1);
    }
  }
}
