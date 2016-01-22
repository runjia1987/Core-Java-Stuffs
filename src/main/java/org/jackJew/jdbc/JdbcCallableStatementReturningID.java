package org.jackJew.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 获取Oracle数据库的最新插入的主键ID
 * @author zhurunjia
 */
public class JdbcCallableStatementReturningID {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Class.forName("");
		Connection conn = DriverManager.getConnection("url", "username", "password");
		
		CallableStatement statement = conn.prepareCall(
					"{call INSERT INTO tableA(name,password) VALUES ('admin','123') RETURNING id INTO ?}");
		// {call proc(?) }
		
		statement.registerOutParameter(1, Types.INTEGER);

		int id = statement.getInt(1);
		System.out.println(id);
	}

}
