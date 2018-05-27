package com.scss.scss.service

import com.scss.scss.domain.course
import com.scss.scss.domain.courseRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class courseServiceImpl : courseService {

    @Autowired
    lateinit var CourseRepo: courseRepo

    //    增
    override fun insertCourse(Course: course): course {
        return CourseRepo.save(Course)
    }

    //    删除
    override fun delCourse(id: Long) {
        CourseRepo.deleteById(id)
    }

    //    改
    override fun updateCourse(Course: course): course {
        return CourseRepo.save(Course)
    }

    //    查
    override fun findCourse(): List<course> {
        return CourseRepo.findAll()
    }
}