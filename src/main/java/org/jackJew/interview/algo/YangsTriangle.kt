package org.jackJew.interview.algo

// 计算杨辉三角
fun calc(n: Int): List<Int> {
  if (n == 1) return listOf(1)
  if (n == 2) return listOf(1, 1)
  var list = mutableListOf(1, 1)
  for (index in 2 until n) {
    val res = mutableListOf<Int>()
    res.add(1)
    for (j in 0 until list.size - 1) {
      res.add(list[j] + list[j+1])
    }
    res.add(1)

    list = res
  }
  return list
}

fun main() {
  println(calc(3).joinToString(","))
  println(calc(4).joinToString(","))
}