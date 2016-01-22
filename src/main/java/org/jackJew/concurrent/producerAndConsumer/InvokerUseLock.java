package org.jackJew.concurrent.producerAndConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.lang.StringBuffer;

/**
 * 调用者
 * @author zhurunjia
 */
public class InvokerUseLock {

	/**
	 * 物体集合
	 */
	public static final List<Object> O_LIST = new ArrayList<Object>();
	/**
	 * 最大SIZE
	 */
	public static final int MaxSize = 10;
	/**
	 * 可重入锁
	 */
	public final static Lock lock = new ReentrantLock();
	/**
	 * 充满条件
	 */
	public final static Condition FULL = lock.newCondition();
	/**
	 * 空置条件
	 */
	public final static Condition EMPTY = lock.newCondition();
	
	public static void main(String[] args) {
		ConsumerUseLock c1 = new ConsumerUseLock("C1", lock, FULL, EMPTY, O_LIST);
		ConsumerUseLock c2 = new ConsumerUseLock("C2", lock, FULL, EMPTY, O_LIST);
		ProducerUseLock p1 = new ProducerUseLock("P1", lock, FULL, EMPTY, O_LIST);
		ProducerUseLock p2 = new ProducerUseLock("P2", lock, FULL, EMPTY, O_LIST);
		c1.start();
		c2.start();
		p1.start();
		p2.start();
	}

}
