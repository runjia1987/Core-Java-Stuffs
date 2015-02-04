package org.jackJew.classes.initialization;

public class ValueInNormalInitializer {
	
	{
		//System.out.println(xyz);   // compile error, can not be referenced
		xyz = 456;  // can be re-assigned to new value
	}
	
	int xyz = 123;
	
	public static void main(String[] args) {
		ValueInNormalInitializer initializer = new ValueInNormalInitializer();
		System.out.println(initializer.xyz);  // output: 123
		// the latter(position) assigned value takes effect
	}

}
