package org.jackJew.designPattern;

public class SingletonMultithread {

	/**
	 * test
	 */
	public static void main(String[] args) {
		Task task = new Task();
		Thread[] threads = new Thread[10];
		
		for(int i = 0; i < 10; i++){
			Thread t = new Thread(task);
			threads[i] = t;
		}
		
		for(Thread t : threads){
			t.start();
		}
	}
	
	static class Task implements Runnable {

		@Override
		public void run() {
			UnsafeSingleton.getInstance();  //call getInstance, will show that's unsafe !!!!
			Singleton1.getInstance();
			Singleton2.getInstance();
			Singleton3.getInstance();
			throw new IllegalArgumentException("for test");   // unchecked exception.
		}
		
	}

}

class UnsafeSingleton {
	
	private static UnsafeSingleton instance  = null;
	
	private UnsafeSingleton(){
		try {
			Thread.sleep(100);
		} catch(Exception e){ }
		
		System.out.println("UnsafeSingleton constructed.");
	}
	
	public static UnsafeSingleton getInstance(){
		if(instance == null){
			instance = new UnsafeSingleton();
			return instance;
		} else {
			return instance;
		}
	}
}
