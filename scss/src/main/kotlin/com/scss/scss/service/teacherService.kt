package com.scss.scss.service

import com.scss.scss.domain.teacher
//教师服务
interface teacherService {
    //    增
    fun insertTeacher(Teacher: teacher): teacher

    //    删除
    fun delTeacher(id: Long): Unit

    //    改
    fun updateTeacher(Teacher: teacher): teacher

    //    查
    fun findTeacher(): List<teacher>
}