package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ConnectionReference extends PhantomReference
 * @author Jack
 *
 */
public class ConnectionReference extends PhantomReference<ConnectionWrapper> {

	/**
	 * Note: Never point a member field to the referent object  !!!
	 * <br> or the referent will never be phantom reachable !!!
	 */
	private Connection con;
	
	public ConnectionReference(ConnectionWrapper connectionWrapper,
					ReferenceQueue<? super ConnectionWrapper> q) {
		super(connectionWrapper, q);
		this.con = connectionWrapper.getDbConnection();
	}
	
	public void cleanUp() {
		try {
			System.out.println(this.con + " is to be closed silently.");
			
			this.con.close();  // after this call, this.con is set to null
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
