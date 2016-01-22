package org.jackJew.concurrent.producerAndConsumer;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 生产者
 * @author zhurunjia
 */
public class ProducerUseLock extends Thread{
	
	private String name;
	private Lock lock;
	private Condition FULL;
	private Condition EMPTY;
	private List<Object> O_LIST;
	
	public ProducerUseLock(String name, Lock lock,
					Condition full, Condition empty, List<Object> list){
		this.name = name;
		this.lock = lock;
		this.FULL = full;
		this.EMPTY = empty;
		this.O_LIST = list;
	}

	@Override
	public void run() {
		while(true){
			lock.lock();
			try {
				while(O_LIST.size() == InvokerUseLock.MaxSize)
					FULL.await();	//已满, 生产者放弃锁
				
				Object o = new Object();
				if(O_LIST.add(o)){
					System.out.println("生产者" + name + "生产了: " + o);
					Thread.sleep(500);
					EMPTY.signalAll();	//通知被空置条件阻塞的消费者
				}
				
			} catch (Exception e) {
				System.out.println("生产者异常!");
			} finally {
				lock.unlock();
			}
			
		}
	}

}