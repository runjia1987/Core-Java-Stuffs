package org.jackJew.thread.stopSafe;

public class TestClient {

	/**
	 * @throws InterruptedException 
	 * 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		RunningThread rt = new RunningThread(10.0d);
		rt.start();
		
		Thread.sleep(4000);
		rt.setIsRun(false);	//设置共享变量标识
		rt.interrupt();		//调用中断方法
	}

}
