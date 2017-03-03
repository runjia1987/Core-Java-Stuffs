package org.jackJew.mockito.implementation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Verifier<T> {
	
	private int count;
	
	public Verifier(int count) {
		this.count = count;
	}
	
	@SuppressWarnings("unchecked")
	public T createVerifier(Class<T> cls) {
		if(cls.isInterface()) {
			Object obj = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[] { cls }, new InterfaceVerifyCallBack());			
			return (T) obj;
		} else {
			Class<?> newCls = new CglibClassSubclassing().createClassWithCallback(cls, VerifyCallback.class);
			Factory factoryProxy = (Factory) InstantiationStrategy.OBJENESIS.getInstantiatorOf(newCls).newInstance();
			factoryProxy.setCallback(0, new VerifyCallback());
			return (T) factoryProxy;
		}
	}
	
	public class VerifyCallback implements MethodInterceptor {

		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			MethodInvocation methodInvocation = new MethodInvocation(method, args);
			Integer executionCount = Mock.methodExecutionMap.get(methodInvocation);			
			if(executionCount == null || executionCount == 0) {
				if(count != 0) {
					throw new IllegalArgumentException("verify fail, expect " + count + ", but actually is 0");
				}
			} else if(count != executionCount) {
				throw new IllegalArgumentException("verify fail, expect " + count + ", but actually is " + executionCount);
			}
			return null;
		}
		
	}
	
	public class InterfaceVerifyCallBack implements InvocationHandler {
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			MethodInvocation methodInvocation = new MethodInvocation(method, args);
			Integer executionCount = Mock.methodExecutionMap.get(methodInvocation);
			if(executionCount == null || executionCount == 0) {
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
}
