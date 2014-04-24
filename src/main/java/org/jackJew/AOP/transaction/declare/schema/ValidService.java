package org.jackJew.AOP.transaction.declare.schema;

import java.util.List;

import javax.annotation.Resource;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 没有必要实现接口, 直接使用<aop:aspectj-autoproxy />即可.
 * 一旦实现了接口, 则必须使用接口作为declaring type(不能使用具体的业务类),否则会报ClassCastException(可以
 * 配置proxy-target-class=true去强制使用CGlib动态派生子类的代理).
 * @author runjia
 */
public class ValidService {
	
	@Resource(name="transactionDao")
	private PojoDao dao;
	
	@Resource(name="transactionInValidService")
	private InValidService invalidService;
	
	public void callService(List<Pojo> manList){
		insert(manList.get(0));
		try {
			// call other service (invalid insert)
			invalidService.insert(manList.get(1));
		} catch(Exception e){
			System.out.println("catch message: " + e.getMessage());
		}
	}
	
	public void insert(Pojo man){
		dao.insert(man, true);
	}
	
	public void inValidInsert(Pojo man){
		dao.insert(man, false);
	}

}

