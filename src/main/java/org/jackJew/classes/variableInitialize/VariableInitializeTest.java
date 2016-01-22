package org.jackJew.classes.variableInitialize;

public class VariableInitializeTest {
	
	/**
	 * 成员变量有默认的初始化, 会被初始化为其类型的默认值（0、'\u0000'、false、null等）
	 */
	int i;
	boolean b;
	
	public void fn(){
		//局部变量没有默认初始化, 必须显式设置初始值
		int j;
		boolean c;
		//System.out.println(j == 1);	//compile error, must initialize
	}

	/**
	 * 
	 */
	public static void main(String[] args) {
		
		VariableInitializeTest t = new VariableInitializeTest();
		System.out.println(t.i);	// output: 0
		System.out.println(t.b);	//output: false
		t = null;
		//System.out.println(t.i);	//NullPointerException
	}

}
