package org.jackJew.interview.algo

fun solution(n: Int) : Int {
  if (n == 1) return 1
  if (n == 2) return 2
  else
    return solution(n-1) + solution(n-2)
}

fun solution2(n: Int) : Long {
  if (n == 1) return 1
  if (n == 2) return 2
  var index = 2
  val array = longArrayOf(1,2,0)
  while (index < n) {
    array[2] = array[0] + array[1]
    array[0] = array[1]
    array[1] = array[2]
    index++
  }
  return array[2]
}

fun main() {
  print(solution2(25))
}