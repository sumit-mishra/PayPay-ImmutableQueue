package com.paypay.immutableq;

import com.paypay.immutableq.impl.ImmutableQueue;
import com.paypay.immutableq.impl.ImmutableQueueAL;
import com.paypay.immutableq.intrface.Queue;
import org.junit.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

public class ImmutableQueueALTest {

  private static Queue initializeQueueWithTwoElements() {
    Queue immutableQueue = new ImmutableQueueAL();
    immutableQueue = immutableQueue.enQueue("Japan");
    immutableQueue = immutableQueue.enQueue("India");
    return immutableQueue;
  }

  @Test
  public void testEnQueueWithValidInput() {
    Queue<String> immutableQueue = initializeQueueWithTwoElements();
    Queue<String> immutableQueueEnQueued = immutableQueue.enQueue("Canada");
    assertEquals("queue must NOT be empty.", false, immutableQueue.isEmpty());
    assertEquals("queue must NOT be empty.", false, immutableQueueEnQueued.isEmpty());
    assertNotEquals("Both are 2 different instances.", immutableQueue, immutableQueueEnQueued);
    assertEquals("enQueue of an element has increased the size by 1.",
                 ((ImmutableQueueAL) immutableQueue).size() + 1,
                 ((ImmutableQueueAL) immutableQueueEnQueued).size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEnQueueWithNullValue() {
    Queue<String> immutableQueue = initializeQueueWithTwoElements();
    Queue<String> immutableQueueEnQueued = immutableQueue.enQueue(null);
  }

  @Test(expected = IllegalStateException.class)
  public void testDeQueueWithNoElementsInTheQueue() {
    final ImmutableQueueAL immutableQueue = new ImmutableQueueAL();
    assertEquals("queue must be empty.", true, immutableQueue.isEmpty());
    immutableQueue.deQueue();
  }

  @Test
  public void testDeQueueWithValidInput() {
    Queue<String> immutableQueue = initializeQueueWithTwoElements();
    assertEquals("queue must NOT be empty.", false, immutableQueue.isEmpty());

    Queue<String> immutableQueueEnQueued = immutableQueue.deQueue();
    assertNotEquals("Both are 2 different instances.", immutableQueue, immutableQueueEnQueued);
    assertEquals("enQueue of an element has decreased the size by 1.",
                 ((ImmutableQueueAL) immutableQueue).size() - 1,
                 ((ImmutableQueueAL) immutableQueueEnQueued).size());
  }

  @Test
  public void testHeadInTheQueue() {
    final Queue<String> immutableQueue = initializeQueueWithTwoElements();
    assertEquals("value must match with the element of initialized queue.", immutableQueue.head(),
                 "Japan");
  }

}
