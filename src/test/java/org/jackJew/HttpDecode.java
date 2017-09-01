package org.jackJew;

import com.google.common.collect.Lists;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HttpDecode {

  public static void main(String[] args) {
    Character[] array = {0x1f, 0x8b, 0x8};
    String charset = "GBK";
    List<Byte> bytes = Lists.newArrayList();

    List<Character> list = Arrays.asList(array);
    Arrays.asList(array).forEach(c -> {
      bytes.add((byte)(c.charValue() >> 8));
      bytes.add((byte)(c.charValue() & 0x00ff));
    } );
    byte[] barray = {(byte)0x1f, (byte)0x8b, (byte)0x8, (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0 ,(byte)0x3};
    byte[] barray2 = {(byte)0xe7, (byte)0x83, (byte)0x9b, (byte)0x1, (byte)0x0, (byte)0x0 };

    byte[] barray3 = {(byte)0xe6,(byte)0x9c,(byte)0xb1,(byte)0xe5,(byte)0x85,(byte)0x88,(byte)0xe7,(byte)0x94,(byte)0x9f};
    try {
      System.out.println(new String(barray2, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
