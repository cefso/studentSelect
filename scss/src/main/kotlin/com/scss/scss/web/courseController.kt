package com.scss.scss.web

import com.scss.scss.domain.course
import com.scss.scss.service.courseService
import com.scss.scss.service.professionService
import com.scss.scss.service.spService
import com.scss.scss.service.studentService
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
    lateinit var ProfessionService: professionService
    @Autowired
    lateinit var StudentService:studentService

    @RequestMapping("/student/course", method = arrayOf(RequestMethod.GET))
    fun getAll(map: ModelMap, session: HttpSession): String {
        println("==============================CourseList=============================")
        println(session.getAttribute("login"))
//        映射课程列表
        map.addAttribute("courseList", CourseService.findCourse())
//        从session中获取学号信息
        var sNumber: String = session.getAttribute("login") as String
//        从学生信息中获取专业名
        var pName:String=StudentService.findBysNumber(sNumber).sProfession as String
//        获取专业号
        var pNumber:String=ProfessionService.findBypName(pName).pNumber as String
//        var pNumber: String = SpService.findBysNumber(sNumber).pNumber as String
//        获取推荐课程号
        var rNumber: String = ProfessionService.findBypNumber(pNumber).rNumber as String
//        获取推荐课程信息
        var Course: course = CourseService.findBycNumber(rNumber)
//        映射推荐课程信息
        map.addAttribute("course", Course)
        println("==============================CourseList=============================")
        return "course"
    }
}