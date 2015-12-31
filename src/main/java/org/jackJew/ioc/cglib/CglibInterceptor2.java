package org.jackJew.ioc.cglib;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @author Jack
 *
 */
public class CglibInterceptor2 implements MethodInterceptor {	
	private Object target;
	
	public CglibInterceptor2(Object target) {
		this.target = target;
	}

	@Override
	public Object intercept(Object proxy, Method method, Object[] params,
			MethodProxy methodProxy) throws Throwable {
		System.out.println("CglibInterceptor2拦截的方法名：" + method.getName());
		System.out.println("CglibInterceptor2拦截的参数：" + Arrays.toString(params));
		System.out.println("代理的父类: " + proxy.getClass().getSuperclass());
		System.out.println("代理实现的接口: " + Arrays.toString(proxy.getClass().getInterfaces()));
		
		// invokeSuper是退出当前interceptor的处理，进入下一个callback处理; invoke则会继续回调该方法
		return methodProxy.invoke(this.target, params);
	}

}