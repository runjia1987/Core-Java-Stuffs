package org.jackJew.ioc.spring;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("service2")
public class Service2 {
	
	@Resource(name="dataDao1")
	private DataDao1 dataDao1;
	
	public void save(String container){
		System.out.println("org.jackJew.ioc.spring.service1.save(String): " + container);
	}
	
}
