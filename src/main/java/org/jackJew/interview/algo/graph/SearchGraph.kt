package org.jackJew.interview.algo.graph

import java.util.LinkedList
import java.util.Stack

// 无向有向图的遍历
data class Point(val id: Int,
                 val neighbours: List<Point>)

class Graph(private val root: Point) {

  // 广度优先搜索
  fun bfs() {
    val visited = mutableSetOf<Int>()  // id集合
    val queue = LinkedList<Point>()
    queue.offer(root)

    while (!queue.isEmpty()) {
      val cur = queue.poll()
      if (!visited.contains(cur.id))
        println(" $cur")

      for (point in cur.neighbours) {
        if (visited.add(point.id)) {
          queue.offer(point)
          println(" $point")
        }
      }
    }
  }

  // 深度优先遍历
  fun dfs() {
    val visited = mutableSetOf<Int>()
    val stack = Stack<Point>()
    stack.push(root)
    visited.add(root.id)

    while (!stack.isEmpty()) {
      val cur = stack.pop()
      println(" $cur")
      for (point in cur.neighbours.reversed()) {
        if (visited.add(point.id)) {
          stack.push(point)
        }
      }
    }
  }
}

fun main() {
  val graph = Graph(Point(1, listOf(
      Point(2, listOf(
          Point(5, listOf()),
          Point(6, listOf())
      )),
      Point(3, listOf(
          Point(7, listOf()),
          Point(8, listOf())
      )),
      Point(4, listOf(
          Point(9, listOf())
      ))
  )))
  println("bfs:")
  graph.bfs()

  println("dfs:")
  graph.dfs()
}