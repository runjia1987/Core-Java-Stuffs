package org.jackJew.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 先进先出的队列, 基于可重入锁ReentrantLock的并发安全实现, Blocked无限等待方式
 * @author zhurunjia
 */
public class ConcurrentLockQueue<E> {	//JDK java.util.Queue 接口

	private Lock lock = new ReentrantLock();
	private Condition emptyCondition = lock.newCondition();
	
	private ReadWriteLock rwLock = new ReentrantReadWriteLock();
	private Lock readLock = rwLock.readLock();
	private Lock writeLock = rwLock.writeLock();
	
	private LinkedList<E> queue = new LinkedList<E>();
	//JDK LinkedList基于双向链表实现, java.util.LinkedList.Node<E>: prev, next

	public void put(E object) {
		lock.lock();
		try {
			queue.add(object);
			
			if (queue.size() > 0)
				emptyCondition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public E take() throws InterruptedException {	//checked exception
		lock.lock();
		try {
			while (queue.size() == 0)
				emptyCondition.await();

			E element = queue.get(0);
			return element;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ConcurrentLockQueue<String> clq = new ConcurrentLockQueue<String>();

		try {
			//clq.put("123");
			String str = clq.take();
			System.out.println(str);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
