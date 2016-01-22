package org.jack.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class WriteCmd {
	
	public final static String File_Name = "d:\\file.bin";

	
	public static void main(String[] args) throws IOException {
		Model m = new Model();
		OutputStream os = new FileOutputStream(File_Name);
		ObjectOutputStream oous = new ObjectOutputStream(os);
		oous.writeObject(m);
		
		System.out.println("serialize completed.");
		oous.close();
		os.close();
	}

}
