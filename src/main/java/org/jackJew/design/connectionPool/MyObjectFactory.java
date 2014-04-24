package org.jackJew.design.connectionPool;

import org.apache.commons.pool.PoolableObjectFactory;

public class MyObjectFactory implements PoolableObjectFactory {

	@Override
	public Object makeObject() throws Exception {
		System.out.println("makeObject");
		return new MyConnection();
	}

	@Override
	public void destroyObject(Object arg0) throws Exception {
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
	public void activateObject(Object arg0) throws Exception {
		System.out.println("activate this connection.");

	}
	
	@Override
	public void passivateObject(Object arg0) throws Exception {
		System.out.println("passivate this connection.");
	}

}
