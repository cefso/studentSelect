package com.scss.scss.web

import com.scss.scss.domain.student
import com.scss.scss.service.studentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpSession

@Controller
class studentController {
    @Autowired
    lateinit var StudentService: studentService

    //    注册
    @RequestMapping("/student/create", method = arrayOf(RequestMethod.POST))
    fun insertStudent(@ModelAttribute Student: student): String {
        StudentService.insertStudent(Student)
        println(Student)
        return "redirect:/login"
    }

    //    进入用户视图
    @RequestMapping("/student/create", method = arrayOf(RequestMethod.GET))
    fun insertStudentForm(map: ModelMap): String {
        var Student = student()
        map.addAttribute("student", Student)
        map.addAttribute("action", "create")
        return "Screate"
    }


    //    登录视图
    @RequestMapping("/login", method = arrayOf(RequestMethod.GET))
    fun SloginForm(map: ModelMap): String {
        var Student = student()
        map.addAttribute("student", Student)
        map.addAttribute("action", "111")
        return "Slogin"
    }


//   登录
    @RequestMapping("/login",method = arrayOf(RequestMethod.POST))
    fun Slogin(@ModelAttribute Student: student,map: ModelMap,session: HttpSession):String{
//    创建常量用于储存学号
        val S_number:String
//    将登陆的时候输入的学号储存到S_number
    println("111")
        S_number=Student.sNumber!!
    println(Student.sNumber)
    println("111")
//    通过学号从数据库中查询对应学生信息并且储存到getStudent中
        val getStudent:student = StudentService.findBysNumber(S_number)
//    将查询到的密码与输入的密码对比
        if (getStudent.sPasswd == Student.sPasswd){
            map.addAttribute("student",StudentService.findBysNumber(S_number))
//            在session中储存登录信息
            session.setAttribute("login",S_number)
//            登录成功返回信息页面
            return "Sinfo"
        }else{
//            登录失败返回登录页面
            return "redirect:/login"
        }
//    return "redirect:/Slogin"
}
}