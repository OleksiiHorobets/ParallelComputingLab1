package org.example.counter;

import java.util.stream.IntStream;
import lombok.SneakyThrows;
import org.example.counter.impl.AtomicCounter;
import org.example.counter.impl.NaiveCounter;
import org.example.counter.impl.SynchronizedBlockCounter;
import org.example.counter.impl.SynchronizedMethodCounter;

public class CounterRunner {


  public static void main(String[] args) {
//    for (int i = 0; i < 100; i++) {
//
//    }
    testRunner(new NaiveCounter());
    testRunner(new SynchronizedBlockCounter());
    testRunner(new SynchronizedMethodCounter());
    testRunner(new AtomicCounter());
  }


  @SneakyThrows
  private static void testRunner(Counter counter) {
    var t1 = new Thread(() -> IntStream.rangeClosed(0, 10_000).forEach(x -> counter.increment()));
    var t2 = new Thread(() -> IntStream.rangeClosed(0, 10_000).forEach(x -> counter.decrement()));

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println(counter.getClass().getSimpleName() + " result: " + counter.get());
  }
}
