package org.jackJew.thread;

/**
 * 守护线程 不一定确保执行finally
 * @author zhurunjia
 */
public class DaemonThreadWithFinally implements Runnable {

	/**
	 * @throws InterruptedException 
	 * 
	 */
	public static void main(String... args) throws InterruptedException {
		Thread dhf = new Thread(new DaemonThreadWithFinally());
		dhf.setDaemon(true);
		dhf.start();
		System.out.println("Daemon thread started...");
		
		//必须使主线程休眠一段时间等待守护线程执行
		Thread.sleep(1000);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1200);
			//System.exit(0);	//也可导致finally块不被执行
		} catch(InterruptedException ie){
			ie.printStackTrace();
		} finally {
			//由于守护线程的生命周期取决于主线程, 主线程结束后守护线程直接结束, finally语句块未被执行
			System.out.println("finally block executed.");
		}
	}

}
