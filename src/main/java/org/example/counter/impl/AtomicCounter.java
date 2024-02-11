package org.example.counter.impl;

import java.util.concurrent.atomic.AtomicInteger;
import org.example.counter.Counter;

public class AtomicCounter implements Counter {

  private final AtomicInteger counter = new AtomicInteger(0);

  @Override
  public void increment() {
    counter.incrementAndGet();
  }

  @Override
  public void decrement() {
    counter.decrementAndGet();
  }

  @Override
  public int get() {
    return counter.get();
  }
}