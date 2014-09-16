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
		}
	}

}
