package org.jackJew.jsonPerformace;

/**
 * 
 * do not use fastjson, since it requires that the model class has setter methods for fields,
 * <br/>
 * while Gson does not.
 * 
 * @author zhurunjia
 * 
 */
public class UserInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -9119809361527461888L;
	private int id;
	private String name;
	private String password;
	
	/**
	 * if no default non-arg constructor is provided, will throw: <br>
	 * com.alibaba.fastjson.JSONException: default constructor not found.
	 */
	public UserInfo(){ }

	public UserInfo(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public String toString() {
		return getId() + "," + getName() + "," + getPassword();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

}
