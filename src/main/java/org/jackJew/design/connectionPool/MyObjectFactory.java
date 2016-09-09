package org.jackJew.design.connectionPool;

import org.apache.commons.pool.PoolableObjectFactory;

public class MyObjectFactory implements PoolableObjectFactory {

	@Override
	public Object makeObject() throws Exception {
		System.out.println("create new connection.");
		return new MyConnection();
	}

	@Override
	public void destroyObject(Object object) throws Exception {
		if(object instanceof MyConnection){
			MyConnection conn = (MyConnection)object;
			conn.getResultSet().clear();
		}
		System.out.println("destroy this connection.");
	}

	@Override
	public boolean validateObject(Object object) {
		if(object instanceof MyConnection){
			MyConnection conn = (MyConnection)object;
			if(conn.getResultSet().isEmpty()){
				return false;
			}
			return true;
		} else {
			return false;
		}		
	}
	
	@Override
	public void activateObject(Object object) throws Exception {
		System.out.println("activate this connection.");

	}
	
	@Override
	public void passivateObject(Object object) throws Exception {
		System.out.println("passivate this connection.");
	}

}
