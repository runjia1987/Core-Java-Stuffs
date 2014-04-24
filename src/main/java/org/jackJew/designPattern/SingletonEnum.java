package org.jackJew.designPattern;

public enum SingletonEnum {

	INSTANCE;

	public static SingletonEnum getInstance() {
		return INSTANCE;
	}

}
