package org.jackJew.AOP.transaction.declare.annotation;

import java.util.List;

import javax.annotation.Resource;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 没有必要实现接口, 直接使用<aop:aspectj-autoproxy />即可. 一旦实现了接口, 则必须使用接口作为declaring
 * type(不能使用具体的业务类),否则会报ClassCastException(可以
 * 配置proxy-target-class=true去强制使用CGlib动态派生子类的代理).
 * 
 * @author runjia
 */
@Service("transactionValidService")
public class ValidService {

	@Resource(name = "transactionDao")
	private PojoDao pojoDao;

	@Resource(name = "transactionInValidService")
	private InValidService invalidService;

	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(List<Pojo> manList) {
		insert(manList.get(0));
		try {
			// call other service (invalid insert)
			invalidService.insert(manList.get(1));
		} catch (Exception e) {
			System.out.println("catch message: " + e.getMessage());
		}
	}

	private void insert(Pojo man) {
		pojoDao.insert(man, true);
	}

	/**
	 * will not be intercepted, because it's in the same service class. this is
	 * due to proxy-based mechanism.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	// @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void inValidInsert(Pojo man) {
		pojoDao.insert(man, false);
	}

}
