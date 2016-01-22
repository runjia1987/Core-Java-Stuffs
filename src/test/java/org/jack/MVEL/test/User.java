package org.jack.MVEL.test;

public class User {
	
	private String name;
	
	private String password;
	
	public User(String s1, String s2) {
		this.name = s1;
		this.password = s2;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder(1 << 6);
		builder.append(name).append(",").append(password);
		
		return builder.toString();
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
