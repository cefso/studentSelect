package com.scss.scss.service

import com.scss.scss.domain.course

interface courseService{
//    增
    fun insertCourse(Course:course):course

//    删除
    fun delCourse(id:Long):Unit

//    改
    fun updateCourse(Course: course):course

//    查
    fun findCourse():List<course>
}
