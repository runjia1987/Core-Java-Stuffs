package org.jackJew.AOP.proxyTest;

import org.jackJew.AOP.transaction.declare.annotation.Anno;

@Anno(name = "TargetClass2 annotation")
public class TargetClass2 implements Interface1 {

	@Override
	public void print(int a, int b) {
		System.out.println("结果为：" + ( a + b ) );
	}

}
