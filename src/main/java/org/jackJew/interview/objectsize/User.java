package org.jackJew.interview.objectsize;

import java.lang.reflect.Field;
import java.util.Date;
//import sun.misc.Unsafe;

/**
 * 计算对象内存占用 shallow size - retained size.
 * <br> padding and alignment of JMM.
 */
public class User {

  private char[] f;  // padding
  private String c;
  private int a;
  private byte b;
  private Date d;
  private byte e;
  private boolean g;

  public static void main(String[] args) throws Exception {
//    Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//    theUnsafe.setAccessible(true);
//    Unsafe unsafe = (Unsafe) theUnsafe.get(null);
//
//    Field[] fields = User.class.getDeclaredFields();
//    for (Field fd : fields) {
//      System.out.println(fd.getName() + ": " + unsafe.objectFieldOffset(fd));
//    }
  }
}
