package org.jackJew.mockito.implementation;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibClassSubclassing {	
	
	public Class<?> createClass(Class<?> cls) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallbackType(Callback.class);
		
		return enhancer.createClass();
	}
	
	public Class<?> createClass(Class<?> cls, Class<?> advisedInterface) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallbackType(Noop.class);
		enhancer.setInterfaces(new Class<?>[] {advisedInterface });
		return enhancer.createClass();
	}
	
	public class Callback implements MethodInterceptor {

		@Override
		public Object intercept(Object proxy, Method method, Object[] args,
				MethodProxy methodproxy) throws Throwable {
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
	
	public class Noop implements MethodInterceptor {

		@Override
		public Object intercept(Object obj, Method method, Object[] args,
				MethodProxy proxy) throws Throwable {
			return null;
		}
		
	}

}
