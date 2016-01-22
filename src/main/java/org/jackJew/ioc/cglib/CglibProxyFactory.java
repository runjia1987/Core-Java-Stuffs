package org.jackJew.ioc.cglib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.Enhancer.EnhancerKey;
import org.springframework.util.ReflectionUtils;


/**
 * create proxy
 * @author Jack
 *
 */
public class CglibProxyFactory {
	
	private Object targetObject;
	
	public CglibProxyFactory(Object targetObject) {
		this.targetObject = targetObject;
	}
	
	public Object getProxy() throws Exception {
		Enhancer enhancer = new org.springframework.cglib.proxy.Enhancer();
		final Class<?> cls = targetObject.getClass();
		enhancer.setSuperclass(cls);
		enhancer.setCallbacks(getCallbacks().toArray(new Callback[2]));
		enhancer.setCallbackFilter(new CustomCallbackFilter());
		
		// if default no-args isn't defined, use this instead
		Constructor<?> ctr = cls.getConstructor(new Class<?>[]{int.class});
		ReflectionUtils.makeAccessible(ctr);
		return enhancer.create(ctr.getParameterTypes(), new Object[]{ 100 });
	}
	
	private List<Callback> getCallbacks() {
		List<Callback> callbacks = new ArrayList<>();
		// callbacks order 0, 1
		callbacks.add(new CglibInterceptor(targetObject));
		callbacks.add(new CglibInterceptor2(targetObject));
		return callbacks;
	}
	
	private class CustomCallbackFilter implements CallbackFilter {

		/**
		 * indicate the index of callback to be executed
		 */
		@Override
		public int accept(Method method) {
			final String methodName = method.getName();
			if(methodName.equals("step1")) {
				return 0;
			} else if(methodName.equals("step2")) {
				return 1;
			}
			return 0;
		}
		
	}

}
