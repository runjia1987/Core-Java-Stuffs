package org.jackJew.interview.algo

/**
 * 折叠二分查找
 */
val array = intArrayOf(100, 108, 150, 388, 988, 6, 26, 38, 72, 80)
fun binarySearchBreak(array: IntArray, left: Int, right: Int, ele: Int): Int {
  if (left == right) {
    return left
  }
  while (left < right) {
    var mid = (left + right) ushr 1
    if (ele == array[mid])
      return mid
    else if (ele < array[mid]) {
      return if (ele < array[left]) {
        binarySearchBreak(array, mid, right, ele)
      } else {
        binarySearchBreak(array, left, mid, ele)
      }
    } else {
      return if (ele < array[left]) {
        binarySearchBreak(array, mid, right, ele)
      } else {
        binarySearchBreak(array, left, mid, ele)
      }
    }
  }
  return -1
}

fun main() {
  println(binarySearchBreak(array, 0, array.size - 1, 150))
  println(binarySearchBreak(array, 0, array.size - 1, 38))
}