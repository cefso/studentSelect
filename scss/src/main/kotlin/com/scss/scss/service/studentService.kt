package com.scss.scss.service

import com.scss.scss.domain.student
//学生服务
interface studentService {
    //    增
    fun insertStudent(Student: student): student

    //    删除
    fun delStudent(id: Long): Unit

    //    改
    fun updateStudent(Student: student): student

    //    查
    fun findStudent(): List<student>


    //    学号查找
    fun findBysNumber(S_number:String):student
}