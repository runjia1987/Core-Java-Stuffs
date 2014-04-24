package org.jackJew.annotation.inherited;

//@InheritedAnno(content="Child class")
public class Child extends Parent implements Interface1 {
	
	/**
	 * 注意此处覆盖了父类的方法具有的注解
	 */	
	/*
	@Override
	@InheritedAnno(content="child method定义的注解")
	public void print() {
		
	}*/

}
