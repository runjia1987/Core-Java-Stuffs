package org.jackJew.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Description: Integer类型的数据库列映射  
 * @author zhurunjia
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlInteger {
	
	/**
	 * 列的默认值
	 */
	int value() default 0;
	
	/**
	 * 列名称
	 */
	String name() default "";
	
	/**
	 * 约束
	 */
	Constraints constraints() default @Constraints;

}
