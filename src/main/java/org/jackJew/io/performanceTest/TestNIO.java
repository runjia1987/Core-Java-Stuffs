package org.jackJew.io.performanceTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * Description: 测试NIO写入百万行abc文本
 * 
 * @author zhurunjia
 * 
 */
public class TestNIO {

	public static void main(String[] args) throws IOException {

		long beginTime = System.currentTimeMillis();
		FileChannel channel = new FileOutputStream("c:\\data.bin").getChannel();
		
		//MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, 1 << 23);
		ByteBuffer buf = ByteBuffer.allocateDirect(10000 * 5 + 1);

		int count = 0, j = 0;
		StringBuilder sb = new StringBuilder(50001);

		while (count++ < 100) {
			sb.delete(0, sb.length());
			j = 0;
			while (j++ < 10000)
				sb.append("abc\r\n");
			
			buf.put(sb.toString().getBytes());
			buf.flip();
			channel.write(buf);
			buf.clear();
			
		}
		
		channel.close();

		System.out.println((System.currentTimeMillis() - beginTime) + "ms");
	}

}
