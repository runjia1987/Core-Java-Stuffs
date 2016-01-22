package org.jackJew.design.connectionPool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class MyConnectionPool extends GenericObjectPool {
	
	public MyConnectionPool(PoolableObjectFactory factory){
		super(factory);
		setMaxActive(5);
		setMaxIdle(10);
		setWhenExhaustedAction(WHEN_EXHAUSTED_BLOCK);
		setTestOnBorrow(true);
		setTestWhileIdle(false);
	}

	@Override
	public Object borrowObject() throws Exception {
		System.out.println("MyConnectionPool starts to borrow an instance from pool.");
		return super.borrowObject();
	}

	@Override
	public void returnObject(Object connection) throws Exception {		
		super.returnObject(connection);
		System.out.println("MyConnectionPool already returns an instance to pool, the pool has: "
					+ this.getNumIdle() + " idle connections.");
	}
	

}
