package org.jackJew.algorithm;

public class Fibonacci {
	
	/**
	 * recursive computing
	 * @param n
	 */
	public long recursive(int n){
		assert(n > 0);
		
		if( n == 1 ){
			return 1;
		} else if( n == 2){
			return 1;
		} else {
			return recursive(n - 1 ) + recursive(n - 2);
		}
	}
	
	/**
	 * normal computing, more efficient in time and avoid stackOverflowError
	 * @param n
	 * @return
	 */
	public long normal(int n) {
		long[] array = new long[3];
		assert(n > 0);
		if( n == 1 ){
			return 1;
		} else if( n == 2){
			return 1;
		} else {
			int i = 3;
			array[0] = 1;
			array[1] = 1;
			while (i++ <= n) {
				array[2] = array[0] + array[1];
				array[0] = array[1];
				array[1] = array[2];
			}
			return array[2];
		}
	}

	/**
	 * testcase
	 * @param args
	 */
	public static void main(String[] args){
		Fibonacci fc = new Fibonacci();
		long startMillis = System.currentTimeMillis();
		System.out.println(fc.recursive(30));
		System.out.println(System.currentTimeMillis() - startMillis);
		
		startMillis = System.currentTimeMillis();
		System.out.println(fc.normal(30));
		System.out.println(System.currentTimeMillis() - startMillis);
	}
	
}
