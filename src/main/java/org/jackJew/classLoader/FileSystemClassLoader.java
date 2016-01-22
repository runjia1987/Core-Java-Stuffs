package org.jackJew.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 自定义一个文件系统的类加载器
 * @author zhurunjia
 */
public class FileSystemClassLoader extends ClassLoader {
	
	private String rootDir;	//查找的基准目录
	
	public FileSystemClassLoader(String rootDir){
		this.rootDir = rootDir;
	}

	/**
	 * className格式：org.jackJew.Class1
	 */
	@Override
	protected Class<?> findClass(String className) {		
		byte[] bytesData = getClassFileBytes(className);
		
		System.out.println("字节码文件的字节长：" + bytesData.length);
		return defineClass(className, bytesData, 0, bytesData.length);
	}
	
	private byte[] getClassFileBytes(String className){
		String path = rootDir + File.separator + className.replace('.', File.separatorChar) + ".class";
		
		try {
			FileInputStream fi = new FileInputStream(path);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[512];
			int numRead = -1;
			while( (numRead=fi.read(buffer)) != -1)
				bos.write(buffer, 0, numRead);
			
			fi.close();buffer = null;
			
			return bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return null;
	}

}
