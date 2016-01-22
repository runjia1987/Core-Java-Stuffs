package org.jackJew.interview;

public class ThreadOverride extends Thread {
	
	@Override
	public void start(){  // this is allowed to override
		System.out.println("override Thread start().");
	}
	
	
	@Override
	public void run(){
		System.out.println("run() 1.");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("run() 2.");		
	}

	public static void main(String[] args) throws Exception {
		Thread t = new ThreadOverride();
		//t.run();   // different result for output order
		t.start();
		//t.join();
		
		System.out.println("end.");
	}

}
