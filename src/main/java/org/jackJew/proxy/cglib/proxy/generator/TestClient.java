package org.jackJew.proxy.cglib.proxy.generator;

public class TestClient {
	
	public static void main(String[] args) {
		ServiceObject serviceObject = new ServiceObject(100);
		
		ServiceObject proxy = new ObjenesisCglibProxyGenerator<ServiceObject>(serviceObject).generateProxy();
		proxy.process();
	}

}
