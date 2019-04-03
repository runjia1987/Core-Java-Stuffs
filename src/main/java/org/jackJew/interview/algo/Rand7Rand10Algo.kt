package org.jackJew.interview.algo

import kotlin.random.Random

/**
 * 1 ~ 7
 */
fun rand7() = 1 + Random.nextInt(7)

/**
 * Based on rand7 function.
 */
fun rand10(): Int {
  // 7 * (rand7() - 1) + rand7()
  var com = 7 * (rand7() - 1) + rand7()
  if (com <= 40)
    return com % 10 + 1

  com -= 40 // 1-9
  // 7 * (com - 1) + rand7()
  com = 7 * (com - 1) + rand7()
  if (com <= 60)
    return com % 10 + 1

  com -= 60  // 1-3
  // 7 (com - 1) + rand7()
  com = 7 * (com - 1) + rand7()
  if (com <= 20)
    return com % 10 + 1

  return rand10()
}

/**
 * Based on rand7 function.
 */
fun rand5(): Int {
  // 7 * (rand7 - 1) + rand7
  var com = 7 * (rand7() - 1) + rand7()
  if (com <= 45)
    return com % 5 + 1

  com -= 45 // 1-4
  com = 7 * (com - 1) + rand7()
  if (com <= 25)
    return com % 5 + 1

  com -= 25 // 1-3
  com = 7 * (com - 1) + rand7()
  if (com <= 20)
    return com % 5 + 1

  return rand5()
}

fun main() {
  repeat(10) { println("${it+1} times rand10(): " + rand10())}

  repeat(10) { println("${it+1} times rand5(): " + rand5())}
}