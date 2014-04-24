package org.jackJew.AOP.transaction.declare.schema;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class InValidService {
	
	@Resource(name="transactionDao")
	private PojoDao dao;
	
	public void insert(Pojo man){
		dao.insert(man, false);
	}

	public void callService(List<Pojo> manList) { }

}
