package org.jackJew.classLoader;

public class PrintClass {
	
	public PrintClass(){
		System.out.println("::: PrintClass构造器被调用");
		print();
	}

	/**
	 * <br> 输出:
	 * sun.misc.Launcher$AppClassLoader@19821f
	 * sun.misc.Launcher$ExtClassLoader@addbf1
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		new PrintClass();
	}
	
	/**
	 * 打印出当前类的全部加载器
	 * <br>输出:
	 * org.jackJew.classLoader.FileSystemClassLoader@61de33
	 * sun.misc.Launcher$ AppClassLoader@19821f
	 * sun.misc.Launcher$ ExtClassLoader@addbf1
	 */
	private void print(){
		ClassLoader loader = PrintClass.class.getClassLoader();
		System.out.println("输出调用的类加载器树：");
		while(loader != null){
			System.out.println(loader);
			loader = loader.getParent();
		}
	}

}
