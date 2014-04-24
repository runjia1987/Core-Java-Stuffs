package org.jackJew.designPattern;

import java.util.Date;

/**
 * 保护性拷贝
 * @author runjia
 */
public class ProtectiveCopy {
	
	private Date birthday;   //可变类型
	private String name;	 //不可变类型, 安全
	
	/**
	 * 构造方法升级
	 */
	public ProtectiveCopy(Date date, String name){
		this.birthday = new Date(date.getTime());  //保护性拷贝, 免疫于外部更改
		this.name = name;
	}
	
	public static void main(String[] args) {
		Date d = new Date();
		ProtectiveCopy instance = new ProtectiveCopy(d, "runjia");
		d.setYear(1970);  //外部更改1
		
		instance.getBirthday().setYear(1980);	//外部更改2
		
		System.out.println(instance);
	}

	public Date getBirthday() {
		//return birthday;	// 不安全, 被外部更改2破坏
		return (Date)(birthday.clone());	//返回新拷贝的对象, 或者下述方法
		//return new Date(birthday.getTime());
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		return birthday + " , " + name;
	}

}
