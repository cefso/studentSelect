package com.scss.scss.web

import com.scss.scss.domain.course
import com.scss.scss.domain.profession
import com.scss.scss.service.courseService
import com.scss.scss.service.professionService
import com.scss.scss.service.spService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpSession

@Controller
class courseController {

    @Autowired
    lateinit var CourseService: courseService
    @Autowired
    lateinit var SpService: spService
    @Autowired
    lateinit var ProfessionService:professionService

    @RequestMapping("/student/course", method = arrayOf(RequestMethod.GET))
    fun getAll(map: ModelMap,session: HttpSession): String {
        map.addAttribute("courseList", CourseService.findCourse())
        var sNumber:String=session.getAttribute("login")as String
        var pNumber:String=SpService.findBysNumber(sNumber).pNumber as String
        var rNumber:String = ProfessionService.findBypNumber(pNumber).rNumber as String
        var Course:course=CourseService.findBycNumber(rNumber)
        map.addAttribute("course",Course)
        return "course"
    }
}