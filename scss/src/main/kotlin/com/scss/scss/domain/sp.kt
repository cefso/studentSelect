package com.scss.scss.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
//学生专业表
@Entity
data class sp(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        var sNumber: String? = null,
        var pNumber: String? = null

)