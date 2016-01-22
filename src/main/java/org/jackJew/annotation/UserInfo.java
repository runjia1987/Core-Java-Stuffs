package org.jackJew.annotation;

/**
 * 
 * Description: UserInfo entity 
 * @author zhurunjia
 *
 */
@DbTable(name = "USERINFO")
public class UserInfo {

	/**
	 * 
	 */
	@SqlInteger(name="USERID", constraints = @Constraints(notNull=true,unique=true,primaryKey=true))
	private int userId;
	
	@SqlString(name="USERNAME", length=32, constrains = @Constraints(notNull=true,unique=true,consts={true,false,false}))
	private String userName;
	
	@SqlString(name="PASSWORD", constrains = @Constraints(notNull=true))
	private String password;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
