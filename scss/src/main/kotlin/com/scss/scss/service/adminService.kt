package com.scss.scss.service

import com.scss.scss.domain.admin

interface adminService {
    //    增
    fun insertAdmin(Admin: admin): admin

    //    删除
    fun delAdmin(id: Long): Unit

    //    改
    fun updateAdmin(Admin: admin): admin

    //    查
    fun findAdmin(): List<admin>
}
