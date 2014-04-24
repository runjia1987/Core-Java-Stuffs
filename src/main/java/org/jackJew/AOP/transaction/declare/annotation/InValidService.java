package org.jackJew.AOP.transaction.declare.annotation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("transactionInValidService")
public class InValidService {
	
	@Resource(name="transactionDao")
	private PojoDao dao;
	
	//@Transactional(propagation=Propagation.REQUIRED)  //when try...catch, UnexpectedRollbackException; if not catch then throw ORA-00936 exception.
	//@Transactional(propagation=Propagation.NESTED)  //when try...catch, only execute correct subpath; if does not catch then throw ORA-00936 exception.
	@Transactional(propagation=Propagation.REQUIRES_NEW)  //same as NESTED !!!
	public void insert(Pojo man){
		dao.insert(man, false);
	}

	public void callService(List<Pojo> manList) { }

}
