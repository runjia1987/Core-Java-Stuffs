package org.jackJew.ioc.cglib;

public class ServiceImpl {
	
	public ServiceImpl(int in){
		
	}

	/**
	 * non-public methods not be intercepted by Cglib MethodInterceptor#intercept(MethodProxy)
	 */
	public void service(String msg) {
		System.out.println("业务消息：" + msg);
		step1();
		step2();
	}
	
	public void step1(){
		System.out.println("step1");
	}
	
	public void step2(){
		System.out.println("step2");
	}

}
