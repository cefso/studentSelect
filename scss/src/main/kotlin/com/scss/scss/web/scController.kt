package com.scss.scss.web

import com.scss.scss.domain.sc
import com.scss.scss.domain.student
import com.scss.scss.service.scService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpSession

@Controller
class scController {

    @Autowired
    lateinit var ScService: scService

    @RequestMapping("/student/course/select/{cNumber}", method = arrayOf(RequestMethod.GET))
    fun selectCourse(@PathVariable cNumber: String, session: HttpSession, @ModelAttribute Sc: sc, Student: student): String {
//        将选择的课程储存到Sc中
        Sc.cNumber = cNumber
//        获取Session中存储的学号信息
        var sNumber: String = session.getAttribute("login") as String
//        将学号信息储存到Sc中
        Sc.sNumber = sNumber
//        判断sc表中是否已存在选课信息，如果存在则删除选课信息
        println(Sc)
        println(sNumber)
        var error:String?="xxx"
        try {
            ScService.findBysNumber(sNumber).sNumber
        }catch (e:Exception) {
            error = null
        }
        if (error!=null){
            var id=ScService.findBysNumber(sNumber).id
            ScService.delSc(id!!)
            ScService.insertSc(Sc)
        }else{
            ScService.insertSc(Sc)
        }
        return "redirect:/login"
    }
}