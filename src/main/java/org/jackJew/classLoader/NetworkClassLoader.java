package org.jackJew.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class NetworkClassLoader extends ClassLoader {
	
	private String rootUrl;
	
	public NetworkClassLoader(String rootUrl){
		this.rootUrl = rootUrl;
	}

	@Override
	protected Class<?> findClass(String className) {
		byte[] bytesData = getClassFileBytes(className);
		
		System.out.println("网络流的字节长：" + bytesData.length);
		return defineClass(className, bytesData, 0, bytesData.length);
	}
	
	private byte[] getClassFileBytes(String className){
		String url = rootUrl + className.replace('.', '/') + ".class";
		
		try {
			InputStream is = new java.net.URL(url).openStream();
			
			byte[] buffer = new byte[512];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int numRead = -1;
			while( (numRead=is.read(buffer)) != -1)
				bos.write(buffer, 0, numRead);
			
			is.close();buffer = null;
			
			return bos.toByteArray();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return null;
	}	

}
