package org.jackJew.AOP.proxyTest;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

public class AfterReturnAdvice implements AfterReturningAdvice{

	/**
	 * 对于void返回值的方法, 同样拦截
	 */
	@Override
	public void afterReturning(Object retVal, Method amethod, Object[] arg2,
			Object target) throws Throwable {
		System.out.println("::返回通知开始...");
		System.out.println("【返回通知】取得的返回值：" + retVal);
		System.out.println("::返回通知结束.");
	}
	
	/**
	 * MethodInvocation mi
	 * this.advice.afterReturning(ret, mi.getMethod(), mi.getArguments(), mi.getThis());
	 */

}
