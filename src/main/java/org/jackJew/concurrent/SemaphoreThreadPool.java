package org.jackJew.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量 Semaphore 的多线程方式, 通过发放令牌/令牌限制争用共享资源的线程数目. 
 * @author zhurunjia
 */
public class SemaphoreThreadPool {
	
	final static class Pool {
		
		private final int MAX_NUMBER = 3;
		private Object[] items = new Object[MAX_NUMBER];  //保存资源
		/**
		 * Semaphores are often used to restrict the number of threads than can access 
		 * <br> some (physical or logical) resource. 
		 * <br> For example, use a semaphore to control access to 资源
		 */
		private final Semaphore sem = new Semaphore(MAX_NUMBER); //第二个参数为是否使用公平的FIFO模式

		public Pool(){
			for(int i = 0; i< MAX_NUMBER; i++)
				items[i] = new Object(); // init
		}
		
		/**
		 * 提取资源
		 */
		public Object getItem() {
			System.out.println(Thread.currentThread().getName() + "尝试请求一个令牌....");
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				return null;
			}
			System.out.println(Thread.currentThread().getName() + "获得一个令牌.");
			return getAvailableItem();
		}
		
		/**
		 * 归还资源
		 */
		public void putItem(Object obj){
			if(obj == null) return;
			returnItem(obj);
			sem.release();
			System.out.println(Thread.currentThread().getName() + "已释放一个令牌.");
		}
		
		synchronized Object getAvailableItem(){	 //注意要确保同步
			Object obj;
			for(int i = 0; i < MAX_NUMBER; i++){
				if((obj = items[i]) != null) {
					items[i] = null;	//置空值
					System.out.println("资源" + obj + "被取走.");
					return obj;
				}
			}
			return null;
		}
		
		synchronized void returnItem(Object obj){  //注意要确保同步
			for(int i = 0; i < MAX_NUMBER; i++){
				if(items[i] == null){
					System.out.println("资源" + obj + "被归还.");
					items[i] = obj;
					break;	 //重要!!!
				}
			}
		}
	}
	
	public static void main(String[] args) {
		final Pool pool = new Pool();	//局部变量和参数必须有final修饰
		Random rand = new Random();
		//构造runnable对象
		Runnable runnable = () -> {
			while (true) {
				Object obj = pool.getItem();
				if (obj != null) {
					System.out.println("对取到的资源进行操作.....");
					try {
						Thread.sleep(rand.nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("对取到的资源操作结束.");
				}
				pool.putItem(obj);
			}
		};

		ExecutorService es = Executors.newFixedThreadPool(5);
		//线程池的数目(5)大于令牌的数目(3), 以观察测试效果
		for(int i = 0; i < 5; i++) {
			es.submit(runnable);
		}
		es.shutdown();
	}
	
}