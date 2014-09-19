package org.jack.jsr.testcase;

/**
 * test "final" keyword in jsr 133
 * @author runjia
 *
 */
public class Jsr133FinalTest {
	
	/**
	 * define a sample Singleton class
	 * @author zhurunjia
	 *
	 */
	private static class Singleton {
		
		private final String fieldA;   // a willbe safely published
		private String fieldB;	// b is not promised to be 
		
		public Singleton(String a, String b){
			this.fieldA = a;
			this.fieldB = b;
		}
		
		
		private static volatile Singleton instance;
		
		public static void createIntsance(){
			if (instance == null) {
				instance = new Singleton("a", "b");
			}
		}
	}
	
	class Create implements Runnable {

		@Override
		public void run() {
			Singleton.createIntsance();
		}

	}
	
	class Access implements Runnable {

		@Override
		public void run() {
			Singleton s = Singleton.instance;
			if(s != null) {
				Thread t = Thread.currentThread();
				System.out.println(t + ", " + s.fieldA);
				System.out.println(t + ", " + s.fieldB);
			}
		}
		
	}
	
	public static void main(String[] args){
		Jsr133FinalTest test = new Jsr133FinalTest();
		Runnable r1 = test.new Create();
		Runnable r2 = test.new Access();
		
		int maxThreadsSize = 20, i = 0;
		Thread[] threads = new Thread[maxThreadsSize];
		
		
		while(i < maxThreadsSize) {
			if ( i == 0) {
				threads[i++] = new Thread(r1);
			} else {
				threads[i++] = new Thread(r2);
			}
		}
		
		for (Thread t : threads) {
			t.start();
		}
		
	}

}
