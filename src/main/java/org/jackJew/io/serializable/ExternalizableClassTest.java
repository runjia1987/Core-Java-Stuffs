package org.jackJew.io.serializable;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * 
 * 序列化类的另一个测试, Externalizable 比 Serializable 提供更多的可控性.
 * @author zhurunjia
 *
 */
public class ExternalizableClassTest implements Externalizable {
	
	private int number;
	private String name;

	public ExternalizableClassTest() {
		System.out.println("deserialize start..., invoke non-arg constructor(required).");
		System.out.println(number + "," + name);
	}
	
	public ExternalizableClassTest(Integer number, String name){
		this.number = number;
		this.name = name;
	}

	@Override
	public void writeExternal(ObjectOutput oo) throws IOException {
		oo.writeInt(number);
		oo.writeObject(name);
	}

	@Override
	public void readExternal(ObjectInput oi) throws IOException,
			ClassNotFoundException {
		number = oi.readInt();
		name = (String)oi.readObject();
	}
	
	public String toString(){
		return number + "," + name;
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("starting test...");
		ExternalizableClassTest ect = new ExternalizableClassTest(100, "admin");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c:\\data.bin"));
		oos.writeObject(ect);
		oos.flush();
		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c:\\data.bin"));
		ExternalizableClassTest ect2 = (ExternalizableClassTest)ois.readObject();
		System.out.println(ect2.toString());
		ois.close();

	}

}
