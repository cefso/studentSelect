package com.scss.scss.service

import com.scss.scss.domain.teacher
import com.scss.scss.domain.teacherRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class teacheServiceImpl : teacherService {
    @Autowired
    lateinit var TeacherRepo: teacherRepo

    //    增
    override fun insertTeacher(Teacher: teacher): teacher {
        return TeacherRepo.save(Teacher)
    }

    //    删除
    override fun delTeacher(id: Long) {
        TeacherRepo.deleteById(id)
    }

    //    改
    override fun updateTeacher(Teacher: teacher): teacher {
        return TeacherRepo.save(Teacher)
    }

    //    查
    override fun findTeacher(): List<teacher> {
        return TeacherRepo.findAll()
    }

    override fun findBytNumber(T_number: String): teacher {
        return TeacherRepo.findBytNumber(T_number)
    }
}