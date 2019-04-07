package org.jackJew.interview.algo

/**
 * 求开方
 */
fun sqrt(input: Double, precision: Double): Double {
  if (input == 0.0) return 0.0
  var t = input / 2
  var r: Double
  while (true) {
    r = (t + input / t) / 2
    if (Math.abs(r * r  - input) <= precision) {
      break
    }
    t = r
  }
  return r
}

/**
 * 开立方
 */
fun cube(input: Double, precision: Double): Double {
  if (input == 0.0) return 0.0
  var t = input / 3
  var r: Double
  while(true) {
    r = (t * 2 + input / (t * t)) / 3
    if (Math.abs(r * r * r - input) <= precision) {
      break
    }
    t = r
  }
  return r
}

fun main() {
  println(sqrt(10000.0, 0.01))
  println(cube(1000.0, 0.01))
}