package com.scss.scss.service

import com.scss.scss.domain.student
import com.scss.scss.domain.studentRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class studentServiceImpl : studentService {
    @Autowired
    lateinit var StudentRepo: studentRepo

    //    增
    override fun insertStudent(Student: student): student {
        return StudentRepo.save(Student)
    }

    //    删除
    override fun delStudent(id: Long) {
        StudentRepo.deleteById(id)
    }

    //    改
    override fun updateStudent(Student: student): student {
        return StudentRepo.save(Student)
    }

    //    查
    override fun findStudent(): List<student> {
        return StudentRepo.findAll()
    }


    override fun findBysNumber(S_number: String): student {
        return StudentRepo.findBysNumber(S_number)
    }
}