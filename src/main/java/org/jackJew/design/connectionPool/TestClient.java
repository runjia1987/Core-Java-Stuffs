package org.jackJew.design.connectionPool;

import java.util.Random;

public class TestClient {
	
	public static void main(String[] args) throws Exception {
		MyConnectionPool pool = new MyConnectionPool(new MyObjectFactory());
		Random rnd = new Random();

		for(int i = 0; i < 20; i++) {
			Thread thread = new Thread(() -> {
				try {
					MyConnection conn = pool.borrowObject();
					Thread.sleep(rnd.nextInt(1000));
					System.out.println(Thread.currentThread().getName() + "," + conn.getUser() + "," + conn.getPassword());
					pool.returnObject(conn);
				} catch(Exception e) {
					e.printStackTrace();
				}
			});
			thread.start();
		}
		
		Thread.sleep(10000); // expect all threads will have been ended in 10 sec
		pool.close();  // will invoke PoolableObjectFactory.destroyObject()
	}

}
