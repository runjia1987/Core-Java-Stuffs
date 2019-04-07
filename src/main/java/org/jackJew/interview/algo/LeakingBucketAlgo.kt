package org.jackJew.interview.algo

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * 漏桶随机算法
 */
const val SIZE = 5
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var count = 0
    val array = IntArray(SIZE)
    while(true) {
        val number = reader.readLine().toInt()
        if (count < SIZE)
            array[count++] = number
        else {
            Random().nextInt(++count).also {
                if (it < SIZE) array[it] = number
            }
            println(array.joinToString("," ))
        }
    }

}