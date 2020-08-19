package org.jackJew.interview.algo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 阻塞队列实现(基于synchronized锁),更佳实现为ReentrantLock和Condition机制.
 */
public class BlockingQueueBySync {

  private final Queue<Object> queue = new LinkedList<>();
  private final int MAX_SIZE = 5;

  public Object take() throws Exception {
    synchronized (queue) {
      while (queue.isEmpty()) {
        System.out.println("waiting...");
        queue.wait();
      }
      Object obj = queue.poll();
      queue.notifyAll();
      return obj;
    }
  }

  public void put(Object element) throws Exception {
    synchronized (queue) {
      while (queue.size() >= MAX_SIZE) {
        queue.wait();
      }
      queue.offer(element);
      queue.notifyAll();
    }
  }

  public static void main(String[] args) {
    BlockingQueueBySync sync = new BlockingQueueBySync();
    final Random random = new Random();
    Thread t1 = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(random.nextInt(500));
          sync.put(new Object());
          System.out.println("success put");
        } catch(Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    Thread t2 = new Thread(() -> {
      while (true) {
        try {
          System.out.println("take " + sync.take());
          Thread.sleep(random.nextInt(300));
        } catch(Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    t1.start();
    t2.start();
  }
}
