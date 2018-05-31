package com.scss.scss.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//管理员表
@Entity
data class admin(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        var aNumber: String? = null,
        var aName: String? = null,
        var aPasswd: String? = null
)