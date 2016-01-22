package org.jackJew.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadUsingCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyCallable mc = new MyCallable("zhurunjia");
		FutureTask<String> task = new FutureTask<String>(mc);
		Thread t = new Thread(task);
		t.start();
		
		System.out.println(task.get());
	}
	
	static class MyCallable implements Callable<String> {
		private String str;
		
		public MyCallable(String str) {
			this.str = str;
		}

		@Override
		public String call() throws Exception {
			Thread.sleep(1000);
			return str;
		}
		
	}

}
