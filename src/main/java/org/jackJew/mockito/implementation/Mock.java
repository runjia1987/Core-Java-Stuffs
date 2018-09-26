package org.jackJew.mockito.implementation;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class Mock {
	
	final static Map<Object, Class<?>> objectTypeMap = new IdentityHashMap<>();
	
	final static Map<MethodInvocation, Object> methodsMap = new HashMap<>();
	
	final static Map<MethodInvocation, Integer> methodExecutionMap = new HashMap<>();
	
	final static Map<Class<?>, AnyAny> anyObjectMap = new HashMap<>();
	
	final static ThreadLocal<MethodInvocation> methodLocal = new ThreadLocal<>();
	
	public static <T> T mock(Class<T> cls) {
		T mockedObject = new InstantiationStrategy().instantiate(cls);
		objectTypeMap.put(mockedObject, cls);
		return mockedObject;
	}
	
	public static <T> OngoingStub<T> when(T methodCall) {
		return new OngoingStub<>(methodLocal.get());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T verify(T mockedObject, int count) {
		Class<T> cls = (Class<T>) objectTypeMap.get(mockedObject);
		if(cls == null) {
			throw new IllegalArgumentException("verify for mockedObject " + mockedObject + " not exists!");
		}
		return new Verifier<T>(count).createVerifier(cls);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T verify(T mockedObject) {
		Class<T> cls = (Class<T>) objectTypeMap.get(mockedObject);
		if(cls == null) {
			throw new IllegalArgumentException("verify for mockedObject " + mockedObject + " not exists!");
		}		
		return new Verifier<T>(1).createVerifier(cls);
	}
	
	public static int times(int count) {
		return count;
	}
	
	public static <T> T any(Class<T> cls) { // any condition priority, not solved
		T anyObject = new InstantiationStrategy().instantiate(cls, AdvisedMarker.class);
		anyObjectMap.put(cls, new AnyAny((AdvisedMarker) anyObject));
		return anyObject;
	}
	
	static class AnyAny {
		private AdvisedMarker anyObject;
		private boolean used;
		
		AnyAny(AdvisedMarker anyObject) {
			this.anyObject = anyObject;
		}
		
		void setUsed(boolean used) {
			this.used = used;
		}

		public AdvisedMarker getAnyObject() {
			return anyObject;
		}
	}

}
