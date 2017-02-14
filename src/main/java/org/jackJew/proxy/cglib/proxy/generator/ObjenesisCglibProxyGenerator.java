package org.jackJew.proxy.cglib.proxy.generator;

import java.lang.reflect.Method;

import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Based on Objenesis, to advise against Class with non-default constructor; <br/>
 * Prior to Spring 4, no advised proxy is supported for Class with non-default constructor;
 * <br/>
 * Spring 4 introduced {@link ObjenesisCglibAopProxy} to support this typical usage.
 * <br/>
 * this is a simple illustration.
 * @author Jack
 *
 * @param <T>
 */
public class ObjenesisCglibProxyGenerator<T> {

	private T target;
	final Objenesis objenesis = new ObjenesisStd();
	
	public ObjenesisCglibProxyGenerator(T target) {
		this.target = target;
	}
	
	public T generateProxy() {
		Class<T> subClass = new CglibSubClassingStrategy().createClass(target);
		System.out.println(subClass);   // subClass extends T implements Factory, SpringProxy, Advised
		
		Factory factoryProxy = (Factory) objenesis.getInstantiatorOf(subClass).newInstance(); //not by constructor, instead via ReflectionFactory
		factoryProxy.setCallback(0, new AdvisedCallback());
		
		return (T)factoryProxy;
	}
	
	public class AdvisedCallback implements MethodInterceptor {
		
		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			System.out.println("start call advised interceptor");
			Object result = proxy.invoke(target, args);
			System.out.println("end call advised interceptor");
			return result;
		}
	}
}
