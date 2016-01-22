package org.jackJew.interview;

public class XYZ_Quiz {	
	
	public static void main(String[] args) {
		System.out.println(X.Y.getClass());   // org.jackJew.interview.Z
		System.out.println(X.Y.Z);   // 1234
	}

}

class X {
	static class Y {	 // static class has lower priority
		static String Z = "abcd";
	}
	
	static Z Y = new Z();   // static variable has higher priority
}

class Z {
	static String Z = "1234";
}
