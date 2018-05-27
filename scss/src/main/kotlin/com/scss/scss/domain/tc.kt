package com.scss.scss.domain

import javax.persistence.Entity

@Entity
data class tc(
        val id: Long? = null,
        var t_number: String? = null,
        var c_number: String? = null
)