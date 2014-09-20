package org.jackJew.garbageCollection.phantomReference.connection;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.sql.Connection;
import java.sql.SQLException;

import org.jackJew.garbageCollection.phantomReference.Cleanable;

/**
 * ConnectionReference extends PhantomReference
 * @author Jack
 *
 */
public class ConnectionReference extends PhantomReference<ConnectionWrapper> implements Cleanable {

	/**
	 * Note: never point a member field to the referent object  !!!
	 * <br> otherwise the referent will never become phantom reachable !!!
	 */
	private Connection con;
	
	public ConnectionReference(ConnectionWrapper connectionWrapper,
					ReferenceQueue<? super ConnectionWrapper> q) {
		super(connectionWrapper, q);
		this.con = connectionWrapper.getDbConnection();
	}
	
	public void cleanup() {
		try {
			System.out.println(this.con + " is to be closed silently.");
			
			this.con.close();  // after this call, this.con willbe set to null
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
