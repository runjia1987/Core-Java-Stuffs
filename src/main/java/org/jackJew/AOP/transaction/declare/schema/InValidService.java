package org.jackJew.AOP.transaction.declare.schema;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class InValidService {

	@Resource(name = "transactionDao2")
	private PojoDao dao;

	/**
	 * @Transactional(propagation=Propagation.REQUIRED) when invoker uses
	 *                                                  try...catch,
	 *                                                  UnexpectedRollbackException
	 * <br/>
	 *                                                  if does not catch then
	 *                                                  throw ORA-00936
	 *                                                  exception.<br/>
	 * 
	 * @Transactional(propagation=Propagation.NESTED) when invoker uses
	 *                                                try...catch, only execute
	 *                                                the correct subpath; <br/>
	 *                                                if does not catch then
	 *                                                throw ORA-00936 exception. <br/>
	 *                                                REQUIRES_NEW is similar to
	 *                                                NESTED in effects.
	 */
	public void insert(Pojo man) {
		dao.insert(man, false);
	}

}
