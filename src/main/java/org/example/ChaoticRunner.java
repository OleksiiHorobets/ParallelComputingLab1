package org.example;

public class ChaoticRunner {

  private static final int SYMBOLS_AMOUNT = 100;

  static Runnable minusPrinter = () -> {
    for (int i = 0; i < SYMBOLS_AMOUNT; i++) {
      System.out.println("-");
    }
  };

  static Runnable pipePrinter = () -> {
    for (int i = 0; i < SYMBOLS_AMOUNT; i++) {
      System.out.println("|");
    }
  };


  public static void main(String[] args) throws InterruptedException {
    var t1 = new Thread(minusPrinter);
    var t2 = new Thread(pipePrinter);

    t1.start();
    t2.start();

    t1.join();
    t2.join();
  }
}
