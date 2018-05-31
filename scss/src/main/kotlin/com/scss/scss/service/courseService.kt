package com.scss.scss.service

import com.scss.scss.domain.course
//课程服务
interface courseService {
    //    增
    fun insertCourse(Course: course): course

    //    删除
    fun delCourse(id: Long): Unit

    //    改
    fun updateCourse(Course: course): course

    //    查
    fun findCourse(): List<course>

//    通过课程号查询
    fun findBycNumber(cNumber:String):course
}
