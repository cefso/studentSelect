package com.scss.scss.service

import com.scss.scss.domain.sp
//学生专业服务
interface spService {
    //    增
    fun insertSp(Sp: sp): sp

    //    删除
    fun delSp(id: Long): Unit

    //    改
    fun updateSp(Sp: sp): sp

    //    查
    fun findSp(): List<sp>
}