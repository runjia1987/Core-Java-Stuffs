package org.jackJew.io.performanceTest;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * Description: 测试标准IO写入百万行abc文本
 * 
 * @author zhurunjia
 * 
 */
public class TestIO {

	public static void main(String[] args) throws IOException {
		long beginTime = System.currentTimeMillis();

		FileOutputStream fout = new FileOutputStream("c:\\data.bin");
		BufferedOutputStream bos = new BufferedOutputStream(fout, 1000 * 50 + 1);

		int i = 0, j = 0;
		StringBuilder sb = new StringBuilder(50001);
		
		while (i++ < 100) {
			sb.delete(0, sb.length());
			j = 0;
			while (j++ < 10000) {
				sb.append("abc\r\n");
			}
			bos.write(sb.toString().getBytes());
		}
		
		bos.close();
		fout.close();

		System.out.println((System.currentTimeMillis() - beginTime) + "ms");
	}

}