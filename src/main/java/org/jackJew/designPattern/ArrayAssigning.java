package org.jackJew.designPattern;

import java.util.Arrays;

public class ArrayAssigning {

	/**
	 * 
	 */
	public static void main(String[] args) {
		int[] array1 = new int[] {0, 1, 2, 3, 4, 5};
		System.out.println(Arrays.toString(array1));
		array1 = new int[] {10000, 999999};
		System.out.println(Arrays.toString(array1));
		
		String[] array2 = new String[10];
		array2[0] = "runjia";
		System.out.println(array2.length + "," + array2[5]);
		
		String[] array3 = new String[array2.length + 1];
		System.arraycopy(array2, 0, array3, 0, array2.length);
		//Arrays.copyOf(array2, array2.length+1);  // another way
		
		System.out.println(array3.length + "," + array3[0]);
	}

}
