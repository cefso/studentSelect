package com.scss.scss.domain

import javax.persistence.Entity

@Entity
data class sc(
        val id: Long? = null,
        var s_number: String? = null,
        var c_number: String? = null,
        var c_grade: String? = null
)