package com.scss.scss.service

import com.scss.scss.domain.tc

interface tcService {
    //    增
    fun insertTc(Tc: tc): tc

    //    删除
    fun delTc(id: Long): Unit

    //    改
    fun updateTc(Tc: tc): tc

    //    查
    fun findTc(): List<tc>
}