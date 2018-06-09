package com.scss.scss.service

import com.scss.scss.domain.tc

//授课服务
interface tcService {
    //    增
    fun insertTc(Tc: tc): tc

    //    删除
    fun delTc(id: Long): Unit

    //    改
    fun updateTc(Tc: tc): tc

    //    查
    fun findTc(): List<tc>

    //    通过教师号查找
    fun findBytNumber(T_number: String): tc

    //    根据课程号查询
    fun findBycNumber(C_number: String): List<tc>
}