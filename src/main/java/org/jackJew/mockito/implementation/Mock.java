package org.jackJew.mockito.implementation;

import java.util.HashMap;
import java.util.Map;

public class Mock {
	
	public final static Map<MethodInvocation, Object> methodsMap = new HashMap<MethodInvocation, Object>();
	
	public final static Map<MethodInvocation, Integer> methodExecutionMap = new HashMap<MethodInvocation, Integer>();
	
	public final static ThreadLocal<MethodInvocation> methodLocal = new ThreadLocal<MethodInvocation>();	
	
	public static <T> T mock(Class<T> cls) {
		return new InstantiationStrategy().instantiate(cls);
	}
	
	public static <T> OngoingStub<T> when(T methodCall) {
		return new OngoingStub<T>(methodLocal.get());
	}
	
	public static <T> T verify(T mockedObject, int count) {
		return new Verifier<T>(mockedObject, count).getProxy();
	}
	
	public static <T> T verify(T mockedObject) {
		return new Verifier<T>(mockedObject, 1).getProxy();
	}
	
	public static int times(int count) {
		return count;
	}
	
	public static <T> T any(Class<T> cls) {
		return new InstantiationStrategy().instantiate(cls, AdvisedMarker.class);
	}

}
