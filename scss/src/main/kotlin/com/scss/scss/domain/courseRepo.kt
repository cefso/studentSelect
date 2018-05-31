package com.scss.scss.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import javax.validation.constraints.Past

interface courseRepo:JpaRepository<course,Long>{
//    fun findBypName(@Param("pname")pName:String):profession
    fun findBycNumber(@Param("cnumber")cNumber:String):course
}