package org.jackJew.thread;

/**
 * 守护线程 与 main主线程
 * @author zhurunjia
 */
public class DaemonRunnable implements Runnable{

	@Override
	public void run() {
		try {
			while(true){
				
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " executing...");
				
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String... args) throws InterruptedException{
		
		for(int i = 0; i < 10; i++){
			Thread dt = new Thread(new DaemonRunnable());
			dt.setDaemon(true);
			dt.start();
		}
		
		System.out.println("all threads started:");
		//修改下面的main线程休眠时间，等待守护线程的执行
		Thread.sleep(1500);		//主线程执行完毕，daemon守护线程自动终止
	}

}
