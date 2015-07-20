package org.jackJew.classes.initialization;

public class ValueInStaticInitializer {

	static {
		//System.out.println(xyz);  //但不可引用此变量, 编译错误
		xyz = "456";   //可以为此变量赋值
	}
	
	static String xyz = "123";   //静态变量的定义位置
	
	public static void main(String[] args) {
		
		System.out.println(xyz);  // output: 123
		// decided by the order of xyz
		// 后出现的赋值会覆盖前面的赋值，并生效
		
	}

}
