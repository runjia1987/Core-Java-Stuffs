package org.jackJew.interview.algo

/**
 * if we have N elements [a[0], a[1]... a[N-1] ], a[i] value in [1..N], check whether [1..N] exists.
 *
 * a[i], m = a[i] count, t = N+1
 * (a[i] + m * t)/t = m
 * (a[i] + m * t)%t = a[i]
 */
fun main() {
  val array = arrayOf(2, 5, 3, 7, 8, 10, 6, 8, 9, 8, 10)
  val list = mutableListOf<Int>()
  val t = array.size + 1
  println(t)
  for (index in array.indices) {
    array[array[index] % t - 1] += t
  }

  for (index in array.indices) {
//    if (array[index] / t == 0) {
//      list.add(index + 1)
//    }
    list.add(array[index] / t)
  }

  println(list)
}