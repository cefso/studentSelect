package com.scss.scss.service

import com.scss.scss.domain.sc
//选课服务
interface scService {
    //    增
    fun insertSc(Sc: sc): sc

    //    删除
    fun delSc(id: Long): Unit

    //    改
    fun updateSc(Sc: sc): sc

    //    查
    fun findSc(): List<sc>

//    根据学号查询
    fun findBysNumber(S_number:String):sc

    fun findBycNumber(C_number:String):List<sc>
}