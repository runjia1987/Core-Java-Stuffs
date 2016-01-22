package org.jackJew.AOP.aspectj.advice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class JoinpointOperation {
	
	public JoinpointOperation(){
		System.out.println(JoinpointOperation.class.getCanonicalName() + " constructor called.");
	}
	
	public void plainMethod() {
		System.out.println("plainMethod executed.");
	}
	
	public java.util.List<String> getList(String id){
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add(id);
		System.out.println("JoinpointOperation.getList(String) executed, list.size: "
					+ list.size());
		return list;
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println(JoinpointOperation.class.getCanonicalName() + " postConstruct().");
	}
	
}
