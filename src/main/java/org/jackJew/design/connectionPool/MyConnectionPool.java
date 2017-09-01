package org.jackJew.design.connectionPool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class MyConnectionPool extends GenericObjectPool<MyConnection> {
	
	public MyConnectionPool(PooledObjectFactory factory){
		super(factory);
		setMaxTotal(5);
		setMaxIdle(5);
		setBlockWhenExhausted(true);
		setTestOnBorrow(true);
		setTestWhileIdle(false);
	}

	@Override
	public MyConnection borrowObject() throws Exception {
		System.out.println("MyConnectionPool starts to borrow an instance from pool.");
		return super.borrowObject();
	}

	@Override
	public void returnObject(MyConnection connection) {
		super.returnObject(connection);
		System.out.println("MyConnectionPool returned an instance to pool, the pool has: "
					+ this.getNumIdle() + " idle connections.");
	}
	

}
