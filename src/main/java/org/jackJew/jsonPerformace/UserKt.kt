package org.jackJew.jsonPerformace

import com.google.gson.GsonBuilder

data class User(val name: String, val gender: Byte)

val gson = GsonBuilder().disableHtmlEscaping().create()

fun main() {
  val user = User("runjia", 0)
  println(gson.toJson(user))

  val str = """
    {"gender":0}
  """.trimIndent()

  println(gson.fromJson(str, User::class.java))
  // Gson do not comply with non-null type, so should not be used with kotlin !!!
}