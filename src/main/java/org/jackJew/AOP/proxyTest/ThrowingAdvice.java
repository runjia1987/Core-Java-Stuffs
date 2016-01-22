package org.jackJew.AOP.proxyTest;

import java.io.IOException;
import java.lang.reflect.Method;
import org.springframework.aop.ThrowsAdvice;

/**
 * ThrowsAdvice并没有定义任何接口方法，只是一个标签接口
 * <br> 方法格式：afterThrowing([Method, args, target], subclassOfThrowable) 
 * <br> 只有最后一个参数是必需的
 * @author zhurunjia
 */
public class ThrowingAdvice implements ThrowsAdvice {
	
	public void afterThrowing(Exception exception){ }
	
	/**
	 * 根据不同的产生异常类型, 定位到不同的处理方法
	 */
	public void afterThrowing(IOException e) { } 
	
	/**
	 * 注意此方法的最后一个参数类型不能为Throwable, 否则后续的拦截方法都不会被屏蔽掉
	 */
	public void afterThrowing(Method method, Object[] args, Object target, RuntimeException exception){
		System.out.println("::异常通知开始...");
		System.out.println("【异常通知】获得对象类名：" + target.getClass().getName());
		System.out.println("记录了此异常：" + exception.getMessage());
		System.out.println("::异常通知结束.");
	}
	

}