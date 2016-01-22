package org.jackJew.design.connectionPool;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MyConnection {
	
	private String user;	
	private String password;	
	private Set<Object> resultSet;
	
	public MyConnection(){
		System.out.println("MyConnection created new.");
		this.user = "jack";
		this.password = "123456";
		
		this.resultSet = new HashSet<Object>(1 << 8);
		this.resultSet.add(new Date());
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Object> getResultSet() {
		return resultSet;
	}
	public void setResultSet(Set<Object> resultSet) {
		this.resultSet = resultSet;
	}

}
