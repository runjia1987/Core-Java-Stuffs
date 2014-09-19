package org.jackJew.garbageCollection.phantomReference;

import java.lang.ref.ReferenceQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * jdbc Connection wrapper
 * @author Jack
 *
 */
public class ConnectionWrapper {

	private Connection dbConnection;
	
	public ConnectionWrapper(Connection dbConnection, ReferenceQueue<ConnectionWrapper> queue,
				List<ConnectionReference> referenceList) {
		this.dbConnection = dbConnection;
		
		ConnectionReference reference = new ConnectionReference(this, queue);
		referenceList.add(reference);
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
