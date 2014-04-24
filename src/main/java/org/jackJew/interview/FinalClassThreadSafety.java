package org.jackJew.interview;

import java.util.Date;

class TestClientForFinalClass {
	
	public static void main(String... args){
		Runnable task = new Runnable() {
			public void run() {
				FinalClassThreadSafety.getInstance();
			}
		};
		Thread[] threads = new Thread[10];
		for(int i = 0; i < 10;i++){
			threads[i] = new Thread(task);
		}
		for(Thread t :  threads){
			t.start();
		}
	}
}

public class FinalClassThreadSafety {
	
	private static FinalDesign instance;
	
	public static FinalDesign getInstance(){  //final design does not guarantee safety.
		if(instance == null){
			instance = new FinalDesign();
		}
		return instance;
	}
	
	final static class FinalDesign {
		private final Date d;
		
		public FinalDesign(){
			this.d = new Date();
			System.out.println("FinalDesign instance created.");
		}
		
	}
}
