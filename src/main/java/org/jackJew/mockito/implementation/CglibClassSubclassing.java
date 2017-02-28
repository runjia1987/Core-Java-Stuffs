package org.jackJew.mockito.implementation;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibClassSubclassing {	
	
	public static Class<?> createClass(Class<?> cls) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallbackType(Callback.class);
		
		return enhancer.createClass();
	}
	
	public static class Callback implements MethodInterceptor {

		@Override
		public Object intercept(Object obj, Method method, Object[] args,
				MethodProxy proxy) throws Throwable {
			MethodInvocation methodInvocation = new MethodInvocation(method, args);
			Mock.methodLocal.set(methodInvocation);
			
			Integer executionCount = Mock.methodExecutionMap.get(methodInvocation);
			if(executionCount == null) {
				Mock.methodExecutionMap.put(methodInvocation, 1);
			} else {
				Mock.methodExecutionMap.put(methodInvocation, ++executionCount);
			}
			
			Object value = Mock.methodsMap.get(method);
			if(value != null) {
				return value;
			}
			return proxy.invokeSuper(obj, args);
		}
		
	}

}
