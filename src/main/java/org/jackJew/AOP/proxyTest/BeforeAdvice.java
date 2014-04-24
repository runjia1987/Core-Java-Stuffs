package org.jackJew.AOP.proxyTest;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object target)
			throws Throwable {
		
		System.out.println("::前置通知开始...");
		System.out.println("日志截获的参数: " + Arrays.toString(arg1));
		System.out.println("【前置通知】获得对象类名：" + target.getClass().getName());
		System.out.println("::前置通知结束.");
		
	}
	
	

}
