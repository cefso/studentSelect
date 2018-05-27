package com.scss.scss.service

import com.scss.scss.domain.sp

interface spService{
    fun insertSp(Sp:sp):sp

    fun delSp(id:Long):Unit

    fun updateSp(Sp: sp):sp

    fun findSp():List<sp>
}