package org.jackJew.AOP.transaction.declare.schema;

import javax.annotation.Resource;

public class InValidService {

	@Resource(name = "transactionDao2")
	private PojoDao dao;

	/**
	 * {@link Transactional(propagation=Propagation.REQUIRED)} when invoker uses
	 *                                                  try...catch,
	 *                                                  UnexpectedRollbackException, inner transaction marked as roll-back only.
	 * 													<br/>
	 *                                                  if does not catch then
	 *                                                  throw ORA-00936
	 *                                                  exception.
	 *                                                  <br/>
	 * {@link Transactional(propagation=Propagation.NESTED)} when invoker uses
	 *                                                try...catch, only execute
	 *                                                the correct subpath (SavePointManager createAndHoldSavepoint);
	 *                                                <br/>
	 *                                                if does not catch then
	 *                                                throw ORA-00936 exception.
	 *                                                <br/>
	 *                                                REQUIRES_NEW is similar to NESTED in effects.
	 */
	public void insert(Pojo man) {
		dao.insert(man, false);
	}

}
