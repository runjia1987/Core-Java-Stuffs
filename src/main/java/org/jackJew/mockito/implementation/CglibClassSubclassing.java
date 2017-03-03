package org.jackJew.mockito.implementation;

import net.sf.cglib.proxy.Enhancer;

import org.jackJew.mockito.implementation.InstantiationStrategy.Callback;
import org.jackJew.mockito.implementation.InstantiationStrategy.CglibCallbackNoop;

public class CglibClassSubclassing {	
	
	public Class<?> createClass(Class<?> cls) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallbackType(Callback.class);
		
		return enhancer.createClass();
	}
	
	public Class<?> createClass(Class<?> cls, Class<?> advisedInterface) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallbackType(CglibCallbackNoop.class);
		enhancer.setInterfaces(new Class<?>[] {advisedInterface });
		return enhancer.createClass();
	}
	
	public Class<?> createClassWithCallback(Class<?> cls, Class<?> callbackType) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallbackType(callbackType);
		
		return enhancer.createClass();
	}

}
