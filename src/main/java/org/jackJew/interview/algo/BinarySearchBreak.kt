package org.jackJew.interview.algo

/**
 * 折叠二分查找
 */
val array = intArrayOf(100, 108, 150, 388, 988, 1000, 6, 26, 38, 72, 80, 81, 82, 83, 84)
//(100,110,120,1,2,30,40,50,60,70,80,90)

fun binarySearchBreak(array: IntArray, left: Int, right: Int, ele: Int): Int {
  if (left == right) {
    return left
  }
  while (left < right) {
    val mid = (left + right) ushr 1
    return if (ele == array[mid])
      mid
    else if (mid == left && ele != array[right])
      -1
    else if (array[left] < array[mid]) {
      if (ele > array[left] && ele < array[mid]) {
        binarySearchBreak(array, left, mid, ele)
      } else {
        binarySearchBreak(array, mid, right, ele)
      }
    } else {
      if (ele < array[right] && ele < array[mid]) {
        binarySearchBreak(array, left, mid, ele)
      } else {
        binarySearchBreak(array, mid, right, ele)
      }
    }
  }
  return -1
}

fun main() {
  println(binarySearchBreak(array, 0, array.size - 1, 2))
  println(binarySearchBreak(array, 0, array.size - 1, 38))
}