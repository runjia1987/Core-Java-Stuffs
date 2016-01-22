package org.jackJew.classes.interfaces;

/**
 * interface的内嵌
 * @author zhurunjia
 */
public interface Interface {

	String str = "123"; 	// equals to "public static final"

	public void job();	//equals to "public"	

	/**
	 * 仿java.util.Map.Entry设计
	 * @author zhurunjia
	 */
	interface Map<K, V> {
		
		interface Entry<K, V> {
			
			int code = 100;
			
			K getKey();

			V getValue();

			V setValue(V newValue);
			
			boolean equals(Object object);
			
			int hashCode();
		}
	}

}
