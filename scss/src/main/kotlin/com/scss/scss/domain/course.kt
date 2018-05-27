package com.scss.scss.domain

import javax.persistence.Entity

@Entity
data class course(
        val id: Long? = null,
        var c_number: String? = null,
        var c_name: String? = null,
        var c_time: String? = null,
        var c_score: String? = null
)