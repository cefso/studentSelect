package com.scss.scss.domain

import javax.persistence.Entity

@Entity
data class admin(
        val id: Long? = null,
        var a_number: String? = null,
        var a_name: String? = null,
        var a_passwd: String? = null
)