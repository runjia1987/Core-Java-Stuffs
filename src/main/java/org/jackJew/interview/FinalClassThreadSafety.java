package org.jackJew.interview;

import java.util.Date;

class TestClientForFinalClass {
	
	public static void main(String... args){
		Runnable task = () -> FinalClassThreadSafety.getInstance();
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
	
	private static String instance;
	
	public static String getInstance() {
		if(instance == null){
			instance = new String("123");
		}
		return instance;
	}
	
}
class FinalClassThreadSafety2 extends FinalClassThreadSafety {
	
	public static String getInstance() {
		return null;
	}
	
	public static void main(String[] args) {
		FinalClassThreadSafety f = new FinalClassThreadSafety2();
		System.out.println(f.getInstance());
	}
}