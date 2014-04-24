package org.jackJew.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Description: 字段约束
 * 三个内置标准注解：@Deprecated, @SuppressWarnings, @Override
 * 四个元注解(meta annotation)
 * @author zhurunjia
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Constraints {
	
	/**
	 * 注解元素：八个基本数据类型,String,Class,Annotation,enum,与上述类型(8+4)的数组
	 * <br> 是否为主键字段
	 */
	boolean primaryKey() default false;
	
	boolean[] consts() default {true, false, false};
	
	Class<AnnotationProcessor> processor() default AnnotationProcessor.class;
	
	Color color() default Color.BLUE;
	
	/**
	 * 是否为非空字段
	 */
	boolean notNull() default false;
	
	/**
	 * 是否为唯一字段
	 */
	boolean unique() default false;

}
enum Color {
	RED(1), GREEN(2), BLUE(3);
	
	Color(int i){
		
	}
}
