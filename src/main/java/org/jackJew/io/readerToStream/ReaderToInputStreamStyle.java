package org.jackJew.io.readerToStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

/**
 * OutputStreamWriter -> ByteArrayOutputStream, write to char[], call
 * toByteArray().
 */
public class ReaderToInputStreamStyle {
	
	private static final String fileName = "d:\\pom.xml";
	
	private static final int bufferSie = 1 << 9;

	/**
	 * convert a Reader object to InputStream object
	 */
	public static InputStream convertToInputStream(Reader reader, String charset) {
		ByteArrayOutputStream bous = new ByteArrayOutputStream();
		OutputStreamWriter osw = null;
		char[] buffer = new char[bufferSie];
		int count = 0;
		try {
			//osw = new OutputStreamWriter(bous, charset);
			
			while ((count = reader.read(buffer)) != -1) {
				//osw.write(buffer, 0, count);
				bous.write(new String(buffer, 0, count).getBytes(charset));
			}
			//osw.flush();
			
			bous.flush();
			ByteArrayInputStream bois = new ByteArrayInputStream(bous.toByteArray());
			return bois;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(osw != null) {
				try {
					osw.close(); } catch (IOException e) { }
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		String charset = "UTF-8";
		// create a FileReader
		FileReader fileReader = new FileReader(new File(fileName));
		
		// transform to InputStream
		InputStream ins = convertToInputStream(fileReader, charset);
		byte[] buffer = new byte[bufferSie];
		int read = 0;
		while( (read = ins.read(buffer)) != -1){
			System.out.print(new String(buffer, 0, read, charset));
		}
		
		fileReader.close();
		ins.close();
	}

}
