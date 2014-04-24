package org.jackJew.deskUI;

import org.jackJew.classes.enums.EnumTest;

public class EnumTestSub extends EnumTest {
	
	static {
		EnumTest.colors = null;		//protected修饰的变量在类继承中的可访问性
	}

}
