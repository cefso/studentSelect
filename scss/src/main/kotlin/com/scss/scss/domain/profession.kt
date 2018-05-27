package com.scss.scss.domain

import javax.persistence.Entity

@Entity
data class profession(
        val id: Long? = null,
        var p_number: String? = null,
        var p_name: String? = null
)