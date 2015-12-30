package org.jackJew.ioc.cglib;

public class TestClient {

	/**
	 * @throws Exception 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		BizService bizService = new BizService(1);
		CglibProxyFactory proxyFactory = new CglibProxyFactory(bizService);
		
		BizService proxy =(BizService) proxyFactory.getProxy();
		proxy.step1("测试消息");
		
		System.out.println();
		
		proxy.step2("测试消息");
	}

}
