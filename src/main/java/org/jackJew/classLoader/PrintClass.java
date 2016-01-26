package org.jackJew.classLoader;

public class PrintClass {
	
	public PrintClass(){
		System.out.println("::: PrintClass constructor called.");
		print();
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		new PrintClass();
	}
	
	/**
	 * print the classloader hierarchy: <br/>
	 * sun.misc.Launcher$ AppClassLoader@19821f <br/>
	 * sun.misc.Launcher$ ExtClassLoader@addbf1 <br/>
	 * null
	 */
	private void print(){
		ClassLoader loader = PrintClass.class.getClassLoader();
		System.out.println("print the classloader hierarchy:");
		while(true) {
			System.out.println("classloader -> " + loader);
			if(loader == null) {
				break;
			}
			loader = loader.getParent();
		}
	}

}
