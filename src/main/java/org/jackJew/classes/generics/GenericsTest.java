package org.jackJew.classes.generics;

import java.util.List;

public class GenericsTest {
	
	static void job(List<Object> list){
		list.add(123);
	}
	
	static void call(){
		List<String> stringList = null;
		//job(stringList);    // not applicable
	}

}
