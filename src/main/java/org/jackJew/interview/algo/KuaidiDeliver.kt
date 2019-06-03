package org.jackJew.interview.algo

//快递员送货-最短路线规划(含返程)
class Point(val px: Int,
            val py: Int,
            var visited: Boolean = false) {

  fun getLength(target: Point) =
      Math.abs(target.px - px) + Math.abs(target.py - py)
}

val START = Point(0, 0)
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
  var depth = solution(START, nodes1, 0, 0)
  println(depth) //30

  depth = solution(START, nodes2, 0, 0)
  println(depth) //28
}

val nodes1 = listOf(
    Point(2,2),
    Point(2,8),
    Point(4,4),
    Point(7,2))

val nodes2 = listOf(
    Point(2,2),
    Point(2,8),
    Point(6,6))
