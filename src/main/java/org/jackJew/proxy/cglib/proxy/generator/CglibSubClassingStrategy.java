package org.jackJew.proxy.cglib.proxy.generator;

import org.springframework.aop.SpringProxy;
import org.springframework.aop.framework.Advised;

import net.sf.cglib.proxy.Enhancer;

/**
 * create subclass based on Cglib
 * @author Jack
 *
 */
public class CglibSubClassingStrategy {
	
	public <T> Class<T> createClass(T target) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallbackTypes(new Class<?>[]{ObjenesisCglibProxyGenerator.AdvisedCallback.class });
		enhancer.setInterfaces(new Class<?>[] {SpringProxy.class, Advised.class});
		return enhancer.createClass();
	}

}