package org.example.counter.impl;

import org.example.counter.Counter;

public class SynchronizedBlockCounter implements Counter {

  private final Object monitor = new Object();
  private int counter;

  @Override
  public void increment() {
    synchronized (monitor) {
      counter++;
    }
  }

  @Override
  public void decrement() {
    synchronized (monitor) {
      counter--;
    }
  }

  @Override
  public int get() {
    synchronized (monitor) {
      return counter;
    }

  }

}
