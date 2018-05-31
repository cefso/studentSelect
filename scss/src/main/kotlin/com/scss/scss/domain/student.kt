package com.scss.scss.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
//学生表
@Entity
data class student(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        var sAge: Long? = null,
        var sNumber: String? = null,
        var sName: String? = null,
        var sSex: String? = null,
        var sTelephone: String? = null,
        var sProfession: String? = null,
        var sPasswd: String? = null
)