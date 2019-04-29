package org.jackJew.interview.algo

import java.util.*

fun main() {
  val bitset = BitSet()
  bitset.set(63)
  bitset.set(1 shl 30)  // 1<<30
  println(bitset.get(1 shl 30))
}