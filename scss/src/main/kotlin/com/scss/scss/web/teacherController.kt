package com.scss.scss.web

import com.scss.scss.domain.teacher
import com.scss.scss.service.teacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpSession

@Controller
class teacherController {
    @Autowired
    lateinit var TeacherService: teacherService

    //    登录视图
    @RequestMapping("/tlogin", method = arrayOf(RequestMethod.GET))
    fun TloginForm(map: ModelMap): String {
        var Teacher: teacher = teacher()
        map.addAttribute("teacher", Teacher)
        return "Tlogin"
    }

    @RequestMapping("/tlogin", method = arrayOf(RequestMethod.POST))
    fun Tlogin(@ModelAttribute Teacher: teacher, map: ModelMap, session: HttpSession): String {
//        获取教工号
        var T_number: String = Teacher.tNumber!!
//        查询对应教工号在数据库中储存的信息
        println(Teacher)
        var getteacher: teacher = TeacherService.findBytNumber(T_number)
//        判断密码是否正确
        if (getteacher.tPasswd == Teacher.tPasswd) {
//            返回个人信息
            map.addAttribute("teacher", TeacherService.findBytNumber(T_number))
//            添加登录信息到session
            session.setAttribute("Tlogin", T_number)
            return "Tinfo"
        } else {
            return "redirect:/tlogin"
        }

    }

    //    注册
    @RequestMapping("/teacher/create", method = arrayOf(RequestMethod.POST))
    fun insertTeacher(@ModelAttribute Teacher: teacher): String {
        var error:String="error"
        try {
            TeacherService.findBytNumber(Teacher.tNumber as String)
        }catch (e:Exception){
            //    将教师信息插入教师表
            TeacherService.insertTeacher(Teacher)
            error="succeed"
        }
        if (error=="succeed"){
            return "redirect:/tlogin"
        }else{
            return "TCerror"
        }


    }

    //    进入用户视图
    @RequestMapping("teacher/create", method = arrayOf(RequestMethod.GET))
    fun insertTeacherForm(map: ModelMap): String {
        var Teacher = teacher()
        map.addAttribute("teacher", Teacher)
        map.addAttribute("action", "create")
        return "Tcreate"
    }

    //    修改个人信息
    @RequestMapping("teacher/update", method = arrayOf(RequestMethod.GET))
    fun updateT(@ModelAttribute Teacher: teacher, map: ModelMap, session: HttpSession): String {
        var Teacher: teacher
        var tNumber: String = session.getAttribute("Tlogin") as String
        Teacher = TeacherService.findBytNumber(tNumber)
        map.addAttribute("teacher", Teacher)
        return "Tupdate"
    }

    //    提交个人信息修改
    @RequestMapping("teacher/update", method = arrayOf(RequestMethod.POST))
    fun updateStudent(@ModelAttribute Teacher2: teacher, session: HttpSession): String {
        var Teacher: teacher
        var tNumber: String = session.getAttribute("Tlogin") as String
        Teacher = TeacherService.findBytNumber(tNumber)
        var id: Long? = Teacher.id
        TeacherService.delTeacher(id!!)
        TeacherService.insertTeacher(Teacher2)
        return "redirect:/teacher/info"
    }

    //    信息视图
    @RequestMapping("teacher/info", method = arrayOf(RequestMethod.GET))
    fun Tinfo(@ModelAttribute Teacher: teacher, map: ModelMap, session: HttpSession): String {
        var Teacher: teacher
//        从session中获得登录信息
        var tNumber: String = session.getAttribute("Tlogin") as String
        Teacher = TeacherService.findBytNumber(tNumber)
//        将登陆信息展示到页面上
        map.addAttribute("teacher", Teacher)
        return "Tinfo"
    }

}
