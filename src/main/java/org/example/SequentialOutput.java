package org.example;

import lombok.SneakyThrows;

public class SequentialOutput {

  private static final int SYMBOLS_AMOUNT = 100;
  private static final Object monitor = new Object();
  private static boolean printPipe = false;

  public static void main(String[] args) throws InterruptedException {
    Thread minusThread = new Thread(() -> printNTimes("-", SYMBOLS_AMOUNT));
    Thread pipeThread = new Thread(() -> printNTimes("|", SYMBOLS_AMOUNT));

    minusThread.start();
    pipeThread.start();
    minusThread.join();
  }

  @SneakyThrows
  private static void printNTimes(String str, int times) {
    for (int i = 0; i < times; i++) {
      synchronized (monitor) {
        while ((str.equals("-") && printPipe) || (str.equals("|") && !printPipe)) {
          monitor.wait();
        }
        System.out.println(str);
        printPipe = !printPipe;

        monitor.notify();
      }
    }
  }

}
