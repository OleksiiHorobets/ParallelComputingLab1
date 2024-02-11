package org.example.counter.impl;

import org.example.counter.Counter;

public class SynchronizedMethodCounter implements Counter {

  private int counter;

  @Override
  public synchronized void increment() {
    counter++;
  }

  @Override
  public synchronized void decrement() {
    counter--;
  }

  @Override
  public synchronized int get() {
    return counter;
  }
}
