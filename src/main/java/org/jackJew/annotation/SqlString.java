package org.jackJew.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: String类型的数据库列映射 
 * @author zhurunjia
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlString {
	
	/**
	 * 列名称
	 */
	String name() default "";
	
	/**
	 * 列长
	 */
	int length() default 10;
	
	/**
	 * 列约束
	 */
	Constraints constrains() default @Constraints(color = Color.GREEN);

}
