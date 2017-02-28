package org.jackJew.mockito.implementation;

public class OngoingStub<T> {
	
	private MethodInvocation invokedMethodInvocation;
	
	public OngoingStub(MethodInvocation invokedMethodInvocation) {
		this.invokedMethodInvocation = invokedMethodInvocation;
	}
	
	public OngoingStub<T> thenReturn(T returnVal) {
		Mock.methodsMap.put(this.invokedMethodInvocation, returnVal);
		
		Integer executionCount = Mock.methodExecutionMap.get(this.invokedMethodInvocation);
		if(executionCount == null) {
			// do nothing
		} else {
			Mock.methodExecutionMap.put(this.invokedMethodInvocation, --executionCount);
		}
		return this;
	}

}
