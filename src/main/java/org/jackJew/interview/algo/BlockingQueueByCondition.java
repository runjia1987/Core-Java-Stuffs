package org.jackJew.interview.algo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列实现(ReentrantLock和Condition机制).
 */
public class BlockingQueueByCondition {

    private final Queue<Object> queue = new LinkedList<>();
    private int size = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final int MAX_SIZE = 5;

    public Object take() throws Exception {
        lock.lock();
        try {
            while (size == 0) {
                notEmpty.await();
            }
            Object result = queue.poll();
            size--;
            notFull.signalAll();
            return result;
        } finally {
            lock.unlock();
        }
    }

    public void put(Object element) throws Exception {
        lock.lock();
        try {
            while (size >= MAX_SIZE) {
                notFull.await();
            }
            queue.offer(element);
            size++;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockingQueueByCondition condition = new BlockingQueueByCondition();
        final Random random = new Random();
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " try put");
                    Thread.sleep(random.nextInt(100));
                    condition.put(new Object());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        Thread t1_1 = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " try put");
                    Thread.sleep(random.nextInt(100));
                    condition.put(new Object());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " take " + condition.take());
                    Thread.sleep(random.nextInt(100));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        Thread t2_2 = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " take " + condition.take());
                    Thread.sleep(random.nextInt(100));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        t1.start();
        t1_1.start();
        t2.start();
        t2_2.start();
    }
}
