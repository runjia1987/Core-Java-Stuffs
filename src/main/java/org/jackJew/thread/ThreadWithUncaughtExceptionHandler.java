package org.jackJew.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadWithUncaughtExceptionHandler {

	/**
	 * javac编译器确保checked exception在run()方法被强制捕获处理;
	 * <br>对于unchecked exception,由于线程有各自独立的栈(JVM虚拟机栈),而异常基于栈,
	 * 因此不能跨线程捕获异常,会一直向外层传播到控制台.
	 * <br>处理办法: 自定义线程工厂,调用setUncaughtExceptionHandler()定义异常处理器,
	 * <br>可以捕获run()方法抛出的unchecked exception
	 */
	public static void main(String... args) throws Exception {
		ExecutorService cachedThreadPool = new ThreadPoolExecutor(10, 10,
				0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5),
				new ThreadFactoryBuilder().setNameFormat("stakingClearing-pool-%d").build(),
				new ThreadPoolExecutor.CallerRunsPolicy());

		for (int i = 0; i < 20; i++) {
			cachedThreadPool.submit(new SpecificThread(UUID.randomUUID().toString()));
		}
		cachedThreadPool.shutdown();
		while(!cachedThreadPool.isTerminated()) { }

//		ThreadFactory threadFactory = new SpecificThreadFactory();
//		ExecutorService es = Executors.newFixedThreadPool(1, threadFactory);
//		es.execute(new SpecificThread("test-thread"));
//		es.submit(new SpecificThread("test-thread"));
//		// if submit(wrapped by FutureTask), no exception is caught by UncaughtExceptionHandler,
//    // because exception throwable is set to FutureTask.outcome in catch clause of run() method.
//		es.shutdown();
//		es.awaitTermination(1000, TimeUnit.SECONDS);
	}

}

class Task implements Runnable {

	@Override
	public void run() {
		System.out.println("start task " + Thread.currentThread().getId());
		try {
			Thread.sleep(new Random().nextInt(3000));
		} catch (Exception ex) {
			ex.getMessage();
		}
		System.out.println("end task " + Thread.currentThread().getId());
	}
}
/**
 * 自定义的任务线程, 强制抛出一个异常
 * @author zhurunjia
 */
class SpecificThread implements Runnable {
	private String taskName;

	public SpecificThread(String name) {
		this.taskName = name;
	}

	public void run(){
		try {
			Thread.sleep(new Random().nextInt(3000));
		} catch (Exception ex) {
			ex.getMessage();
		}
		System.out.println("task: " + taskName + " running in " + Thread.currentThread().getName());
		throw new RuntimeException("强制抛出的一个测试异常");
	}

}
/**
 * 未捕获异常处理类
 * @author zhurunjia
 */
class SpecificUncaughtExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("捕获到 : " + t.getName() + ", " + e.getMessage());
	}

}
/**
 * 实现线程工厂接口的自定义工厂
 * @author zhurunjia
 */
class SpecificThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		System.out.println("newThread creating...");
		//构造普通线程
		Thread t = new Thread(r);
		//为该线程设置自定义的未捕获异常处理器
		//t.setUncaughtExceptionHandler(new SpecificUncaughtExceptionHandler());

		System.out.println("newThread created.");
		return t;
	}

}
