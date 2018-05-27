package com.scss.scss.service

import com.scss.scss.domain.admin
import com.scss.scss.domain.adminRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class adminServiceImpl : adminService {

    @Autowired
    lateinit var AdminReop: adminRepo

    //    增
    override fun insertAdmin(Admin: admin): admin {
        return AdminReop.save(Admin)
    }

    //    删除
    override fun delAdmin(id: Long) {
        AdminReop.deleteById(id)
    }

    //    改
    override fun updateAdmin(Admin: admin): admin {
        return AdminReop.save(Admin)
    }

    //    查
    override fun findAdmin(): List<admin> {
        return AdminReop.findAll()
    }
}