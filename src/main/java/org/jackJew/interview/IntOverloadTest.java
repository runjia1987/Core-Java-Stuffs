package org.jackJew.interview;

public class IntOverloadTest {
	
	static void job(int abc){
		System.out.println("job(int abc)");
	}
	
	static void job(Integer abc){
		System.out.println("job(Integer abc)");
	}	
	
	
	static void job(int... abc){
		System.out.println("job(int... abc)");
	}
	
	public static void main(String[] args){
		job(123);  // call: job(int abc)
		
		int value = - (1 << 31) ;
		System.out.println("before cnverting: " + value);

		byte[] array = new byte[4];
		array[0] = (byte) (value >> 24);
		array[1] = (byte) (value >> 16);
		array[2] = (byte) (value >> 8);
		array[3] = (byte) value;
		System.out.println("converted to byte array.");
		
		int result = 0;
		result += ((array[0] & 0xff) << 24);
		result += ((array[1] & 0xff) << 16);
		result += ((array[2] & 0xff) << 8);
		result += (array[3] & 0xff);		
		System.out.println("final result: " + result);
		
	}
	
	void xxx(){
		synchronized(this){
			// ...
		}
	}

}
