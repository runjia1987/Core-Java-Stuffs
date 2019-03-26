package org.jackJew.interview.multithread

import java.util.concurrent.CyclicBarrier

/**
 * Try to print 12A34B56C...
 */
fun main() {
  val threads = arrayOf(PrintNumberTask(), PrintLetterTask())
  threads.forEach { it.start() }
  threads.forEach { it.join() }
}

val list = ArrayList<String>()
val barrier = CyclicBarrier(2, Runnable {
  if (list.size == 3) {
    list.apply {
      sort()
      forEach { print(it) }
      clear()
    }
  }
  step++
})
var step = 0

class PrintNumberTask: Thread() {

  override fun run() {
    while(true) {
      if (step >= 26)
        break
      list.add("${step * 2 + 1}")
      list.add("${step * 2 + 2}")
      barrier.await()
    }
  }
}

class PrintLetterTask: Thread() {

  override fun run() {
    while(true) {
      if (step >= 26)
        break
      list.add((step + 65).toChar().toString())
      barrier.await()
    }
  }
}
