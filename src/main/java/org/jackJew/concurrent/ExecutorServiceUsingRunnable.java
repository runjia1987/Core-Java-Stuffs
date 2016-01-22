package org.jackJew.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.util.StopWatch;

/**
 * ExecutorSerice 与 Runnable
 * 
 * @author zhurunjia
 */
public class ExecutorServiceUsingRunnable {

	public static void main(String... args) {
		final int NUMBER = 5;
		ExecutorService es = Executors.newCachedThreadPool();
		// ExecutorService es = Executors.newFixedThreadPool(1); //此处实际上为单线程顺序执行
		
		CountDownLatch latch = new CountDownLatch(NUMBER);
		StopWatch watch = new StopWatch();
		watch.start();
		
		try {
			for (int j = 1; j <= NUMBER; j++)
				es.execute(new Job(j * 100, latch));
		} finally {
			es.shutdown();
		}
		
		try {
			latch.await();
			watch.stop();
			System.out.println("time elapsed: " + watch.getLastTaskTimeMillis() + "ms.");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

}

/**
 * job 例子
 * 
 * @author zhurunjia
 */
class Job implements Runnable {

	private int number = 0;
	private CountDownLatch latch;

	/**
	 * constructor
	 * 
	 * @param _number
	 */
	public Job(int _number, CountDownLatch latch) {
		this.number = _number;
		this.latch = latch;
	}

	public Job() {
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("job " + number++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.latch.countDown();
	}
}