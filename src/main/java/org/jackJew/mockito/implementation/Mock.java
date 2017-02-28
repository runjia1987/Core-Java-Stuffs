package org.jackJew.mockito.implementation;

import java.util.HashMap;
import java.util.Map;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class Mock {
	
	public final static Map<MethodInvocation, Object> methodsMap = new HashMap<MethodInvocation, Object>();
	
	public final static Map<MethodInvocation, Integer> methodExecutionMap = new HashMap<MethodInvocation, Integer>();
	
	public final static ThreadLocal<MethodInvocation> methodLocal = new ThreadLocal<MethodInvocation>();
	
	private final static Objenesis OBJENESIS = new ObjenesisStd();
	
	public static <T> T mock(Class<T> cls) {
		Class<?> newCls = CglibClassSubclassing.createClass(cls);
		return (T) OBJENESIS.getInstantiatorOf(newCls).newInstance();
	}
	
	public static <T> OngoingStub<T> when(T methodCall) {
		return new OngoingStub<T>(methodLocal.get());
	}
	
	public static <T> T verify(T object, int count) {
		return new Verifier<T>(object, count).getProxy();
	}
	
	public static <T> T verify(T object) {
		return new Verifier<T>(object, 1).getProxy();
	}
	
	public static int times(int count) {
		return count;
	}

}
