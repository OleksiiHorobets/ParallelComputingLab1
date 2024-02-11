package org.example.counter.impl;

import org.example.counter.Counter;

@Deprecated(forRemoval = true)
public class NaiveCounter implements Counter {

  private int counter;

  @Override
  public void increment() {
    counter++;
  }

  @Override
  public void decrement() {
    counter--;
  }

  @Override
  public int get() {
    return counter;
  }
}
