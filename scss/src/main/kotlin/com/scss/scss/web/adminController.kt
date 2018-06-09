package com.scss.scss.web

import com.scss.scss.domain.*
import com.scss.scss.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpSession

@Controller
class adminController {
    @Autowired
    lateinit var AdminService: adminService
    @Autowired
    lateinit var StudentService: studentService
    @Autowired
    lateinit var ScService: scService
    @Autowired
    lateinit var CourseService: courseService
    @Autowired
    lateinit var TeacherService: teacherService
    @Autowired
    lateinit var TcService: tcService
    @Autowired
    lateinit var ProfessionService: professionService
    @Autowired
    lateinit var SpService:spService

    //    登录视图
    @RequestMapping("/alogin", method = arrayOf(RequestMethod.GET))
    fun AloginForm(map: ModelMap): String {
        var Admin: admin = admin()
        println("test")
        println(Admin)
        map.addAttribute("admin", Admin)
        return "Alogin"
    }

    //    登录
    @RequestMapping("/alogin", method = arrayOf(RequestMethod.POST))
    fun Alogin(@ModelAttribute Admin: admin, map: ModelMap, session: HttpSession): String {
//        获取输入的账号
        val A_number: String = Admin.aNumber!!
        var error:String="succeed"
        try {
            //        查询对应账号在数据库中储存的信息
            val getAdmin: admin = AdminService.findByaNumber(A_number)
        }catch (e:Exception){
//            未查询到对应信息
            error="error"
        }
        if (error=="succeed"){
            //        查询对应账号在数据库中储存的信息
            val getAdmin: admin = AdminService.findByaNumber(A_number)
//        判断密码是否正确
            if (getAdmin.aPasswd == Admin.aPasswd) {
//            添加登录信息到session
                session.setAttribute("Alogin", A_number)
//            println("登录成功")
                map.addAttribute("admin", getAdmin)
                return "Ainfo"
            } else {
//            println("登录失败")
                return "ALerror"
            }
        }else{
            return "ANotFind"
        }
    }

    //    信息视图
    @RequestMapping("admin/info", method = arrayOf(RequestMethod.GET))
    fun Ainfo(@ModelAttribute Admin: admin, map: ModelMap, session: HttpSession): String {
        var Admin: admin
        var Anumber = session.getAttribute("Alogin") as String
        Admin = AdminService.findByaNumber(Anumber)
        map.addAttribute("admin", Admin)
        return "Ainfo"
    }

    //*******************************学生管理********************************
    //    学生列表
    @RequestMapping("/admin/student", method = arrayOf(RequestMethod.GET))
    fun manageStudent(map: ModelMap): String {
        var StudentList = StudentService.findStudent()
        map.addAttribute("studentList", StudentList)
        return "Mstudent"
    }

    //    成绩管理
    @RequestMapping("admin/student/grade/{sNumber}", method = arrayOf(RequestMethod.GET))
    fun MStudentGrade(@PathVariable sNumber: String, map: ModelMap, Sc: sc, session: HttpSession): String {
        println("---------------")
        var Student: student = StudentService.findBysNumber(sNumber)
        var Sc2: sc = ScService.findBysNumber(sNumber)
        session.setAttribute("C_sNumber", sNumber)
        map.addAttribute("student", Student)
        map.addAttribute("Sc2", Sc2)
        map.addAttribute("action", "grade")
        return "Agrade"
    }

    @RequestMapping("admin/student/grade", method = arrayOf(RequestMethod.POST))
    fun insertGrade(@ModelAttribute Sc: sc, session: HttpSession): String {
        var sNumber: String = session.getAttribute("C_sNumber") as String
        Sc.sNumber = sNumber
        Sc.cNumber = ScService.findBysNumber(sNumber).cNumber
        ScService.delSc(ScService.findBysNumber(sNumber).id as Long)
        ScService.insertSc(Sc)
        println("xxxxxxxxxxxxxxxxxxx")
        return "redirect:/admin/student"
    }

    //    选课管理
    @RequestMapping("admin/student/course/{sNumber}", method = arrayOf(RequestMethod.GET))
    fun MStudentCourse(@PathVariable sNumber: String, map: ModelMap, session: HttpSession): String {
        var cNumber = ScService.findBysNumber(sNumber).cNumber
        var Course = CourseService.findBycNumber(cNumber!!)
        session.setAttribute("A_sNumber", sNumber)
        map.addAttribute("course", Course)
        return "AScourse"
    }

    //变更选课
    @RequestMapping("admin/student/course/change", method = arrayOf(RequestMethod.GET))
    fun MSCChange(map: ModelMap, session: HttpSession): String {
        var sNumber = session.getAttribute("A_sNumber") as String
        map.addAttribute("courseList", CourseService.findCourse())
        return "AScourseList"
    }

    @RequestMapping("admin/student/course/select/{cNumber}", method = arrayOf(RequestMethod.GET))
    fun MSCSelect(@PathVariable cNumber: String, @ModelAttribute Sc: sc, session: HttpSession): String {
        //        将选择的课程储存到Sc中
        Sc.cNumber = cNumber
//        获取Session中存储的学号信息
        var sNumber: String = session.getAttribute("A_sNumber") as String
//        将学号信息储存到Sc中
        Sc.sNumber = sNumber
//        判断sc表中是否已存在选课信息，如果存在则删除选课信息
        println(Sc)
        println(sNumber)
        var error: String? = "xxx"
        try {
            ScService.findBysNumber(sNumber).sNumber
        } catch (e: Exception) {
            error = null
        }
        if (error != null) {
            var id = ScService.findBysNumber(sNumber).id
            ScService.delSc(id!!)
            ScService.insertSc(Sc)
        } else {
            ScService.insertSc(Sc)
        }
        return "redirect:/admin/student"
    }

    //    修改个人信息
    @RequestMapping("admin/student/update/{sNumber}", method = arrayOf(RequestMethod.GET))
    fun updateS(@PathVariable sNumber: String, map: ModelMap, session: HttpSession, @ModelAttribute Student: student): String {
        var Student: student
        var sNumber: String = sNumber
        Student = StudentService.findBysNumber(sNumber)
        session.setAttribute("A_sNumber", sNumber)
        map.addAttribute("student", Student)
        map.addAttribute("action", "update")
        var ProfessionList=ProfessionService.findProfession()
        map.addAttribute("professionList",ProfessionList)
        println("supdate")
        return "AStudentUpdate"
    }

    @RequestMapping("admin/student/update", method = arrayOf(RequestMethod.POST))
    fun updateStudent(@ModelAttribute Student2: student, session: HttpSession): String {
        var Student: student
        var sNumber: String = session.getAttribute("A_sNumber") as String
        Student = StudentService.findBysNumber(sNumber)
        var id: Long? = Student.id
        StudentService.delStudent(id!!)
        StudentService.insertStudent(Student2)
        return "redirect:/admin/student"
    }

    //    删除
    @RequestMapping("admin/student/del/{sNumber}", method = arrayOf(RequestMethod.GET))
    fun delStudent(@PathVariable sNumber: String): String {
//        删除学生信息
        var Id = StudentService.findBysNumber(sNumber).id
        StudentService.delStudent(Id!!)
        var error:String="succeed"
        try {
            var Id2=ScService.findBysNumber(sNumber).id
        }catch (e:Exception){
            error="error"
        }
        if (error=="succeed"){
            //        删除学生选课信息
            var Id2=ScService.findBysNumber(sNumber).id
            ScService.delSc(Id2!!)
        }
        var error2:String="succeed"
        try {
            var Id3=SpService.findBysNumber(sNumber).id
        }catch (e:Exception){
            error2="error"
        }
        if (error2=="succeed"){
            //        删除学生专业信息
            var Id3=SpService.findBysNumber(sNumber).id
            SpService.delSp(Id3!!)
        }
        return "redirect:/admin/student"
    }

    //*******************************教师管理********************************
    //    教师列表
    @RequestMapping("/admin/teacher", method = arrayOf(RequestMethod.GET))
    fun manageTeacher(map: ModelMap): String {
        var TeacherList = TeacherService.findTeacher()
        map.addAttribute("teacherList", TeacherList)
        return "Mteacher"
    }

    // 授课管理
    @RequestMapping("/admin/teacher/course/{tNumber}", method = arrayOf(RequestMethod.GET))
    fun MTeacherCourse(@PathVariable tNumber: String, map: ModelMap, session: HttpSession): String {
//        查找教师教授课程号
        var cNumber = TcService.findBytNumber(tNumber).cNumber
        println("cccccccccccccccccc")
        println(cNumber)
        var Course = CourseService.findBycNumber(cNumber!!)
        session.setAttribute("A_tNumber", tNumber)
        map.addAttribute("course", Course)
        return "ATcourse"
    }

    //    变更授课
    @RequestMapping("admin/teacher/course/change", method = arrayOf(RequestMethod.GET))
    fun MTCChange(map: ModelMap, session: HttpSession): String {
        var tNumber = session.getAttribute("A_tNumber") as String
        map.addAttribute("courseList", CourseService.findCourse())
        return "ATcourseList"
    }

    @RequestMapping("admin/teacher/course/select/{cNumber}", method = arrayOf(RequestMethod.GET))
    fun MTCSelect(@PathVariable cNumber: String, @ModelAttribute Tc: tc, session: HttpSession): String {
//    将课程号存入sc
        Tc.cNumber = cNumber
//    将教师号存入sc
        var tNumber = session.getAttribute("A_tNumber") as String
        Tc.tNumber = tNumber
        var error: String? = "xxx"
        try {
            TcService.findBytNumber(tNumber)
        } catch (e: Exception) {
            error = null
        }
        if (error != null) {
            var Id = TcService.findBytNumber(tNumber).id
            TcService.delTc(Id!!)
            TcService.insertTc(Tc)
        } else {
            TcService.insertTc(Tc)
        }
        return "redirect:/admin/teacher"
    }

    //    更新信息
    @RequestMapping("admin/teacher/update/{tNumber}", method = arrayOf(RequestMethod.GET))
    fun updateT(@PathVariable tNumber: String, map: ModelMap, session: HttpSession, @ModelAttribute Teacher: teacher): String {
        var Teacher: teacher
        var tNumber: String = tNumber
        Teacher = TeacherService.findBytNumber(tNumber)
        session.setAttribute("A_tNumber", tNumber)
        map.addAttribute("teacher", Teacher)
        map.addAttribute("action", "update")
        return "ATeacherUpdate"
    }

    @RequestMapping("admin/teacher/update", method = arrayOf(RequestMethod.POST))
    fun updateTeacher(@ModelAttribute Teacher2: teacher, session: HttpSession): String {
        var Teacher: teacher
        var tNumber: String = session.getAttribute("A_tNumber") as String
        Teacher = TeacherService.findBytNumber(tNumber)
        var id: Long? = Teacher.id
        TeacherService.delTeacher(id!!)
        TeacherService.insertTeacher(Teacher2)
        return "redirect:/admin/teacher"
    }

    //删除
    @RequestMapping("admin/teacher/del/{tNumber}", method = arrayOf(RequestMethod.GET))
    fun delTeacher(@PathVariable tNumber: String): String {
        var Id = TeacherService.findBytNumber(tNumber).id
        TeacherService.delTeacher(Id!!)
//       关联删除tc信息
        var error:String="succeed"
        try {
            TcService.findBytNumber(tNumber)
        }catch (e:Exception){
            error="error"
        }
        if(error=="succeed"){
            var Id=TcService.findBytNumber(tNumber).id
            TcService.delTc(Id!!)
        }
        return "redirect:/admin/teacher"
    }

    //*******************************课程管理********************************
//    课程列表
    @RequestMapping("admin/course", method = arrayOf(RequestMethod.GET))
    fun courseList(map: ModelMap): String {
        var CourseList = CourseService.findCourse()
        map.addAttribute("courseList", CourseList)
        return "Mcourse"
    }

    //    课程修改
    @RequestMapping("admin/course/update/{cNumber}", method = arrayOf(RequestMethod.GET))
    fun updateC(@PathVariable cNumber: String, map: ModelMap, session: HttpSession, @ModelAttribute Course: course): String {
        var Course: course
        var cNumber: String = cNumber
        Course = CourseService.findBycNumber(cNumber)
        session.setAttribute("A_cNumber", cNumber)
        map.addAttribute("course", Course)
        map.addAttribute("action", "update")
        println("mmmmmmmmmmmmmm")
        return "ACourseUpdate"
    }


    @RequestMapping("admin/course/update", method = arrayOf(RequestMethod.POST))
    fun updateCourse(@ModelAttribute Course2: course, session: HttpSession): String {
        var Course: course
        var cNumber: String = session.getAttribute("A_cNumber") as String
        Course = CourseService.findBycNumber(cNumber)
        var id: Long? = Course.id
        CourseService.delCourse(id!!)
        CourseService.insertCourse(Course2)
        return "redirect:/admin/course"
    }

    //    增加课程
    @RequestMapping("admin/course/create", method = arrayOf(RequestMethod.POST))
    fun insertCourse(@ModelAttribute Course: course): String {
        CourseService.insertCourse(Course)
        return "redirect:/admin/course"
    }

    @RequestMapping("admin/course/create", method = arrayOf(RequestMethod.GET))
    fun insertCourseForm(map: ModelMap): String {
        var Course = course()
        map.addAttribute("course", Course)
        map.addAttribute("action", "create")
        return "Ccreate"
    }

    //    删除
    @RequestMapping("admin/course/del/{cNumber}", method = arrayOf(RequestMethod.GET))
    fun delCourse(@PathVariable cNumber: String): String {
        var Id: Long? = CourseService.findBycNumber(cNumber).id
        CourseService.delCourse(Id!!)
//        关联删除sc表
        var error:String="succeed"
        try {
//            获取所有选课的学生
            var ScList=ScService.findBycNumber(cNumber)
        }catch (e:Exception){
            error="error"
        }
        if (error=="succeed"){
//            获取所有选课的学生
            var ScList=ScService.findBycNumber(cNumber)
//            循环删除相关信息
            for (Sc in ScList){
                var Id2=Sc.id
                ScService.delSc(Id2!!)
            }
        }
//        关联删除tc表
        var error2:String="succeed"
        try {
//            获取授课信息
            var TcList=TcService.findBycNumber(cNumber)
        }catch (e:Exception){
            //            获取授课信息
            var TcList=TcService.findBycNumber(cNumber)
//            循环删除
            for(Tc in TcList){
                var Id3=Tc.id
                TcService.delTc(Id3!!)
            }
        }
        return "redirect:/admin/course"
    }

    //*******************************专业管理********************************
//    专业列表
    @RequestMapping("admin/profession", method = arrayOf(RequestMethod.GET))
    fun professionList(map: ModelMap): String {
//        var CourseList = CourseService.findCourse()
////        map.addAttribute("courseList", CourseList)
////        return "Mcourse"
        var ProfessionList = ProfessionService.findProfession()
        map.addAttribute("professionList", ProfessionList)
        return "Mprofession"
    }

    //    信息修改
    @RequestMapping("admin/profession/update/{pNumber}", method = arrayOf(RequestMethod.GET))
    fun updateP(@PathVariable pNumber: String, map: ModelMap, session: HttpSession, @ModelAttribute Profession: profession): String {
        var Profession: profession
        var pNumber = pNumber
        Profession = ProfessionService.findBypNumber(pNumber)
        session.setAttribute("A_pNumber", pNumber)
        map.addAttribute("profession", Profession)
        map.addAttribute("action", "update")
        return "AProfessionUpdate"
    }

    @RequestMapping("admin/profession/update", method = arrayOf(RequestMethod.POST))
    fun updateProfession(@ModelAttribute Profession2: profession, session: HttpSession): String {
        var Profession: profession
        var pNumber = session.getAttribute("A_pNumber") as String
        Profession = ProfessionService.findBypNumber(pNumber)
        var id: Long? = Profession.id
        ProfessionService.delProfession(id!!)
        ProfessionService.insertProfession(Profession2)
        return "redirect:/admin/profession"
    }

    //    推荐选课修改(列表显示)
    @RequestMapping("/admin/profession/r/{pNumber}", method = arrayOf(RequestMethod.GET))
    fun MPr(@PathVariable pNumber: String, map: ModelMap, Profession: profession, session: HttpSession): String {
//    添加pNumber到session
        session.setAttribute("A_pNumber", pNumber)
//    映射所有课程信息
        var CourseList = CourseService.findCourse()
        map.addAttribute("courseList", CourseList)
//    获取原推荐课程号
        var Profession1: profession = ProfessionService.findBypNumber(pNumber)
        var rNumber = Profession1.rNumber
//    获取原推荐课程信息
        var Course2: course = CourseService.findBycNumber(rNumber!!)
//    映射原推荐课程信息
        map.addAttribute("course2", Course2)
        return "Aprofession"
    }

    //    修改操作
    @RequestMapping("admin/profession/r/select/{cNumber}", method = arrayOf(RequestMethod.GET))
    fun selectR(@PathVariable cNumber: String, session: HttpSession, Profession: profession): String {
//        获取原专业号
        var pNumber = session.getAttribute("A_pNumber") as String
//        获取原专业名
        var pName = ProfessionService.findBypNumber(pNumber).pName
//        获取原id
        var id = ProfessionService.findBypNumber(pNumber).id
//        删除原信息
        ProfessionService.delProfession(id!!)
//        插入新信息
        Profession.pNumber = pNumber
        Profession.pName = pName
        Profession.rNumber = cNumber
        ProfessionService.insertProfession(Profession)
        return "redirect:/admin/profession/r"
    }

    @RequestMapping("admin/profession/r", method = arrayOf(RequestMethod.GET))
    fun rForm(session: HttpSession, map: ModelMap): String {
        var CourseList = CourseService.findCourse()
        map.addAttribute("courseList", CourseList)
        var pNumber = session.getAttribute("A_pNumber") as String
        var cNumber = ProfessionService.findBypNumber(pNumber).rNumber
        var Course: course = CourseService.findBycNumber(cNumber!!)
        map.addAttribute("course2", Course)
        return "Aprofession"
    }

    //    删除
    @RequestMapping("admin/profession/del/{pNumber}", method = arrayOf(RequestMethod.GET))
    fun delProfession(@PathVariable pNumber: String): String {
//        删除专业信息
        var Id = ProfessionService.findBypNumber(pNumber).id
        ProfessionService.delProfession(Id!!)

        return "redirect:/admin/profession"
    }

    //新增专业
    @RequestMapping("admin/profession/create", method = arrayOf(RequestMethod.POST))
    fun insertProfession(@ModelAttribute Profession: profession): String {
        ProfessionService.insertProfession(Profession)
        return "redirect:/admin/profession"
    }

    @RequestMapping("admin/profession/create", method = arrayOf(RequestMethod.GET))
    fun insertProfessionForm(map: ModelMap): String {
        var Profession = profession()
        map.addAttribute("profession", Profession)
        map.addAttribute("action", "create")
        return "Pcreate"
    }

}