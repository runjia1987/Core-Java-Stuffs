package org.jackJew.annotation.inherited;

import java.io.File;
import java.lang.StringIndexOutOfBoundsException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;

public class TestClientPackageScan {

	static ClassLoader contextLoader = TestClientPackageScan.class.getClassLoader();
			//Thread.currentThread().getContextClassLoader();
	private static List<Class<?>> classes = new ArrayList<Class<?>>(20);

	/**
	 * 测试扫描指定包名下的全部类具有的注解名称
	 */
	public static void main(String[] args) {
		String basePackageName = "org.jackJew.annotation";

		URL fullUrl = contextLoader.getResource(basePackageName.replace('.', '/'));
		
		String dirName = fullUrl.getFile().replace('/', File.separatorChar);
		
		scanClasses(basePackageName, dirName);
		for(Class<?> c : classes){
			System.out.println("扫描到类：" + c.getName());
			Annotation[] as = c.getAnnotations();	//查找每个类的注解
			for(Annotation a : as)
				System.out.println("\t扫描到注解：" + a.annotationType().getName());
			
			System.out.println();
		}
	}
	
	/**
	 * 扫描类并加载类
	 * @param basePackageName 基准包名
	 * @param dir 完整file路径
	 */
	public static void scanClasses(String basePackageName, String dir){
		
		System.out.println(dir);
		
		File packageDir = new File(dir);
		File[] classFiles = packageDir.listFiles();
		
		try {
			String className;
			for (File cf : classFiles) {
				if(cf.isDirectory()) {
					String newBasePackageName = basePackageName + "." + cf.getName();
					scanClasses(newBasePackageName, cf.getAbsolutePath());
				} else {
					className = basePackageName + "." + cf.getName().replace(".class", "");
					classes.add(Class.forName(className, false, contextLoader));
					//注意此与 Class.forName(cls)不同, false不初始化cls类(
										// 初始化类一般指静态变量与static初始化块)
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
