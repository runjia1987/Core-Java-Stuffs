package org.jackJew.thread.stopSafe;

public class RunningThread extends Thread {
	
	private volatile boolean isRun = true;
	private double result = 0.0d;
	
	public RunningThread(double d){
		this.result = d;
	}
	
	public void setIsRun(boolean b){
		this.isRun = b;
	}

	@Override
	public void run() {
		while(isRun) {
			if(result > 1000)
				result = 90.0d;
			else
				result *= result;
			System.out.println(result);
			
			//int x = 1 / 0;	//unchecked exception会导致本线程终止, terminated
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {	//捕获异常 (checked Exception)
				System.out.println("catch an InterruptedException!");
			}
		}
	}

}
