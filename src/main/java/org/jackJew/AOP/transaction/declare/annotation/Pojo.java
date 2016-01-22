package org.jackJew.AOP.transaction.declare.annotation;

public class Pojo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Integer id;
	String name;
	String password;
	
	public Pojo(int id, String name, String password){
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
