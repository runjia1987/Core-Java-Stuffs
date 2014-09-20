package org.jackJew.garbageCollection.phantomReference.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * jdbc Connection wrapper
 * @author Jack
 *
 */
public class ConnectionWrapper {

	private Connection dbConnection;
	
	public ConnectionWrapper(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Connection getDbConnection() {
		return dbConnection;
	}

	/**
	 * here we do not close the connection, just for test phantomReference. Not advised !!!
	 */
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
}
