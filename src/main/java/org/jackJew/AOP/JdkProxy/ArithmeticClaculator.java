package org.jackJew.AOP.JdkProxy;

/**
 * 计算接口的一个实现类
 * @author zhurunjia
 */
public class ArithmeticClaculator implements Calculator {

	/**
	 * 在JDK 1.5中, @Override不能用于interface method.
	 */
	@Override
	public int add(int arg0, int arg1) {
		return arg0 + arg1;
	}

}
