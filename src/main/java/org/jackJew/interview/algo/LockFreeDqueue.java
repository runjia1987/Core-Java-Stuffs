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
    Node t;
    while (true) {
      t = tail.get();
      if (t.next.compareAndSet(null, node)) {
        tail.compareAndSet(t, node);  // 允许失败
        return;
      }
    }
  }

  public String poll() {
    Node h;
    while (true) {
      h = head.get();
      Node next = h.next.get();
      if (next == null) { //无元素可用
        try {
          Thread.sleep(100);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      if (next == DELETED) { //上一次head未更新成功
        System.out.println("上一次head未更新成功");
        continue;
      }
      if (h.next.compareAndSet(next, DELETED)) {
        head.compareAndSet(h, next);  // 允许失败
        break;
      }
    }
    return h.value;
  }

  public void iterate() {
    Node node = head.get();
    while (node != null) {
      if (node != pseudo) {
        System.out.println(node.value);
      }
      node = node.next.get();
    }
  }

  public static void main(String[] args) throws Exception {
    LockFreeDqueue deque = new LockFreeDqueue();
    Random rand = new Random();
    Runnable offer = () -> {
      while(true) {
        try {
          Thread.sleep(rand.nextInt(100));
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        deque.offer("abcdefghijklmnopqrstuvwxyz".substring(rand.nextInt(20)));
      }
    };
    Runnable poll = () -> {
      while(true) {
        try {
          Thread.sleep(rand.nextInt(120));
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        System.out.println("poll: " + deque.poll());
      }
    };

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
