package com.scss.scss.web

import com.scss.scss.service.courseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class courseController {

    @Autowired
    lateinit var CourseService: courseService

    @RequestMapping("/student/course", method = arrayOf(RequestMethod.GET))
    fun getAll(map: ModelMap): String {
        map.addAttribute("courseList", CourseService.findCourse())
        return "course"
    }
}