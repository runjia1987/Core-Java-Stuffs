package org.jackJew.interview.multithread

/**
 * Try to print 12A34B56C...
 */

val obj = Object()
var steps = 0
var lastStr: String = "0"

fun main() {
  val threads = arrayOf(PrintNumber(), PrintLetter())
  threads.forEach { it.start() }
  threads.forEach { it.join() }
}

class PrintNumber : Thread() {
  override fun run() {
    while(true) {
      synchronized(obj) {
        while (lastStr != "0" && lastStr != (steps + 64).toChar().toString()) {
          obj.wait()
        }
        if (steps >= 26) {
          obj.notify()  // notify PrintLetter
          return // thread exit
        }
        print("${steps * 2 + 1}")
        lastStr = "${steps * 2 + 2}"
        print(lastStr)
        obj.notify()
      }
    }
  }
}

class PrintLetter : Thread() {
  override fun run() {
    while(true) {
      synchronized(obj) {
        while (steps < 26 && (!isDigit(lastStr) || lastStr.toInt() != steps * 2 + 2)) {
          obj.wait()
        }
        if (steps >= 26) {
          return // thread exit
        }
        lastStr = ('A' + steps).toString()
        print(lastStr)
        steps++
        obj.notify()
      }
    }
  }
}

fun isDigit(input: String) =
  input.chars().allMatch { it in 48..57 }
