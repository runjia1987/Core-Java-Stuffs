package org.jack.serialization;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ReadCmd {

	public static void main(String[] args) throws Exception {
		//Model.password = "1234";
		//System.out.println("Model static password: " + Model.password);		
		
		InputStream ins = new FileInputStream(WriteCmd.File_Name);
		ObjectInputStream ois = new ObjectInputStream(ins);
		Model m = (Model)ois.readObject();
		
		System.out.println("deserialize completed. " + m.toString());
		
		// note the static field has been modified by Java deserialization readObject(*)
		//System.out.println("Model static password: " + Model.password);
	}

}
