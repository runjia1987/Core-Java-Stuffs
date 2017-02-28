package org.jackJew.io.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化写入读出测试
 * @author zhurunjia
 *
 */
public class SerializableTest {
	
	private UserInfo user = new UserInfo(123, "runjia", "admin");
	
	public static void main(String[] args){
		
		//new SerializableTest().byteArrayReadWrite();
		new SerializableTest().fileSystemReadWrite();
		
	}
	
	/**
	 * 通过文件系统
	 */
	public void fileSystemReadWrite(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:\\data.bin"));
			oos.writeObject(user);	//注意在此方法调用时，对传递的实现了Serializable接口的对象，检查其是否实现了private的writeObject(oos)方法，如有则调用该方法，否则调用默认方法			
			oos.flush();
			oos.close();
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:\\data.bin"));
			Object u = ois.readObject();	//注意在此方法调用时，对传递的实现了Serializable接口的对象，检查其是否实现了private的readObject(ois)方法，如有则调用该方法，否则调用默认方法
			ois.close();

			//下面的语句为false, 因为新建对象了. 可改写readResolve方法返回同一个实例(Singleton单例)
			System.out.println("恢复的对象是否==：" + (u == user));  //false
			
			System.out.println(u.toString());
			//System.out.println(u.getSerialversionuid());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过内存中的字节数组流
	 */
	public void byteArrayReadWrite(){
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(user);
			oos.flush();
			
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			UserInfo u = (UserInfo)ois.readObject();
			
			//下面为false, 因为新建对象了.
			//可改写readResolve方法返回同一个实例(Singleton单例, Enum)
			System.out.println("恢复的对象是否==：" + (u == user));  //false
			
			System.out.println(u.getId() + "," + u.getUsername() + "," + u.getPassword());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}