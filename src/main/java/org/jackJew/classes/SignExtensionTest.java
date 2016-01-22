package org.jackJew.classes;

import java.util.Date;

public class SignExtensionTest<E> {

	AbstractClass field = new AbstractClass(){ };

	public static void main(String... args) {
		System.out.println(3 >> 1);
		// 抽象类的实例化
		AbstractClass ac = new AbstractClass() { };
		ac.print();
		System.out.println( 8 & 7);
		System.out.println(new Date());
		System.out.println(null instanceof Object);
		
		// how to avoid sign extension
		byte b = -1;
		System.out.println(b);
		int i1 = (int) (b & 0xff);  // 255
		System.out.println("(int) (b & 0xff): " + i1);
		
		int i2 = (int) (b & 0xffff);  // 65535
		System.out.println("(int) (b & 0xffff)： " + i2);
		
		int i3 = (char)b;   // 65535
		System.out.println("(char)b: " + i3);
		
		char c4 = (char)(b & 0xff); // 255
		System.out.println("(char)(b & 0xff): " + (c4 == 255) ); // true
	}
	
	class Inner {
		
		private E instance = null;
		
	}

}

class AA {
	public AA (int index){ }
}

class BB extends AA {

	public BB() {  	// must explicitly define a constructor !
		super(0);	// must explicitlycall super constructor !
	}
	 
 }