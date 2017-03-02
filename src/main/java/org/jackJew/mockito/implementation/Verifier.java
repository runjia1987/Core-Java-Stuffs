package org.jackJew.mockito.implementation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Verifier<T> {
	
	private T mockedObject;
	private int count;
	
	public Verifier(T mockedObject, int count) {
		this.mockedObject = mockedObject;
		this.count = count;
	}
	
	@SuppressWarnings("unchecked")
	public T getProxy() {
		// jdk dynamic proxy class is final
		if(Modifier.isFinal(mockedObject.getClass().getModifiers())) {
			return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), mockedObject.getClass().getInterfaces(), new InterfaceVerifyCallBack());
		} else {
			// must be Cglib-generated subclass
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(mockedObject.getClass());
			enhancer.setCallback(new VerifyCallback());
			return (T) enhancer.create();
		}
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
	
	class InterfaceVerifyCallBack implements InvocationHandler {
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
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

	public T getMockedObject() {
		return mockedObject;
	}
}
