package org.jackJew.AOP.proxyTest;

public class TargetClass1 implements Interface1 {
	
	private String str;
	
	public TargetClass1(String s){
		this.str = s;
	}

	@Override
	public void print(int a, int b) {
		System.out.println("计算结果：" + (a + b)  + " " + str);
		//throw new IllegalArgumentException("测试抛出一个异常!");
	}

}
