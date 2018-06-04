package com.scss.scss.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
//教师表
@Entity
data class teacher(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        var tNumber: String? = null,
        var tName: String? = null,
        var tSex: String? = null,
        var tTelephone: String? = null,
        var tEmail: String? = null,
        var tPasswd: String? = null
)