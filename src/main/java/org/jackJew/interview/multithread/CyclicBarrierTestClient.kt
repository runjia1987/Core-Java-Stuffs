package org.jackJew.interview.multithread

import java.util.concurrent.CyclicBarrier

/**
 * Try to print 12A34B56C...
 */
fun main() {
  val threads = arrayOf(PrintNumberTask(), PrintLetterTask())
  threads.forEach { it.start() }
}

val list = arrayOfNulls<String>(3)
val barrier = CyclicBarrier(2, Runnable {
  if (list.size == 3) {
    list.forEach { print(it) }
  }
  step++
})
var step = 0

class PrintNumberTask: Thread() {

  override fun run() {
    while(true) {
      if (step >= 26)
        break
      list[0] = "${step * 2 + 1}"
      list[1] = "${step * 2 + 2}"
      barrier.await()
    }
  }
}

class PrintLetterTask: Thread() {

  override fun run() {
    while(true) {
      if (step >= 26)
        break
      list[2] = (step + 65).toChar().toString()
      barrier.await()
    }
  }
}
