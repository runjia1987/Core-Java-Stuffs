package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * jdbc Connection wrapped by PhantomReference
 * @author Jack
 *
 */
public class PhantomConnection extends PhantomReference<Connection> {

	private Connection dbConnection;
	
	public PhantomConnection(Connection dbConnection, ReferenceQueue<Connection> queue) {
		super(dbConnection, queue);		
	}
	
	public Connection getDbConnection() {
		return dbConnection;
	}
	
	public void queryOperation() throws Exception {
		String sql = "select id, password from user_storage where user_name = ?";
		PreparedStatement psmt = null;
		try {
			psmt = this.dbConnection.prepareStatement(sql);
			psmt.setString(1, "Jack");
			
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( psmt != null){
				psmt.close();
			}
		}
	}

	public void cleanUp(){
		try {
			this.dbConnection.close();
			
			System.out.println(this.dbConnection + " is closed silently.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
