package org.jackJew.ioc.cglib;

public class TestClient {

	/**
	 * 
	 */
	public static void main(String[] args) {
		CglibProxyFactory factory = new CglibProxyFactory();
		
		ServiceImpl s =(ServiceImpl) factory.createProxy(new ServiceImpl(1));
		s.service("测试消息输出");
		s.step1();
	}

}
