package com.scss.scss.domain

import javax.persistence.Entity

@Entity
data class teacher(
        val id: Long? = null,
        var t_number: String? = null,
        var t_name: String? = null,
        var t_sex: String? = null,
        var t_telephone: String? = null,
        var t_email: String? = null,
        var t_passwd: String? = null
)