package org.jackJew.mockito.implementation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class InstantiationStrategy {
	
	private final static Objenesis OBJENESIS = new ObjenesisStd();
	
	@SuppressWarnings("unchecked")
	public <T> T instantiate(Class<T> cls) {
		if(cls.isInterface()) {
			Object obj = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[] { cls }, new JdkDynamicProxy());			
			return (T) obj;
		} else {
			Class<?> newCls = new CglibClassSubclassing().createClass(cls);
			return (T) OBJENESIS.getInstantiatorOf(newCls).newInstance();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T instantiate(Class<T> cls, Class<?> advisedInterface) {
		if(cls.isInterface()) {
			Object obj = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[] { cls, advisedInterface }, new Noop());
			System.out.println(obj.getClass().isInterface());
			
			return (T) obj;
		} else {
			Class<?> newCls = new CglibClassSubclassing().createClass(cls, advisedInterface);
			return (T) OBJENESIS.getInstantiatorOf(newCls).newInstance();
		}
	}
	
	public class JdkDynamicProxy implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			MethodInvocation methodInvocation = new MethodInvocation(method, args);
			Mock.methodLocal.set(methodInvocation);
			
			Integer executionCount = Mock.methodExecutionMap.get(methodInvocation);
			if(executionCount == null) {
				Mock.methodExecutionMap.put(methodInvocation, 1);
			} else {
				Mock.methodExecutionMap.put(methodInvocation, ++executionCount);
			}
			
			Object value = Mock.methodsMap.get(methodInvocation);
			if(value != null) {
				return value;
			}
			return null;
		}
		
	}
	
	public class Noop implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			return null;
		}
		
	}

}
