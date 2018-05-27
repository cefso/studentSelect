package com.scss.scss.domain

import javax.persistence.Entity

@Entity
data class student(
        val id: Long? = null,
        var s_number: String? = null,
        var s_age: Long? = null,
        var s_name: String? = null,
        var s_sex: String? = null,
        var s_telephone: String? = null,
        var s_profession: String? = null,
        var s_passwd: String? = null
)