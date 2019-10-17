package org.jackJew.classes;

public class Prime {

  static int getNextPrime(int s) {
    if (s == 1) return 2;
    if (s == 2) return 3;
    if (s == 3 || s == 4) return 5;
    while (s++ < Integer.MAX_VALUE) {
      int d = s % 6;
      if (d != 1 && d != 5) { // prime is 6n-1 or 6n + 1
        continue;
      }
      d = s < 25 ? s : (int) Math.sqrt(s);
      int i = 5;
      while (i <= d) {
        if (s == i || s == i + 2) return s;
        if (s % i == 0 || s % (i + 2) == 0) { //ignore 2m and 3m
          break;
        }
        i += 6;
      }
      if (i > d)
        return s;
    }
    throw new IllegalArgumentException("too large input " + s);
  }

  static class A {
    A() {
      i = (j++ != 0) ? ++j : --j;
    }

    int i;
    static int j;
  }

  public static void main(String[] args) {
    A a1 = new A();
    System.out.println(a1.i);
    System.out.println(a2.i);

    System.out.println(getNextPrime(5));
    System.out.println(getNextPrime(256));
    System.out.println(getNextPrime(1024));
  }

  static A a2 = new A();

}
