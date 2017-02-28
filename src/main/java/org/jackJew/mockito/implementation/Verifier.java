package org.jackJew.mockito.implementation;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Verifier<T> {
	
	private T object;
	private int count;
	
	public Verifier(T object, int count) {
		this.object = object;
		this.count = count;
	}
	
	public <T> T getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(object.getClass());
		enhancer.setCallback(new VerifyCallback());
		return (T) enhancer.create();
	}
	
	class VerifyCallback implements MethodInterceptor {

		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			MethodInvocation methodInvocation = new MethodInvocation(method, args);
			Integer executionCount = Mock.methodExecutionMap.get(methodInvocation);
			if(executionCount == 0) {
				if(count != 0) {
					throw new IllegalArgumentException("verify fail, expect " + count + ", but actually is 0");
				}
			} else if(count != executionCount) {
				throw new IllegalArgumentException("verify fail, expect " + count + ", but actually is " + executionCount);
			}
			return null;
		}
		
	}

	public int getCount() {
		return count;
	}

	public T getObject() {
		return object;
	}

}
