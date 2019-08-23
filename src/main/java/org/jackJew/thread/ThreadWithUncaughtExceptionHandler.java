package org.jackJew.thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
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
		
		ThreadFactory threadFactory = new SpecificThreadFactory();
		ExecutorService es = Executors.newFixedThreadPool(1, threadFactory);
		es.execute(new SpecificThread("test-thread"));
		// if submit(wrapped by FutureTask), no exception is caught by UncaughtExceptionHandler,
    // because exception throwable is set to FutureTask.outcome in catch clause of run() method.
		es.shutdown();
		es.awaitTermination(1000, TimeUnit.SECONDS);
	}

}
/**
 * 自定义的任务线程, 强制抛出一个异常
 * @author zhurunjia
 */
class SpecificThread extends Thread {
	
	public SpecificThread(String name){
		super(name);		
	}
	
	public void run(){
		System.out.println(getName() + " running... ");
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
		t.setUncaughtExceptionHandler(new SpecificUncaughtExceptionHandler());
		
		System.out.println("newThread created.");
		return t;
	}
	
}