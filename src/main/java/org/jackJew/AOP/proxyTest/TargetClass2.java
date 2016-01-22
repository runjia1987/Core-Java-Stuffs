package org.jackJew.AOP.proxyTest;

public class TargetClass2 implements Interface1 {

	@Override
	public void print(int a, int b) {
		System.out.println("结果为：" + ( a + b ) );
	}

}
