package com.scss.scss.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class sp(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        var sNumber: String? = null,
        var pNumber: String? = null,
        var rNumber: String? = null

)