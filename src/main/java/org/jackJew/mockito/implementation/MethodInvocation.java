package org.jackJew.mockito.implementation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodInvocation {
	
	private Method method;
	private Object[] arguments;
	
	public MethodInvocation(Method method, Object[] arguments) {
		this.method = method;
		this.arguments = arguments;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MethodInvocation) {
			MethodInvocation methodInvocation = (MethodInvocation)obj;
			if(!method.equals(methodInvocation.method)) {
				return false;
			}
			if(arguments == null && methodInvocation.arguments == null) {
				return true;
			} else if(arguments == null && methodInvocation.arguments != null) {
				return false;
			} else if(arguments != null && methodInvocation.arguments == null) {
				return false;
			} else {
				int size0 = arguments.length, size1 = methodInvocation.arguments.length;
				if(size0 != size1) {
					return false;
				} else {
					int i = 0;
					boolean allEqual = true;
					while(i < size0) {
						if(arguments[i] == methodInvocation.arguments[i] ||
								(arguments[i] != null && arguments[i].equals(methodInvocation.arguments[i]))) {
							i++;
							continue;
						} else {
							allEqual = false;
							break;
						}
					}
					return allEqual;
				}
			}
		}
		return false;
	}
	
	public int hashCode() {
		return method.hashCode() << 1 + (arguments == null ? 0 : Arrays.toString(arguments).hashCode());
	}

}
