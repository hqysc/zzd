package com.zzd.controller.admin;

import com.zzd.entity.AdminUser;
import com.zzd.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/1/14 20:37
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 访问首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "/admin/index";
    }

    /**
     * 访问登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String toLogin() {
        return "admin/login";
    }

    /**
     * 登录请求
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        AdminUser adminUser = adminUserService.checkLogin(username, password);
        if(adminUser != null) {
            request.getSession().setAttribute("adminUser", adminUser);
            return "redirect:/admin/index";
        }else{
            map.put("message", "用户名或者密码错误");
            return "admin/login";
        }
    }

}
