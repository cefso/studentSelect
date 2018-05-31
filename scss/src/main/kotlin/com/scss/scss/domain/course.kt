package com.scss.scss.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
//课程表
@Entity
data class course(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        var cNumber: String? = null,
        var cName: String? = null,
        var cTime: String? = null,
        var cScore: String? = null
)