package org.jackJew.interview.algo.graph

// Dijkstra shortest path. 适用于带权重的图路径计算.
class Dijkstra {
  private val NUM = 5 // points size
  // MAX_VALUE = unreachable
  private val matrix = Array(NUM) { IntArray(NUM) { Int.MAX_VALUE } }

  val pre = IntArray(NUM)  // 前驱节点

  init {
    matrix[0][1] = 3
    matrix[1][0] = 3

    matrix[0][2] = 4
    matrix[2][0] = 4

    matrix[1][2] = 5
    matrix[2][1] = 5

    matrix[1][3] = 4
    matrix[3][1] = 4

    matrix[2][3] = 8
    matrix[3][2] = 8

    matrix[2][4] = 12
    matrix[4][2] = 12

    matrix[3][4] = 3
    matrix[4][3] = 3
  }

  fun calc(source: Int): IntArray {
    val distance = IntArray(NUM) { Int.MAX_VALUE }
    val visited = BooleanArray(NUM) { false }

    // init
    for (index in 0 until NUM) {
      distance[index] = matrix[source][index]
    }
    visited[source] = true
    distance[source] = 0

    for (index in 1 until NUM) {
      // get min from neighbours
      var k = 0
      var shortest = Int.MAX_VALUE
      for (j in 0 until NUM) {
        if (!visited[j] && shortest > distance[j]) {
          shortest = distance[j]
          k = j
        }
      }
      visited[k] = true  // visited and shortest decided.

      for (j in 0 until NUM) {
        if (!visited[j] && matrix[k][j] != Int.MAX_VALUE) { // not visited, reachable
          val length = shortest + matrix[k][j]
          if (distance[j] > length) {
            distance[j] = length
            pre[j] = k
          }
        }
      }
    }
    return distance
  }
}

fun main() {
  val dijkstra = Dijkstra()
  val distance = dijkstra.calc(0)
  println(distance.joinToString(", "))
  println(dijkstra.pre.joinToString(", "))
}