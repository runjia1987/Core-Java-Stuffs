package org.jackJew.interview.algo

// 两个线程分别打印元音字母（a,e,i,o,u）， 另一个打印其他字母
val set = setOf('a', 'e', 'i', 'o', 'u')

val lock = Object()
var lastChar = 'a' - 1

fun main() {
  val a = Yuan()
  val b = Normal()
  a.start()
  b.start()
  a.join()
  b.join()
}
//打印元音
class Yuan: Thread() {
  override fun run() {
    while (true) {
      synchronized(lock) {
        while (!set.contains(lastChar + 1)) {
          if (lastChar == 'z') {
            return
          }
          lock.wait()
        }
        print(++lastChar)
        lock.notify()
      }
    }
  }
}
//打印其他
class Normal: Thread() {
  override fun run() {
    while (true) {
      synchronized(lock) {
        while (set.contains(lastChar + 1)) {
          lock.wait()
        }
        if (lastChar == 'z') {
          lock.notify()
          return
        }
        print(++lastChar)
        lock.notify()
      }
    }
  }
}
