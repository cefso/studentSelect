package com.scss.scss.service

import com.scss.scss.domain.student

interface studentService{
    fun insertStudent(Student:student):student

    fun delStudent(id:Long):Unit

    fun updateStudent(Student: student):student

    fun findStudent():List<student>
}