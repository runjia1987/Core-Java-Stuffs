package org.jackJew.interview.algo

//快递员送货路线规划
class Point {
  val px: Int = 0
  val py: Int = 0
  var visited = false

  fun getLength(target: Point) =
      Math.abs(target.px - px) + Math.abs(target.py - py)
}

val START = Point()
var minDistance = Integer.MAX_VALUE

// sum distance
fun solution(start: Point, nodes: List<Point>, sum: Int, count: Int): Int {
  if (count == nodes.size) {
    minDistance = Math.min(minDistance, sum + start.getLength(START))
    return minDistance
  }
  for (index in 0 until nodes.size) {
    if (!nodes[index].visited) {
      var s = sum + nodes[index].getLength(start)
      if (s < minDistance) {
        nodes[index].visited = true
        solution(nodes[index], nodes, s, count + 1)
      }
      s -= nodes[index].getLength(start)
      nodes[index].visited = false
    }
  }
  return minDistance
}

fun main() {
  val depth = solution(START, listOf(), 0, 0)
  println(depth)
}