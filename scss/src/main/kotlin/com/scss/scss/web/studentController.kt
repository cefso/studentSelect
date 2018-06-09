package com.scss.scss.web

import com.scss.scss.domain.course
import com.scss.scss.domain.profession
import com.scss.scss.domain.sp
import com.scss.scss.domain.student
import com.scss.scss.service.*
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
    lateinit var ProfessionService: professionService
    @Autowired
    lateinit var SpService: spService
    @Autowired
    lateinit var ScService: scService
    @Autowired
    lateinit var CourseService: courseService

    //    注册
    @RequestMapping("/student/create", method = arrayOf(RequestMethod.POST))
    fun insertStudent(@ModelAttribute Student: student, Sp: sp): String {
        println(Student)
        var error:String="error"
        try {
            StudentService.findBysNumber(Student.sNumber as String)
        }catch (e:Exception){
            //         将学生信息插入学生表
            println(Student)
            StudentService.insertStudent(Student)
//            获取专业名
            var pName:String=Student.sProfession as String
//            获取专业号
            var pNumber=ProfessionService.findBypName(pName).pNumber
////        获取专业号
//            var pNumber: String = Student.sProfession as String
//            println(pNumber)
//        将学号和专业对应信息写入Sp对象
            Sp.pNumber = pNumber
            println(Sp.pNumber)
            Sp.sNumber = Student.sNumber
            println(Sp.sNumber)
//        讲对应数据写入学生专业表
            SpService.insertSp(Sp)
            error="succeed"
        }
        println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
        println(error)
        println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
        if (error=="succeed"){
            return "redirect:/login"
        }else{
            return "SCerror"
        }
    }

    //  注册视图
    @RequestMapping("/student/create", method = arrayOf(RequestMethod.GET))
    fun insertStudentForm(map: ModelMap): String {
        println("get")
        var Student = student()
        map.addAttribute("student", Student)
        var ProfessionList=ProfessionService.findProfession()
        map.addAttribute("professionList",ProfessionList)
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
//        创建Lerror储存登录错误
        var Lerror:String="succeed"
        try {
//            尝试查询登录信息
            StudentService.findBysNumber(S_number)
        }catch (e:Exception){
//            报错证明账号不存在
            Lerror="error"
        }
        if (Lerror=="error"){
//            账号不存在返回错误页面
            return "LNerror"
        }else{
//            正常登录
            //    通过学号从数据库中查询对应学生信息并且储存到getStudent中
            val getStudent: student = StudentService.findBysNumber(S_number)
//    将查询到的密码与输入的密码对比
            if (getStudent.sPasswd == Student.sPasswd) {
//            返回个人信息
                map.addAttribute("student", StudentService.findBysNumber(S_number))
//            在session中储存登录信息
//            查询选课信息
                var error: String? = "xxx"
//            error = null 代表没有选课信息
                try {
//                    查询选课信息
                    ScService.findBysNumber(S_number)
                } catch (e: Exception) {
//                    报错证明没有选课
                    error = null
                }
//            如果有选课信息，映射选课信息
                if (error != null) {
                    var cNumber: String = ScService.findBysNumber(S_number).cNumber!!
                    var Course: course = CourseService.findBycNumber(cNumber)
                    map.addAttribute("course", Course)
                } else {
//                    没有选课映射，没有选课信息
                    var Course = course()
                    Course.cName = "没有选课"
                    Course.cNumber = "没有选课"
                    Course.cTime = "没有选课"
                    Course.cScore = "没有选课"
                    println(Course)
                    map.addAttribute("course", Course)
                }
                session.setAttribute("login", S_number)
//            登录成功返回信息页面
                return "Sinfo"
            } else {
//            登录失败返回登录页面
                return "LPerror"
            }
        }
    }

    //    修改个人信息
    @RequestMapping("student/update", method = arrayOf(RequestMethod.GET))
    fun updateS(@ModelAttribute Student: student, map: ModelMap, session: HttpSession): String {
//        映射学生信息
        var Student: student
        var sNumber: String = session.getAttribute("login") as String
        Student = StudentService.findBysNumber(sNumber)
        map.addAttribute("student", Student)
//        映射专业列表
        var ProfessionList=ProfessionService.findProfession()
        map.addAttribute("professionList",ProfessionList)
        println(ProfessionList)
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

    //    信息视图
    @RequestMapping("student/info", method = arrayOf(RequestMethod.GET))
    fun Sinfo(@ModelAttribute Student: student, map: ModelMap, session: HttpSession): String {
        var Student: student
//    从session中获得登录信息
        var sNumber: String = session.getAttribute("login") as String
        Student = StudentService.findBysNumber(sNumber)
//       映射学生信息
        map.addAttribute("student", Student)
//        映射专业信息
//        var professionList=ProfessionService.findProfession()
//        map.addAttribute("prefessionList",professionList)
//        选课信息判断
        var error: String? = "xxx"
        try {
//                    查询选课信息
            ScService.findBysNumber(sNumber)
        } catch (e: Exception) {
//                    报错证明没有选课
            error = null
        }
//        如果有选课信息，映射选课信息
        if (error != null) {
            var cNumber: String = ScService.findBysNumber(sNumber).cNumber!!
            var Course: course = CourseService.findBycNumber(cNumber)
            map.addAttribute("course", Course)
        } else {
//          没有选课映射，没有选课信息
            var Course = course()
            Course.cName = "没有选课"
            Course.cNumber = "没有选课"
            Course.cTime = "没有选课"
            Course.cScore = "没有选课"
            println(Course)
            map.addAttribute("course", Course)
        }
        return "Sinfo"
    }
}