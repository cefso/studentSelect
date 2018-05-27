package com.scss.scss.service

import com.scss.scss.domain.tc

interface tcService{
    fun insertTc(Tc:tc):tc

    fun delTc(id:Long):Unit

    fun updateTc(Tc: tc):tc

    fun findTc():List<tc>
}