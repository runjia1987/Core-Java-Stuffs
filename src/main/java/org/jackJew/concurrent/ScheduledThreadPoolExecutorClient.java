package org.jackJew.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor 定时的任务线程执行器
 */
public class ScheduledThreadPoolExecutorClient {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1) {

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
			}

		};
		
		//When submit a task to the executor, it returns you a FutureTask instance,
		//FutureTask.get() will re-throw any exception thrown by the task wrapped in an ExecutorException.

		ExecutorService pool = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		Runnable task = () -> {
			try {
				Thread.sleep(1000L);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("123");
			throw new IllegalArgumentException("test");
		};
		
		pool.execute(task);
		//Future<?> future = pool.submit(task);
		//future.get();
		pool.shutdown();
	}

}
