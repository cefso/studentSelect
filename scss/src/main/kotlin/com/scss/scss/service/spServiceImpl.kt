package com.scss.scss.service

import com.scss.scss.domain.sp
import com.scss.scss.domain.spRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class spServiceImpl : spService {
    @Autowired
    lateinit var SpRepo: spRepo

    //    增
    override fun insertSp(Sp: sp): sp {
        return SpRepo.save(Sp)
    }

    //    删除
    override fun delSp(id: Long) {
        SpRepo.deleteById(id)
    }

    //    改
    override fun updateSp(Sp: sp): sp {
        return SpRepo.save(Sp)
    }

    //    查
    override fun findSp(): List<sp> {
        return SpRepo.findAll()
    }
}