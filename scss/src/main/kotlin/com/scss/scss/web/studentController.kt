package com.scss.scss.web

import com.scss.scss.domain.profession
import com.scss.scss.domain.sp
import com.scss.scss.domain.student
import com.scss.scss.service.professionService
import com.scss.scss.service.spService
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
    @Autowired
    lateinit var ProfessionService:professionService
    @Autowired
    lateinit var SpService: spService

    //    注册
    @RequestMapping("/student/create", method = arrayOf(RequestMethod.POST))
    fun insertStudent(@ModelAttribute Student: student,Sp:sp): String {
//        将学生信息插入学生表
        StudentService.insertStudent(Student)
//        获取专业名
        var pName:String=Student.sProfession  as String
        println(pName)
//        查询专业号
        var pNumber:String=ProfessionService.findBypName(pName).pNumber as String
        println(pNumber)
//        将学号和专业对应信息写入Sp对象
        Sp.pNumber=pNumber
        println(Sp.pNumber)
        Sp.sNumber=Student.sNumber
        println(Sp.sNumber)
//        讲对应数据写入学生专业表
        SpService.insertSp(Sp)
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
//        map.addAttribute("action", "111")
        return "Slogin"
    }


    //   登录
    @RequestMapping("/login", method = arrayOf(RequestMethod.POST))
    fun Slogin(@ModelAttribute Student: student, map: ModelMap, session: HttpSession): String {
//    创建常量用于储存学号
        val S_number: String
//    将登陆的时候输入的学号储存到S_number
        S_number = Student.sNumber!!
//    通过学号从数据库中查询对应学生信息并且储存到getStudent中
        val getStudent: student = StudentService.findBysNumber(S_number)
//    将查询到的密码与输入的密码对比
        if (getStudent.sPasswd == Student.sPasswd) {
//            返回个人信息
            map.addAttribute("student", StudentService.findBysNumber(S_number))
//            在session中储存登录信息
            session.setAttribute("login", S_number)
//            登录成功返回信息页面
            return "Sinfo"
        } else {
//            登录失败返回登录页面
            return "redirect:/login"
        }
    }

    //    修改个人信息
    @RequestMapping("student/update", method = arrayOf(RequestMethod.GET))
    fun updateS(@ModelAttribute Student: student, map: ModelMap, session: HttpSession): String {
        var Student: student
        var sNumber: String = session.getAttribute("login") as String
        Student = StudentService.findBysNumber(sNumber)
        map.addAttribute("student", Student)
        return "Supdate"
    }

    //    提交个人信息修改
    @RequestMapping("/student/update", method = arrayOf(RequestMethod.POST))
    fun updateStudent(@ModelAttribute Student2: student, session: HttpSession): String {
        var Student: student
        var sNumber: String = session.getAttribute("login") as String
        Student = StudentService.findBysNumber(sNumber)
        var id: Long? = Student.id
        StudentService.delStudent(id!!)
        StudentService.insertStudent(Student2)
        return "redirect:/student/info"
    }
}