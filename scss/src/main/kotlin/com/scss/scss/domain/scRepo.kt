package com.scss.scss.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface scRepo:JpaRepository<sc,Long>{
    fun findBysNumber(@Param("snumber")sNumber:String):sc
    fun findBycNumber(@Param("cnumber")cNumber:String):List<sc>
}