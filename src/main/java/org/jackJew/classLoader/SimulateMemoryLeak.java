package org.jackJew.classLoader;

/**
 * testcase to simulate memory leak
 * @author Jack
 *
 */
public class SimulateMemoryLeak {

	public static void main(String[] args) throws Exception {
		ClassLoader cl = new FileSystemClassLoader("D:\\");
		Class<?> cls = cl.loadClass("org.jackJew.classLoader.Demo");
		cls.newInstance();
		
		cls = null;
		cl = null;
		
		// then repeat
	}

}

class Demo {
	
	static byte[] buffer = new byte[1 << 20];
	static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<Object>(){

		@Override
		protected Object initialValue() {
			return buffer;
		}
		
	};
	
	static {
		System.out.println("static block executed.");
	}
}
