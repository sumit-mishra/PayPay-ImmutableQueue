package com.paypay.immutableq.impl;

import com.paypay.immutableq.intrface.Queue;

import java.util.NoSuchElementException;

public class ImmutableQueue<T> implements Queue<T> {
  private java.util.Queue<T> queue;

  public ImmutableQueue() {
    this.queue = new java.util.LinkedList<T>();
  }

  private ImmutableQueue(java.util.Queue<T> queue) {
    this.queue = new java.util.LinkedList<>(queue);
  }

  @Override
  public Queue<T> enQueue(T t) {
    if (t == null) {
      throw new IllegalArgumentException("null can not be inserted in to the queue.");
    }
    java.util.Queue<T> newQueue = new java.util.LinkedList<>(this.queue);
    newQueue.add(t);
    return new ImmutableQueue<T>(newQueue);
  }

  @Override
  public Queue<T> deQueue() {
    if (this.isEmpty()) {
      throw new IllegalStateException("Queue.deQueue invoked on an empty queue.");
    }
    java.util.Queue<T> newQueue = new java.util.LinkedList<>(this.queue);
    newQueue.remove();
    return new ImmutableQueue<T>(newQueue);
  }

  @Override
  public T head() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Queue is empty.");
    }
    return this.queue.peek();
  }

  @Override
  public boolean isEmpty() {
    return this.queue.isEmpty();
  }

  public int size() {
    return queue.size();
  }
}
