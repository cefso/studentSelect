package com.scss.scss.service

import com.scss.scss.domain.tc
import com.scss.scss.domain.tcRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class tcServiceImpl : tcService {
    @Autowired
    lateinit var TcRepo: tcRepo

    //    增
    override fun insertTc(Tc: tc): tc {
        return TcRepo.save(Tc)
    }

    //    删除
    override fun delTc(id: Long) {
        TcRepo.deleteById(id)
    }

    //    改
    override fun updateTc(Tc: tc): tc {
        return TcRepo.save(Tc)
    }

    //    查
    override fun findTc(): List<tc> {
        return TcRepo.findAll()
    }
}