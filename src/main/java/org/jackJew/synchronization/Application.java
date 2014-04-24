package org.jackJew.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class Application {

	/**
	 * 注意：volatile关键字确保修饰变量的可见性visibility, 
	 * 		消除内存的本地线程缓存和编译器优化(指令重排序reordering)等反常行为;
	 * 		synchronized关键字确保修饰变量的原子性atomicity和visibility.
	 */
	
	public static int num = 0;
	public static AtomicInteger num2 = new AtomicInteger(0);
	public final static Object ob = new Object();

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		
		Thread[] threads = new Thread[300];

		int i = 0;
		while (i < 300) {
			Thread job = new Thread(new IncrementUseLock());
			//Thread job = new Thread(new IncrementUseConcurrentType());
			threads[i] = job;
			i++;
		}
		
		for(Thread t: threads)
			t.start();
		
		Thread.sleep(1000);
		System.out.println("value:" + num2 + ", Time elapsed(ms): "
					+ (System.currentTimeMillis() - start));

	}

}
