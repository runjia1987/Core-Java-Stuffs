package org.jackJew.interview.algo

import kotlin.random.Random

/**
 * 1 ~ 7
 */
fun rand7() = 1 + Random.nextInt(7)

fun rand5() = 1 + Random.nextInt(5)

fun calcRand7(): Int {
  var num = rand5() + 5 * (rand5() - 1) // 1-25
  if (num <= 21) return num % 7 + 1
  num -= 21 // 1-4

  num = 5 * (num - 1) + rand5() // 1-20
  if (num <= 14) return num % 7 + 1
  num -= 14 // 1-6

  num = 5 * (num - 1) + rand5() // 1-30
  if (num <= 28) return num % 7 + 1

  return calcRand7()
}

/**
 * Based on rand7 function.
 */
fun calcRand10(): Int {
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

  return calcRand10()
}

/**
 * Based on rand7 function.
 */
fun calcRand5(): Int {
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
  repeat(10) { println("${it+1} times rand10(): " + calcRand10())}

  repeat(10) { println("${it+1} times rand5(): " + calcRand5())}
}
