package org.jackJew.design.connectionPool;

public class TestClient {
	
	public static void main(String[] args) throws Exception {
		MyConnectionPool pool = new MyConnectionPool(new MyObjectFactory());
		int i = 0;
		while(i++ < 20) {
			Object conn = pool.borrowObject();
		
			pool.returnObject(conn);
		}
	}

}
