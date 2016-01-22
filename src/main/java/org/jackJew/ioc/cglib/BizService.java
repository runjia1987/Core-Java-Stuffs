package org.jackJew.ioc.cglib;

public class BizService {
	
	public BizService(int in) {
	}
	
	/**
	 * non-public methods will not be intercepted by Cglib MethodInterceptor#intercept(MethodProxy)
	 */
	public void step1(String msg) {
		System.out.println("step1 业务消息：" + msg);
	}
	
	public void step2(String msg) {
		System.out.println("step2 业务消息：" + msg);
	}

}
