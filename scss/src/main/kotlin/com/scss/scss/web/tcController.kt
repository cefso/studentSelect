package com.scss.scss.web

import com.scss.scss.domain.course
import com.scss.scss.domain.sc
import com.scss.scss.domain.student
import com.scss.scss.domain.tc
import com.scss.scss.service.courseService
import com.scss.scss.service.scService
import com.scss.scss.service.studentService
import com.scss.scss.service.tcService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpSession

@Controller
class tcController {
    @Autowired
    lateinit var TcService: tcService
    @Autowired
    lateinit var CourseService: courseService
    @Autowired
    lateinit var ScService: scService
    @Autowired
    lateinit var StudentService: studentService

    @RequestMapping("/teacher/course", method = arrayOf(RequestMethod.GET))
    fun findTC(map: ModelMap, session: HttpSession): String {
//        从session中获取教师号
        var T_number: String = session.getAttribute("Tlogin") as String
//        定义错误标记
        var error: String = "succeed"
        try {
//        在tc表中通过教师号查询对应信息
            var Tc: tc = TcService.findBytNumber(T_number)
        } catch (e: Exception) {
//            没有安排选课
            error = "error"
        }
        if (error == "succeed") {
////        在tc表中通过教师号查询对应信息
            var Tc: tc = TcService.findBytNumber(T_number)
//        查询教师所授课程课程信息
            var Course: course = CourseService.findBycNumber(Tc.cNumber!!)
//        map.addAttribute("tc",Tc)
//        返回课程信息到页面
            map.addAttribute("course", Course)
            return "Tcourse"
        } else {
            return "TCourseError"
        }
    }

    @RequestMapping("teacher/find", method = arrayOf(RequestMethod.GET))
    fun getAll(map: ModelMap, session: HttpSession): String {
//        获取教工号
        var tNumber: String = session.getAttribute("Tlogin") as String
//        获取课程号
        var cNumber: String = TcService.findBytNumber(tNumber).cNumber as String
//       查找对应学生列表
        var ScList: List<sc>
        ScList = ScService.findBycNumber(cNumber)
        var StudentList = mutableListOf<student>()
        ScList.forEach {
            val sNumber: String = it.sNumber!!
            val Student: student = StudentService.findBysNumber(sNumber)
            StudentList.add(Student)
        }
        map.addAttribute("studentList", StudentList)
        map.addAttribute("action", "find")
        return "Fstudent"
    }

    //    成绩录入
    @RequestMapping("teacher/grade/{sNumber}", method = arrayOf(RequestMethod.GET))
    fun gradeForm(@PathVariable sNumber: String, map: ModelMap, Sc: sc, session: HttpSession): String {
        println(sNumber)
        var Student: student = StudentService.findBysNumber(sNumber)
        var Sc2: sc = ScService.findBysNumber(sNumber)
        session.setAttribute("C_sNumber", sNumber)
        map.addAttribute("student", Student)
        map.addAttribute("Sc2", Sc2)
        map.addAttribute("action", "grade")
        return "Grade"
    }

    @RequestMapping("teacher/grade", method = arrayOf(RequestMethod.POST))
    fun insertGrade(@ModelAttribute Sc: sc, session: HttpSession): String {
        var sNumber: String = session.getAttribute("C_sNumber") as String
        Sc.sNumber = sNumber
        Sc.cNumber = ScService.findBysNumber(sNumber).cNumber
        ScService.delSc(ScService.findBysNumber(sNumber).id as Long)
        ScService.insertSc(Sc)
        return "redirect:/teacher/find"
    }


}






