package com.scss.scss.service

import com.scss.scss.domain.profession
//专业服务
interface professionService {
    //    增
    fun insertProfession(Profession: profession): profession

    //    删除
    fun delProfession(id: Long): Unit

    //    改
    fun updateProfession(Profession: profession): profession

    //    查
    fun findProfession(): List<profession>
}