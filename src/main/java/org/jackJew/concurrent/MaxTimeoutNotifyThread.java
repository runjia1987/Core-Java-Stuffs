package org.jackJew.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

public class MaxTimeoutNotifyThread implements Runnable {
	final Logger logger = Logger.getLogger(MaxTimeoutNotifyThread.class);

	/**
	 * 测试最大延迟时间自动线程
	 */
	public static void main(String[] args){
		new Thread(new MaxTimeoutNotifyThread()).start();
	}
	
	
	
	public void run() {
		while(true){
			// specified threads to run Task, plus one thread as the timeout-control thread
			final int taskThreadsnumber = 3;
			// max timeout milliseconds
			final int maxTime0ut = 5000;
			final ExecutorService es = Executors.newFixedThreadPool(taskThreadsnumber + 1);
			final List<Future<?>> futureList = new ArrayList<Future<?>>(taskThreadsnumber);
			
			Runnable controlDaemon = () -> {
				try {
					Thread.sleep(maxTime0ut);
				} catch (InterruptedException e) {}
				logger.info("max timeout is reached, shutdown tasks mannually.");
				for(Future<?> f : futureList){
					f.cancel(true);
				}
			};
			
			for(int i = 0; i < taskThreadsnumber; i++){
				futureList.add(es.submit(new Task()));
			}
			es.submit(controlDaemon);
			
			for(Future<?> f : futureList){
				try {
					f.get();
				} catch (Exception e) { }
			}
			es.shutdownNow();
			System.out.println("all tasks have been completed or shutdown.");
		}
	}
	
	class Task implements Runnable {

		@Override
		public void run() {
			// 0~20 seconds
			int waitingTime = new Random().nextInt(20) * 1000;
			String threadName = Thread.currentThread().getName();
			logger.info(threadName + " has to wait " + waitingTime + "ms.");
			
			try {
				Thread.sleep(waitingTime);
				logger.info(threadName + " has completed waiting.");
			} catch(InterruptedException e){
				logger.error(threadName + " has been interrupted.");
			}
		}
		
	}

}
