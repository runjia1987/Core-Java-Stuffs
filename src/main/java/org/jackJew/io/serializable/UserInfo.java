package org.jackJew.io.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <i>一个序列化与反序列化的方法执行顺序</i>:
 *	writeReplace()
 *	writeObject(ObjectOutputStream oos)
 *	readObject(ObjectInputStream ois)
 *	readResolve() 
 */
public class UserInfo implements java.io.Serializable {
	
	/**
	 * 序列化版本ID, 反序列化检查版本一致性
	 */
	private static final long serialVersionUID = 4594050065192424681L;
	private Integer id;
	private String username;
	private transient String password;	//不定义transient关键字也可实现此字段不被序列化;
										//定义了transient关键字也可实现此字段被序列化.
	public static String XXX = "XXX";
	
	/**
	 * 自定义<i>私有</i>的writeObject方法,
	 * <br> 注意：可先调用oos.defaultWriteObject()自动写入类的全部非transient字段(确保为第一个操作)，
	 * <br> 再调用writeXXX方法显式写入transient字段
	 */
	private void writeObject(ObjectOutputStream oos) throws IOException{
		System.out.println("writeObject method.");
		//oos.defaultWriteObject();
		oos.writeInt(id);
		oos.writeObject(username);
		oos.writeObject(password);
		oos.writeUTF(XXX);
	}
	
	/**
	 * 自定义私有的readObject方法,
	 * <br> 注意：可先调用ois.defaultReadObject()自动读取类的全部非transient字段(确保为第一个操作)，
	 * <br> 再调用readXXX方法显式读取transient字段
	 * <br> 等价于一个公有构造方法, 如果要达到非可变字段效果需使用保护性拷贝, 
	 * 		同时检查约束条件是否满足, 可抛出 InvalidObjectException .（Effective Java第56条）
	 */
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
		System.out.println("readObject method.");
		//ois.defaultReadObject();
		id = ois.readInt();
		username = (String) ois.readObject();
		password = (String)ois.readObject();
		XXX = ois.readUTF();
	}
	
	/**
	 * 在writeObject()方法前调用，在序列化之前替换对象.
	 */
	private Object writeReplace() {
		System.out.println("writeReplace method.");
        return this;  
    }
	
	/**
	 * 在readObject()方法后调用，在反序列化之后返回对象.
	 * <br> 可用来替代使用保护性拷贝的readObject方法, 例如返回单一实例(所有实例变量都应为transient)
	 */
	private Object readResolve() {
		System.out.println("readResolve method.");
        return this;  
    }
    
	public UserInfo(Integer id, String name, String password){
		this.id = id;
		this.username = name;
		this.password = password;
	}  

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
