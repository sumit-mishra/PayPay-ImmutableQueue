package com.paypay.immutableq.impl;

import com.paypay.immutableq.intrface.CloneableQ;
import com.paypay.immutableq.intrface.Queue;

import java.util.ArrayList;
import java.util.List;

public class ImmutableQueueAL<T> implements Queue<T> {
  //ImmutableQueueAL<T extends CloneableQ<T>> implements Queue<T>
  /**
   * @author - sumit mishra
   * ImmutableQueueAL - AL in the class name stands for ArrayList for the used dataStructure.
   *
   * The plan was to implement something like -> ImmutableQueueAL<T extends CloneableQ<T>> implements Queue<T>
   * where CloneableQ<T> is an implementation of Cloneable interface
   * and targeted to clone the items of queue to make it strictly immutable (e.g - item.clone())
   * whenever there is any change in the queue.
   *
   * Also to implement logic for head() independent of the default functionality given in the data structure.
   */
  private T head;
  private List<T> existingQ;
  private int size;

  public ImmutableQueueAL(T element) {
    this.head = element;
    this.existingQ = new ArrayList<>();
    this.size = 1;
  }

  public ImmutableQueueAL() {
    this.head = null;
    this.existingQ = new ArrayList<>();
    this.size = 0;
  }

  private ImmutableQueueAL(T head, List<T> queue) {
    this.head = head;
    this.existingQ = queue;
    this.size = this.existingQ.size() + 1;
  }

  @Override
  public Queue<T> enQueue(T element) {
    if (element == null) {
      throw new IllegalArgumentException("null can not be inserted in to the queue.");
    }
    if (this.isEmpty()) {
      return new ImmutableQueueAL<T>(element);
    } else if (this.size == 1) {
      List<T> newQ = new ArrayList<>();
      newQ.add(element);
      return new ImmutableQueueAL<>(head(), newQ);
    }
    List<T> existingElements = new ArrayList<>();
    this.existingQ.forEach(item -> existingElements.add(item));
    existingElements.add(element);
    return new ImmutableQueueAL<>(head(), existingElements);
  }

  @Override
  public Queue<T> deQueue() {
    if (this.isEmpty()) {
      throw new IllegalStateException("Queue.deQueue invoked on an empty queue.");
    } else if (this.size == 1) {
      return new ImmutableQueue<>();
    } else if (this.size == 2) {
      return new ImmutableQueueAL<>(this.existingQ.get(0));
    }
    return new ImmutableQueueAL<>(this.existingQ.get(0),
                                  this.existingQ.subList(1, this.existingQ.size() - 1));
  }

  @Override
  public T head() {
    return this.head;
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  public int size() {
    return this.size;
  }

}
