package org.jackJew.interview.algo;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Lock free double-linked queue.
 */
public class LockFreeDqueue {
  private final Node DELETED = new Node(null);
  private final Node pseudo = new Node(null);
  private final AtomicReference<Node> head = new AtomicReference<>(pseudo);
  private final AtomicReference<Node> tail = new AtomicReference<>(pseudo);

  static class Node {
    String value;
    AtomicReference<Node> next;

    public Node(String val) {
      value = val;
      next = new AtomicReference<>(null);
    }

    @Override
    public String toString() {
      return value;
    }
  }

  public void offer(String element) {
    Node node = new Node(element);
    Node old;
    while (true) {
      old = tail.get();
      if (old.next.compareAndSet(null, node)) {
        tail.compareAndSet(old, node);  // 允许失败
        return;
      }
    }
  }

  public String poll() {
    if (head.get() == null)
      return null;

    Node old;
    while (true) {
      old = head.get();
      Node next = old.next.get();
      if (next == DELETED) {
        continue;
      }
      if (old.next.compareAndSet(next, DELETED)) {
        head.compareAndSet(old, next);  // 允许失败
        break;
      }
    }
    old.next = null; // help GC
    return old.value;
  }

  public void iterate() {
    Node node = head.get();
    while (node != null) {
      System.out.println(node.value);
      node = node.next.get();
    }
  }

  public static void main(String[] args) throws Exception {
    LockFreeDqueue deque = new LockFreeDqueue();
    Random rand = new Random();
    Runnable offer = () -> deque.offer("abcdefghijklmnopqrstuvwxyz".substring(rand.nextInt(20)));
    Runnable poll = () -> System.out.println("poll: " + deque.poll());

    Thread t1 = new Thread(offer);
    Thread t2 = new Thread(offer);
    Thread t3 = new Thread(offer);
    Thread t4 = new Thread(poll);
    Thread t5 = new Thread(poll);
    Thread t6 = new Thread(poll);

    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    t6.start();

    t1.join();
    t2.join();
    t3.join();
    t4.join();
    t5.join();
    t6.join();

    deque.iterate();
  }

}
