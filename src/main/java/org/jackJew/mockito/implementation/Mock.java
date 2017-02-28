package org.jackJew.mockito.implementation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class Mock {
	
	public final static Map<Method, Object> methodsMap = new HashMap<Method, Object>();
	
	public final static Map<Method, Integer> methodExecutionMap = new HashMap<Method, Integer>();
	
	public final static ThreadLocal<Method> methodLocal = new ThreadLocal<Method>();
	
	private final static Objenesis OBJENESIS = new ObjenesisStd();
	
	public static <T> T mock(Class<T> cls) {
		Class<?> newCls = CglibClassSubclassing.createClass(cls);
		return (T) OBJENESIS.getInstantiatorOf(newCls).newInstance();
	}
	
	public static <T> OngoingStub<T> when(T methodCall) {
		return new OngoingStub<T>(methodLocal.get());
	}
	
	public static <T> T verify(T object) {
		return null;
	}

}
