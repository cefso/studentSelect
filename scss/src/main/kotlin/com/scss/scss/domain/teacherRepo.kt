package com.scss.scss.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface teacherRepo:JpaRepository<teacher,Long>{
    fun findBytNumber(@Param("tnumber")tNumber:String):teacher
}