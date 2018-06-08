package com.scss.scss.service

import com.scss.scss.domain.sc
import com.scss.scss.domain.scRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class scServiceImpl : scService {
    @Autowired
    lateinit var ScRepo: scRepo

    //    增
    override fun insertSc(Sc: sc): sc {
        return ScRepo.save(Sc)
    }

    //    删除
    override fun delSc(id: Long) {
        ScRepo.deleteById(id)
    }

    //    改
    override fun updateSc(Sc: sc): sc {
        return ScRepo.save(Sc)
    }

    //    查
    override fun findSc(): List<sc> {
        return ScRepo.findAll()
    }

    //    根据学号查找
    override fun findBysNumber(S_number: String): sc {
        return ScRepo.findBysNumber(S_number)
    }

    override fun findBycNumber(C_number: String): List<sc> {
        return ScRepo.findBycNumber(C_number)
    }
}