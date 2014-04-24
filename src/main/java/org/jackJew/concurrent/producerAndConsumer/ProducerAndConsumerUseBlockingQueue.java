package org.jackJew.concurrent.producerAndConsumer;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用BlockingQueue实现生产者, 消费者问题
 * @author zhurunjia
 */
public class ProducerAndConsumerUseBlockingQueue {
	
	private final int Max_Size = 10;
	/**
	 *  Throws exception 		Special value 	Blocked				Waiting Time out 	<br>
		 	add(e) 				offer(e) 		put(e) 				offer(e, time, unit)<br> 
			remove() 			poll() 			take() 				poll(time, unit) 	<br>
			element() 			peek() 			not applicable 		not applicable 		<br>
	 * <br>
	 * Throws exception与Special value的六个方法定义可参考java.util.Queue 接口
	 */
	private final BlockingQueue<Object> O_QUEUE = new LinkedBlockingQueue<Object>(Max_Size);

	class Producer extends Thread {
		
		private String name;
		
		public Producer(String name){
			this.name = name;
		}
		
		public void run(){
			boolean success;
			while(true){
				Object o = new Object();
				try {
					success = O_QUEUE.offer(o);
					if(success)
						System.out.println("生产者" + name + "已生产: " + o);
					
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("生产者异常!");
				}
			}
		}
	}
	
	class Consumer extends Thread {
		private String name;
		
		public Consumer(String name){
			this.name = name;
		}
		
		public void run(){
			Object o;
			while(true){
				try {
					o = O_QUEUE.poll();
					if(o != null)
						System.out.println("消费者" + name + "消费了: " + o);
					
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("消费者异常!");
				}				
			}
		}
	}
	
	public static void main(String[] args) {
		ProducerAndConsumerUseBlockingQueue pcub = new ProducerAndConsumerUseBlockingQueue();
		Producer p1 = pcub.new Producer("P1");
		Producer p2 = pcub.new Producer("P2");
		Consumer c1 = pcub.new Consumer("C1");
		Consumer c2 = pcub.new Consumer("C2");
		Consumer c3 = pcub.new Consumer("C3");
		p1.start();
		p2.start();
		c1.start();
		c2.start();
		c3.start();
	}

}