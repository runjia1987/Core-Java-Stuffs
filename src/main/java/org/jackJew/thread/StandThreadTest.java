package org.jackJew.thread;

/**
 * 线程方法测试 sleep join wait
 * @author zhurunjia
 */
public class StandThreadTest implements Runnable {

	private final String name;
	private final Object sync;
	private boolean isRunning = false;

	public StandThreadTest(String name, Object sync) {
		this.name = name;
		this.sync = sync;
	}

	public void run() {
		synchronized (sync) {
			if (!isRunning) {
				isRunning = true;
				try {
					sync.wait(1000);
					// System.out.println(name + " Running .......");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(name + " Running .......");
		}
	}

	public void notifySelf() {
		synchronized (sync) {
			System.out.println(" -------- notify the thread " + name);
			sync.notify();
		}
	}

	public void notifySelf2() {
		synchronized (this) {
			// do something...
			notify();
		}
	}

	public static void notifySelf3() {
		synchronized (StandThreadTest.class) {
			// do something...
		}
	}

	public static void main(String[] args) {
	
		MyThread2 mythread = new MyThread2("zhurunjia");
		mythread.start();
		
		try {
			//main线程与mythread线程：调用mythread.join()，main线程挂起，
			//等待mythread线程执行结束，再恢复执行
			mythread.join();
			//可以在join方法中带超时参数，当mythread在超时时间段内未执行完，确保总能返回
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("继续执行, this is main thread");
		
		/*
		Runner run = new Runner();
		Thread thread1 = new Thread(run, "线程1");
		Thread thread2 = new Thread(run, "线程2");
		thread1.start();
		thread2.start();
		*/
		
		//System.out.println("Math.round(-11.4d): " + Math.round(-11.4d));
		//System.out.println("Math.floor(-11.4d): " + Math.floor(-11.4d));
		
	}
}

/**
 * 演示sleep()方法 * 
 * @author zhurunjia
 * 
 */
class Runner implements Runnable {
	public void run() {
		for (int i = 0; i < 50; i++) {
			if (i % 10 == 0 && i > 0) {
				try {
					System.out.println("——————————即将sleep————————————" + i
							+ " 所属线程 " + Thread.currentThread().getName());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("——————————线程————————————" + i + " 所属线程 "
					+ Thread.currentThread().getName());
		}
	}
}

class MyThread2 extends Thread {	

	public MyThread2(String string) {
		super(string);
	}

	public void run() {
		for (int i = 1; i <= 5; i++) {
			try {
				//int x = 1 / 0; 
				sleep(1000);  //阻塞1秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("sleep 1秒钟, I am " + getName());
		}
	}
}