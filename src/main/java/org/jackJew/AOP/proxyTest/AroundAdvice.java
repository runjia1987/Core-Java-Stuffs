package org.jackJew.AOP.proxyTest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 一个环绕通知Around Advice
 * @author zhurunjia
 */
public class AroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("::环绕通知开始...");
		
		System.out.println("【环绕通知】获得对象类名：" + arg0.getThis().getClass().getName());
		
		Object retVal;
		try {
			retVal = arg0.proceed();
		} finally {
			System.out.println("::环绕通知结束.");
		}
		
		return retVal;
	}	
}
