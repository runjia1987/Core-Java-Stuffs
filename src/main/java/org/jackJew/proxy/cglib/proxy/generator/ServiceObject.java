package org.jackJew.proxy.cglib.proxy.generator;

public class ServiceObject {
	private int type;
	
	public ServiceObject(int type) {
		this.type = type;
	}
	
	public void process() {
		System.out.println("process type: " + type);
	}

}
