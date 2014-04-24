package org.jackJew.io.fileDirPath;

import java.io.File;
import java.io.IOException;

public class TestDirPath {

	/**
	 * 输出:
	 * E:\workspace\JavaApp\
	 * E:\
	 */
	public static void main(String[] args) throws IOException {
		System.out.println(new File(".").getCanonicalPath());  // use getCanonicalPath()
		
		System.out.println(new File("/").getAbsolutePath());
	}

}
