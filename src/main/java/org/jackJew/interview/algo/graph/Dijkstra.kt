package org.jackJew.interview.algo.graph

// Dijkstra shortest path. 适用于带权重的图路径计算.
class Dijkstra {
  private val num = 5 // points size
  // 邻接矩阵, MAX_VALUE = unreachable
  // 邻接矩阵vs邻接表:
  // 矩阵优点可以快速判断两顶点之间是否有边, 边可以快速增删; 缺点是对于稀疏图(边数远小于顶点数n*n时)
  //     比较浪费空间, 因为总需要 n*n 的matrix;
  // 邻接表优点是节省空间, 缺点是需要遍历一个链表去获得顶点的出入度s; 对于无向图的删边操作, 需要
  //     遍历两个链表定位删除; 邻接表对于有向图去算顶点入度, 需要逆邻接表或十字链表解决;
  private val matrix = Array(num) { IntArray(num) { Int.MAX_VALUE } }

  val pre = IntArray(num)  // 前驱节点

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
    val distance = IntArray(num) { Int.MAX_VALUE }
    val visited = BooleanArray(num) { false }

    // init
    for (index in 0 until num) {
      distance[index] = matrix[source][index]
    }
    visited[source] = true
    distance[source] = 0

    for (index in 1 until num) {
      // get min from neighbours
      var k = 0
      var shortest = Int.MAX_VALUE
      for (j in 0 until num) {
        if (!visited[j] && shortest > distance[j]) {
          shortest = distance[j]
          k = j
        }
      }
      visited[k] = true  // visited and shortest decided.

      for (j in 0 until num) {
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