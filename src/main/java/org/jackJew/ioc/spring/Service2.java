package org.jackJew.ioc.spring;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("service2")
public class Service2 {
	
	@Resource(name="dao1")
	private DataDao1 dao1;
	
	public void save(String container){
		System.out.println("org.jackJew.ioc.spring.service1.save(String): " + container);
	}

	public DataDao1 getDao1() {
		return dao1;
	}

	public void setDao1(DataDao1 dao1) {
		System.out.println("org.jackJew.ioc.spring.Service2.setDao1(DataDao1) called.");
		this.dao1 = dao1;
	}

}
