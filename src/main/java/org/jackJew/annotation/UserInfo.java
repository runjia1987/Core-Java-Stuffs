package org.jackJew.annotation;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * Description: UserInfo entity 
 * @author zhurunjia
 *
 */
@DbTable(name = "USERINFO")
public class UserInfo {

  public static void remoteNums(Map<String, Integer> map) {
    Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator();
    while (itr.hasNext()) {
      Map.Entry entry = itr.next();
      Integer value = (Integer) entry.getValue();
      if (value != null) {
        if (value % 2 == 1) {
          itr.remove();
        }
      }
    }
  }

  public static int search(int nums[], int target) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
      int mid = (left + right) / 2;
      if (nums[mid] > target) {
        if (mid == 0 || nums[mid - 1] < target) return mid;
        right = mid - 1;
      } else {
        if (mid == right || nums[mid + 1] > target) return mid + 1;
        left = mid - 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(search(new int[] {1,2,3,4,6,7}, 0));

    Map<String, Integer> map = new HashMap<>();
    map.put("abc", 1);
    map.put("bcd", 2);
    map.put("cde", -3);
    remoteNums(map);
    assert(map.size() == 1 && map.containsKey("bcd"));
  }

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
