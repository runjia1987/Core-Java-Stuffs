package org.jackJew.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceClient {

	public static void main(String[] args) {
		final ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		
		Runnable task = new Runnable() {
			@Override
			public void run() {  //will swallow exception
				int i = 100 / 0;
				System.out.println("zhurunjia");
			}
		};
		
		final ScheduledFuture<?> sf =
				ses.scheduleAtFixedRate(task, 1500, 1000, TimeUnit.MILLISECONDS);	//间隔1秒
		
		Runnable cancel = new Runnable() {
			@Override
			public void run() {
				sf.cancel(true);	//停止定时任务
				ses.shutdown();		//停止整个service
			}
		};
		ses.schedule(cancel, 10, TimeUnit.SECONDS);	//10秒后停止
		
		try {
			sf.get();
		} catch (Exception e) {
			//When submit a task to the executor, it returns you a FutureTask instance,
			//FutureTask.get() will re-throw any exception thrown by the task as an ExecutorException.
			System.out.println(e.getMessage());
		}
		
	}

}
