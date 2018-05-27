package com.scss.scss.service

import com.scss.scss.domain.admin

interface adminService{
    fun insertAdmin(Admin:admin):admin

    fun delAdmin(id:Long):Unit

    fun updateAdmin(Admin: admin):admin

    fun findAdmin():List<admin>
}