package com.scss.scss.web

import com.scss.scss.domain.course
import com.scss.scss.domain.tc
import com.scss.scss.service.courseService
import com.scss.scss.service.tcService
import com.scss.scss.service.teacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpSession

@Controller
class tcController{
    @Autowired
lateinit var TcService:tcService
    @Autowired
    lateinit var CourseService:courseService

    @RequestMapping("/teacher/course",method = arrayOf(RequestMethod.GET))
fun findTC(map:ModelMap,session:HttpSession):String{
//        从session中获取教师号
        var T_number:String=session.getAttribute("Tlogin") as String
//        在tc表中通过教师号查询对应信息
        var  Tc:tc=TcService.findBytNumber(T_number)
//        查询教师所授课程课程信息
        var Course:course=CourseService.findBycNumber(Tc.cNumber!!)
//        map.addAttribute("tc",Tc)
//        返回课程信息到页面
        map.addAttribute("course",Course)
        return "Tcourse"
    }
}