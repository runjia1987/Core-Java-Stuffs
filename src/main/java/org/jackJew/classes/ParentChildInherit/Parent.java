package org.jackJew.classes.ParentChildInherit;

import java.util.List;

public class Parent {

	int val = 100;
	static String XYZ = "parent static value";

	static String getStr() {
		return XYZ;
	}
	
	public int getVal() {
		return this.val;
	}

	public Parent() {
	}

	public Parent(int i) {
		System.out.println("父类构造方法被调用.");

		dosth();

		// 构造函数一定不能调用可能被子类改写的方法，如public和protected级别的域方法。
		// 由于父类的初始化早于子类的初始化，如果此时调用的方法被子类覆盖，而子类中的此覆盖方法中又引用了子类中尚未初始化的成员变量，
		// 这将很容易导致NullPointerException异常被抛出.
	}

	protected void dosth() {
		System.out.println("parent dosth()");
	}

	/**
	 * 父类对外的统一方法接口
	 */
	public void unifiedJob() {
		dosth();
	}

	protected List<?> getList() {
		return null;
	}

	private int ABC = 1000000;

	public int getABC() {
		return ABC;
	}

}
