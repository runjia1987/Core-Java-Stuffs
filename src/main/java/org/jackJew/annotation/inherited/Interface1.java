package org.jackJew.annotation.inherited;


@InheritedAnno(content="Interface1 class定义在类型上的注解")
public interface Interface1 {
	
	@InheritedAnno(content="Interface1 method定义在方法上的注解")
	void print();

}
/*
 * 如果使用元注解(@Inherited)注解类以外的任何事物，此元注解类型都是无效的。
 * 还要注意，此元注解仅促成从超类superclass继承注解；对接口interface的注解则不促成继承。
 */