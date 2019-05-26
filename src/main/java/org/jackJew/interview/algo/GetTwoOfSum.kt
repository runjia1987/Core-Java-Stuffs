package org.jackJew.interview.algo

// intArray is ordered, return the two index
fun get(intArray: IntArray, sum: Int): List<String> {
  val map = mutableMapOf<Int, Int>()
  val list = mutableListOf<String>()
  for (i in 0 until intArray.size) {
    val delta = sum - intArray[i]
    if (map.containsKey(delta)) {
      list.add("$i-${map[delta]!!}")
    } else
      map.put(intArray[i], i)
  }
  return list
}

fun main() {
  println(get(intArrayOf(2,1,3,4,5,6,7,8,5), 10).joinToString(","))
}