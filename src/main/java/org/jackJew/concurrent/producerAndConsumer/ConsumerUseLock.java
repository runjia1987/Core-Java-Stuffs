package org.jackJew.concurrent.producerAndConsumer;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 消费者
 * @author zhurunjia
 */
public class ConsumerUseLock extends Thread {
	
	private String name;
	private Lock lock;
	private Condition FULL;
	private Condition EMPTY;
	private List<Object> O_LIST;
	
	public ConsumerUseLock(String name, Lock lock,
					Condition full, Condition empty, List<Object> list){
		this.name = name;
		this.lock = lock;
		this.FULL = full;
		this.EMPTY = empty;
		this.O_LIST = list;
	}

	public void run() {
		while(true){
			lock.lock();
			try {
				while(O_LIST.size() == 0)
					EMPTY.await();	//已空, 释放锁
				
				Object o = O_LIST.remove(0);
				if(o != null) {
					System.out.println("消费者" + name + "消费了：" + o);
					Thread.sleep(500);
					FULL.signalAll();	//通知被充满条件阻塞的生产者
				}
				
			} catch(Exception e){
				System.out.println("消费者异常!");
			} finally {
				lock.unlock();
			}
		}
	}

}
