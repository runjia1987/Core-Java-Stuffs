package org.jackJew.mockito.implementation;

import java.lang.reflect.Method;

public class OngoingStub<T> {
	
	private Method invokedMethod;
	
	public OngoingStub(Method invokedMethod) {
		this.invokedMethod = invokedMethod;
	}
	
	public OngoingStub<T> thenReturn(T returnVal) {
		Mock.methodsMap.put(this.invokedMethod, returnVal);
		
		Integer executionCount = Mock.methodExecutionMap.get(this.invokedMethod);
		if(executionCount == null) {
			// do nothing
		} else {
			Mock.methodExecutionMap.put(this.invokedMethod, --executionCount);
		}
		return this;
	}

}
