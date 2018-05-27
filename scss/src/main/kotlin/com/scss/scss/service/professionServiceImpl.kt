package com.scss.scss.service

import com.scss.scss.domain.profession
import com.scss.scss.domain.professionRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class professionServiceImpl : professionService {

    @Autowired
    lateinit var ProfessionRepo: professionRepo

    //    增
    override fun insertProfession(Profession: profession): profession {
        return ProfessionRepo.save(Profession)
    }

    //    删除
    override fun delProfession(id: Long) {
        ProfessionRepo.deleteById(id)
    }

    //    改
    override fun updateProfession(Profession: profession): profession {
        return ProfessionRepo.save(Profession)
    }

    //    查
    override fun findProfession(): List<profession> {
        return ProfessionRepo.findAll()
    }
}