package com.scss.scss.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface tcRepo:JpaRepository<tc,Long>{
    fun findBytNumber(@Param("tnumber")tNumber:String):tc
}