package org.jack.serialization;

import java.io.IOException;
import java.io.ObjectStreamException;

public class Model implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private int id = 100;
	
	private String name = "jack";
	
	public String password = "12345678";
	
	private short value;
	
	private String str;
	
	public Model() { }
	
	public Object writeReplace() throws ObjectStreamException {
		System.out.println("writeReplace called");
		return this;
	}
	
	public Object readResolve() throws ObjectStreamException {
		System.out.println("readResolve called.");
		return this;
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		System.out.println("writeObject called.");
		out.defaultWriteObject();
		//out.writeInt(id);
		//out.writeUTF(name);
		//out.writeUTF(password);
		
		//out.writeShort(value);
	}
	
	private void readObject(java.io.ObjectInputStream in)
    			throws IOException, ClassNotFoundException {
		System.out.println("readObject called.");
		in.defaultReadObject();
		//id = in.readInt();
		//name = in.readUTF();
		//password = in.readUTF();
		
		//value = in.readShort();
	}
	
	public String toString() {
		return this.id + "," + this.name + "," + this.password + "," + this.str;
	}

}
