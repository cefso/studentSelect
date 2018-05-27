package com.scss.scss.service

import com.scss.scss.domain.teacher

interface teacherService{
    fun insertTeacher(Teacher:teacher):teacher

    fun delTeacher(id:Long):Unit

    fun updateTeacher(Teacher:teacher):teacher

    fun findTeacher():List<teacher>
}