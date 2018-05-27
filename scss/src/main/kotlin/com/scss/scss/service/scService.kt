package com.scss.scss.service

import com.scss.scss.domain.sc

interface scService{
    fun insertSc(Sc:sc):sc

    fun delSc(id:Long):Unit

    fun updateSc(Sc: sc):sc

    fun findSc():List<sc>
}