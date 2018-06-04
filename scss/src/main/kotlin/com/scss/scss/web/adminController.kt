package com.scss.scss.web

import com.scss.scss.domain.admin
import com.scss.scss.service.adminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpSession

@Controller
class adminController {
    @Autowired
    lateinit var AdminService: adminService

    //    登录视图
    @RequestMapping("/alogin", method = arrayOf(RequestMethod.GET))
    fun AloginForm(map: ModelMap): String {
        var Admin: admin = admin()
        map.addAttribute("admin", Admin)
        return "Alogin"
    }

    //    登录
    @RequestMapping("/alogin", method = arrayOf(RequestMethod.POST))
    fun Alogin(@ModelAttribute Admin: admin, map: ModelMap, session: HttpSession): String {
//        获取输入的学号
        val A_number: String = Admin.aNumber!!
//        查询对应学号在数据库中储存的信息
        val getAdmin: admin = AdminService.findByaNumber(A_number)
//        判断密码是否正确
        if (getAdmin.aPasswd == Admin.aPasswd) {
//            添加登录信息到session
            session.setAttribute("Alogin", A_number)
//            println("登录成功")
            return "管理员控制界面"
        } else {
//            println("登录失败")
            return "redirect:/alogin"
        }
    }

}