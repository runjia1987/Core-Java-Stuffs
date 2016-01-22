package org.jackJew.classes;

import java.lang.ThreadLocal;
import java.util.Locale;


public abstract class AbstractClass {
	
	public void print(){		
		
		System.out.println("print()...");
	}
	
	//private abstract void job();	//注意抽象不能被修饰为private, 因为其必被子类所可见
	
	public static void main(String[] args){
		Locale[] locales = Locale.getAvailableLocales();
		for(Locale lc : locales){
			System.out.println(lc);
		}
		
		System.out.println("Default: " + Locale.getDefault());
	}

}
