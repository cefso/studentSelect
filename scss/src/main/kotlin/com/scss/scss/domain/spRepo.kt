package com.scss.scss.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface spRepo:JpaRepository<sp,Long>{
    fun findBysNumber(@Param("snumber")sNumber: String):sp
}