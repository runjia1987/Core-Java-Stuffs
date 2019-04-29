package org.jackJew.AOP.JdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorArgsValidatorInvocation implements InvocationHandler {
	
	private Object target;	
	public CalculatorArgsValidatorInvocation(Object target){ this.target = target; }

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		System.out.println("[method]: " + method.getName() + ", [args]: " + Arrays.toString(args));
		System.out.println("实现的接口：" + proxy.getClass().getInterfaces()[0].getName());
		
		for(Object arg: args){
			validate((Integer)arg);
		}
		
		return method.invoke(this.target, args);
	}
	
	private void validate(int arg){
		if(arg < 0) {
			throw new RuntimeException("自定义验证：参数不能为负数!");
		}
	}
	
	public Object getProxy(){
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
									  this.target.getClass().getInterfaces(),
									  this);
	}

}
