package org.jackJew.interview.algo

class RemoveDuplicatesInplace {
}
val intArray = intArrayOf(1,2,3,3,3,7,10,55,55)

fun main() {
  val size = intArray.size
  var curr = 0
  var next = 0
  while (next < size) {
    if (intArray[curr] == intArray[next])
      next++
    else {
      intArray[++curr] = intArray[next++]
    }
  }
  println("length is ${curr + 1}")
  println(intArray.joinToString(","))
}