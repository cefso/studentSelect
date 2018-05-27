package com.scss.scss.service

import com.scss.scss.domain.profession

interface professionService{
    fun insertProfession(Profession:profession):profession

    fun delProfession(id:Long):Unit

    fun updateProfession(Profession: profession):profession

    fun findProfession():List<profession>
}