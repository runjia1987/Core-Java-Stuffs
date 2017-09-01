package org.jackJew.design.connectionPool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class MyObjectFactory implements PooledObjectFactory {

	@Override
	public PooledObject makeObject() throws Exception {
		System.out.println("create new connection.");
		return new DefaultPooledObject(new MyConnection());
	}

	@Override
	public void destroyObject(PooledObject object) throws Exception {
		if(object.getObject() instanceof MyConnection){
			MyConnection conn = (MyConnection)object.getObject();
			conn.getResultSet().clear();
		}
		System.out.println("destroy this connection.");
	}

	@Override
	public boolean validateObject(PooledObject object) {
		if(object.getObject() instanceof MyConnection){
			MyConnection conn = (MyConnection)object.getObject();
			if(conn.getResultSet().isEmpty()){
				return false;
			}
			return true;
		} else {
			return false;
		}		
	}
	
	@Override
	public void activateObject(PooledObject object) throws Exception {
		System.out.println("activate this connection.");

	}
	
	@Override
	public void passivateObject(PooledObject object) throws Exception {
		System.out.println("passivate this connection.");
	}

}
