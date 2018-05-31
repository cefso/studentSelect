package com.scss.scss.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface professionRepo:JpaRepository<profession,Long>{
//    通过专业名查询
    fun findBypName(@Param("pname")pName:String):profession
//    fun findBypNumber(@Param("pnumber")pNumber:String):profession
    fun findBypNumber(@Param("pnumber")pNumber:String):profession
}