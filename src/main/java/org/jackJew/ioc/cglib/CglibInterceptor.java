package org.jackJew.ioc.cglib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib动态派生自目标对象类的子类 , instance of dynamic subclassing
 * @author zhurunjia
 */
public class CglibInterceptor implements MethodInterceptor {
	private Object targetObject;

	@Override
	public Object intercept(Object proxy, Method method, Object[] params,
			MethodProxy methodProxy) throws Throwable {
		
		System.out.println("拦截的方法名：" + method.getName());
		System.out.println("参数值：" + Arrays.toString(params));
		System.out.println("实现的接口: " + Arrays.toString(proxy.getClass().getInterfaces()));
		
		if(method.getName().startsWith("step")) {	//过滤方法
			return null;
		}
		// invokeSuper是退出当前interceptor的处理，进入下一个callback处理; invoke则会继续回调该方法
		return methodProxy.invoke(this.targetObject, params);
	}
	
	public Object createProxy(Object obj){
		this.targetObject = obj;
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(this);
		//enhancer.setInterfaces((Class<?>[]) null);
		
		// if default no-args isn't defined, use this instead
		Constructor<?> ctr = obj.getClass().getConstructors()[0];
		return enhancer.create(ctr.getParameterTypes(), new Object[]{ 1 });
	}

}
