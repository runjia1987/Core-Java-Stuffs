package test.multithread.output;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadOutput {
	
	public static String[] strings;
	
	public final static ReentrantLock lock = new ReentrantLock();
	public final static Condition A_condition = lock.newCondition();
	public final static Condition l_condition = lock.newCondition();
	public final static Condition i_condition = lock.newCondition();
	
	public static int maxTimes;
	public volatile static int timesCount = 0;
	public volatile static int stringCount = 0;

	public static void main(String[] args) throws InterruptedException {
		
		ThreadOne threadOne = new ThreadOne(10);   // restrict how many Ali
		maxTimes = threadOne.getTimes();
		strings = new String[3 * threadOne.getTimes()];
		
		ThreadTwo threadTwo = new ThreadTwo();
		ThreadThree threadThree = new ThreadThree();
		
		threadOne.start();
		threadTwo.start();
		threadThree.start();
		
		threadOne.join();		
		threadTwo.join();
		threadThree.join();
		System.out.println(Arrays.toString(strings));
	}

}
