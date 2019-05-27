package org.jackJew.interview.algo

// intArray is ASC ordered, return the two index
fun get(intArray: IntArray, sum: Int) : List<Int> {
  var head = 0
  var tail = intArray.size - 1
  while (head != tail) {
    val temp = intArray[head] + intArray[tail]
    if (temp == sum) {
      return listOf(head, tail)
    } else if (temp < sum) {
      head++
    } else
      tail --
  }
  return listOf()
}

// intArray is not ordered, return the two index
fun get2(intArray: IntArray, sum: Int): List<String> {
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
  println(get(intArrayOf(1,2,3,5,5,6,7), 10))

  println(get2(intArrayOf(2,1,3,4,5,6,7,8,5), 10).joinToString(","))
}