package com.scss.scss.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface adminRepo:JpaRepository<admin,Long>{
    fun findByaNumber(@Param("anumber")aNumber:String):admin
}