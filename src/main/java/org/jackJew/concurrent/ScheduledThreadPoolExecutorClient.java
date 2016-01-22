package org.jackJew.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor 定时的任务线程执行器
 */
public class ScheduledThreadPoolExecutorClient {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1) {

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
			}

		};
		
		//When submit a task to the executor, it returns you a FutureTask instance,
		//FutureTask.get() will re-throw any exception thrown by the task as an ExecutorException.

		ScheduledFuture<?> future = scheduler.scheduleAtFixedRate( () -> {
				//will swallow exception
				int i = 1 / 0;
				System.out.println("1234567890");
			}, 1, 1, TimeUnit.SECONDS); // 1秒后执行
		
		try {
			future.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
