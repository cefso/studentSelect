package com.scss.scss.domain

import javax.persistence.Entity

@Entity
data class sp(
        val id: Long? = null,
        var s_number: String? = null,
        var p_number: String? = null,
        var r_number: String? = null

)