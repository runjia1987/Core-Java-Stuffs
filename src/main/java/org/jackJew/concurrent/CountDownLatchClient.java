package org.jackJew.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.Timer; // thread-safe
import java.util.Random; // thread-safe

/**
 * 使用 CountDownLatch 让一个或多个线程等待另一组其他线程正在执行的线程执行完毕.
 * <br> 可用在问题分解和任务分解的处理上(divided into N parts)
 * @author zhurunjia
 */
public class CountDownLatchClient {
	
	final static int racer_number = 5;
	final static Random rand = new Random();
	
	private static class RunJob implements Runnable {		
		private CountDownLatch start;
		private CountDownLatch end;
		
		public RunJob(CountDownLatch start, CountDownLatch end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public void run() {
			long startTime = System.currentTimeMillis();
			try {
				start.await();
				System.out.println(Thread.currentThread().getName() + "开始跑了.");
				Thread.sleep(rand.nextInt(5000));
				System.out.println(Thread.currentThread().getName()
						+ "跑到终点了, 花费" + (System.currentTimeMillis() - startTime) + "ms.");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				end.countDown();
			}
			
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch endLatch = new CountDownLatch(racer_number);
		ExecutorService es = Executors.newFixedThreadPool(racer_number); //线程数
		
		for(int i=0; i<racer_number; i++)
			es.submit(new RunJob(startLatch, endLatch));
		
		
		System.out.println("可以开始赛跑了:");
		startLatch.countDown();
		endLatch.await();
		System.out.println("都跑到了终点, 比赛结束!");
		es.shutdown();
		
	}

}
