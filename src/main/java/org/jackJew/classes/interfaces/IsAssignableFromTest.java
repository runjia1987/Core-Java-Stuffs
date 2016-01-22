package org.jackJew.classes.interfaces;

import java.sql.Timestamp;
import java.util.AbstractSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

interface Interface2 extends Interface {

}
public class IsAssignableFromTest {
	
	public static void main(String... args){
		//父接口关系
		System.out.println(Interface.class.isAssignableFrom(Interface2.class));	 //true
		//接口与实现类关系
		System.out.println(Set.class.isAssignableFrom(AbstractSet.class)); 		//true
		//父类关系
		System.out.println(Date.class.isAssignableFrom(Timestamp.class));	//true
		//相同原始类型
		System.out.println(int.class.isAssignableFrom(int.class));		//true
		//不同原始类型
		System.out.println(int.class.isAssignableFrom(long.class));		//false
	}
	
}