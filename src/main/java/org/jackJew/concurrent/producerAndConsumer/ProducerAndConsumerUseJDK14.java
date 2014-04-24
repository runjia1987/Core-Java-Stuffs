package org.jackJew.concurrent.producerAndConsumer;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用JDK 1.4的标准wait, notifyAll实现的生产者消费者scenario
 * @author zhurunjia
 */
public class ProducerAndConsumerUseJDK14 {
	
	private final Object obj = new Object();
	private final List<Object> O_LIST = new LinkedList<Object>();
	private final int SIZE = 10;
	
	class Producer implements Runnable {
		private String name;
		
		public Producer(String name){
			this.name = name;
		}
		
		@Override
		public void run() {
			while(true){
				synchronized (obj) {
					try {
						/**
						 * 在《Effective Java》中Bloch建议：“永远不要在循环之外调用wait()方法“;
						 * 因为被唤醒时并不意味着等待的条件一定是满足了的，有可能其他线程错误的调用了notify()
						 */
						while(O_LIST.size() == SIZE)
							obj.wait();
						
						Object o = new Object();
						if(O_LIST.add(o)){
							System.out.println("生产者" + name + "生产了: " + o);
							Thread.sleep(500);
							obj.notifyAll();
							
						}
						
					} catch(Exception e){
						System.out.println("生产者异常!");
					}
					
				}
			}
		}
		
	}
	
	class Consumer implements Runnable {
		private String name;
		
		public Consumer(String name){
			this.name = name;
		}

		@Override
		public void run() {
			while(true){
				synchronized(obj){
					try {
						while(O_LIST.size() == 0)
							obj.wait();
						
						Object o = O_LIST.remove(0);
						if(o != null){
							System.out.println("消费者" + name + "消费了: " + o);
							Thread.sleep(500);
							obj.notifyAll();
							//System.out.println("Consumer giveup lock;");
						}
						
					} catch(Exception e){
						System.out.println("消费者异常!");
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	public static void main(String[] args) {
		ProducerAndConsumerUseJDK14 pcuj = new ProducerAndConsumerUseJDK14();
		Thread p1 = new Thread(pcuj.new Producer("P1"));
		Thread p2 = new Thread(pcuj.new Producer("P2"));
		Thread c1 = new Thread(pcuj.new Consumer("C1"));
		Thread c2 = new Thread(pcuj.new Consumer("C2"));
		
		p1.start();
		p2.start();
		c1.start();
		c2.start();
	}

}

