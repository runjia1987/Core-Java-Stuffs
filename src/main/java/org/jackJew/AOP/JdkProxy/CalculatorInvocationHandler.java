package org.jackJew.AOP.JdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 实现动态代理的处理器
 * @author zhurunjia
 */
public class CalculatorInvocationHandler implements InvocationHandler {

	//要代理的对象，动态代理只有在运行时才知道代理谁，所以定义为Object类型，可以代理任意对象
    private Object target;
    
    public CalculatorInvocationHandler(Object target){
    	this.target = target;
    }
    
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("[method]: " + method.getName() + ", [args]: " + Arrays.toString(args));
		
		System.out.println("实现的接口：" + proxy.getClass().getInterfaces()[0].getName());
		Object result = method.invoke(this.target, args);
		
		System.out.println("[after invocation, result]: " + result);
		
		return result;
	}
	
	/**
	 * 生成代理对象
	 */
    public Object getProxy(){
    	ClassLoader cl = null;
    	cl = this.target.getClass().getClassLoader();
    	//cl = Thread.currentThread().getContextClassLoader(); //it's OK
        return Proxy.newProxyInstance(cl,
        							  this.target.getClass().getInterfaces(),
        							  this);
    }

}
