package org.jackJew.AOP.JdkProxy;

/**
 * 测试AOP反射代理
 * @author zhurunjia
 */
public class TestClient {

	/**
	 * 
	 */
	public static void main(String[] args) {
		
		ArithmeticClaculator ac = new ArithmeticClaculator();	//目标对象
		
		Calculator proxy = (Calculator)new CalculatorInvocationHandler(ac).getProxy();//生成代理
		
		proxy.add(100, 200);
		
		Calculator proxy2 = (Calculator)new CalculatorArgsValidatorInvocation(ac).getProxy();//生成代理
		
		proxy2.add(100, -200);
	}

}
