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
		
		/**
		 * certainly this is not safe, just for example.
		 * @return
		 */
		public static Singleton getIntsance(){
			if (instance == null) {
				instance = new Singleton("a", "b");
				return instance;
			}
			return instance;
		}
	}
	
	class AccessByGet implements Runnable {

		@Override
		public void run() {
			Singleton s = Singleton.getIntsance();
			Thread t = Thread.currentThread();
			System.out.println(t + ", " + s.fieldA);
			System.out.println(t + ", " + s.fieldB);
		}

	}
	
	class AccessDirect implements Runnable {

		@Override
		public void run() {
			Singleton s = Singleton.instance;
			Thread t = Thread.currentThread();
			System.out.println(t + ", " + s.fieldA);
			System.out.println(t + ", " + s.fieldB);
		}
		
	}
	
	public static void main(String[] args){
		Jsr133FinalTest test = new Jsr133FinalTest();
		Runnable r1 = test.new AccessByGet();
		Runnable r2 = test.new AccessDirect();
		
		int maxThreadsSize = 20, i = 0;
		Thread[] threads = new Thread[maxThreadsSize];
		
		
		while(i < maxThreadsSize) {
			if ( i % 2 == 0) {
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
