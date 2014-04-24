package org.jackJew.jsonPerformace;

/**
 * 
 * Description: Test JavaBean
 * 
 * @author zhurunjia
 * 
 */
public class UserInfo implements java.io.Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -9119809361527461888L;
	private int id;
	private String name;
	private String password;

	public UserInfo(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
