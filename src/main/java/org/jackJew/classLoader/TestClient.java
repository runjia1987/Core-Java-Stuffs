package org.jackJew.classLoader;

public class TestClient {

	/**
	 * test 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("getSystemClassLoader(): " + ClassLoader.getSystemClassLoader());
		// sun.misc.Launcher$AppClassLoader@xxxx
		
		System.out.println("custom FileSystemClassLoader.");
		FileSystemClassLoader fcl = new FileSystemClassLoader("D:\\");
		
		FileSystemClassLoader fc2 = new FileSystemClassLoader("D:\\");
		
		//注意: 如果AppClassLoader加载器(父)查找的目录($buildDir)中存在,
		//目标类的字节码, 将会直接加载, 而不会执行到自定义类加载器的findClass方法, 
		//会造成自定义类加载器无效的假象（需要删除掉$buildDir下的.class）.
		//
		//java.lang.ClassLoader类的loadClass方法执行步骤:  findLoadedClass(name),
		//												 parent.loadClass(name,resolve),
		//												 findClass(name);
		//一个类的定义加载器(defining loader)是该类引用的其他类的初始加载器(initializing loader).
		//假设A类引用了B类, A类最终由ExtClassLoader加载成功, 根据代理模型(delegation model),
		//B类会由BootStrapLoader与ExtClassLoader依次在对应目录中(JRE/lib/与JRE/lib/ext目录)尝试查找,
		//若无则加载失败, 抛出NoClassDefFoundError.
		Class<?> class1 = fcl.loadClass("org.jackJew.classLoader.PrintClass");
		class1.newInstance();
		
		Class<?> class_11 = fc2.loadClass("org.jackJew.classLoader.PrintClass");
		//java.lang.ClassCastException: loaded by different classloaders
		//System.out.println(class1.cast(class_11.newInstance()));
		
		System.out.println("\ncustom NetworkClassLoader");
		NetworkClassLoader ncl = new NetworkClassLoader("http://localhost:8080");
		Class<?> class2 = ncl.loadClass("org.jackJew.classLoader.PrintClass");
		class2.newInstance();
		
		//java.lang.ClassCastException: loaded by different classloaders
		System.out.println(class1.cast(class2.newInstance()));
		
		
	}

}