package org.jackJew.interview.calulator

import java.util.Scanner

fun main() {
  val scanner = Scanner(System.`in`)
  val out = System.out
  val processor = Processor()

  while (true) {
    val input = scanner.nextLine()
    val result = processor.interprete(input)
    out.println(result)
  }
}