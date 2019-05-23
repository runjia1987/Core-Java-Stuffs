package org.jackJew.interview

data class School(var fullName: String = "",
                  var address: String = "",
                  var teachers: MutableList<Teacher> = mutableListOf())

data class Teacher(var name: String = "",
                   var professon: String = "English")

fun school(block: School.() -> Unit) = School().apply(block)

fun School.teacher(block: Teacher.() -> Unit) = teachers.add(Teacher().apply(block))

typealias t_block = Teacher.() -> Unit

fun School.teachers(vararg blocks: t_block) {
  teachers.addAll(blocks.map { Teacher().apply(it) })
}

fun main() {
  school {
    fullName = "ABC School"
    address = "Shanghai Changning district"
    teacher {
      name = "Zhu Jack"
      professon = "Chinese"
    }
    teacher {
      name = "Zhu Jack"
      professon = "Chinese"
    }
  }

  school {
    fullName = "DEF School"
    address = "Shanghai Jiading district"
    teachers({
      name = "Zhu Jack"
      professon = "Chinese"
    }, {
          name = "Tong Mary"
          professon = "English"
    })
  }.let { println(it) }
}

