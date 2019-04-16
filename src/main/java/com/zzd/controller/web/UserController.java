package com.zzd.controller.web;

import com.zzd.entity.User;
import com.zzd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/19 21:27
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "zzd/user/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "zzd/user/register";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        User user = userService.checkLogin(username, password);
        if(user != null) {
            request.getSession().setAttribute("user", user);
            return "redirect:/index";
        }else{
            // request.setAttribute("log_msg", "密码或用户名出错！");
            map.put("message", "用户名或者密码错误");
            return "zzd/user/login";
        }
    }

    /**
     * 注册
     * @param username
     * @param password
     * @param email
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String username, String password, String email,
                         HttpServletRequest request, HttpServletResponse response, ModelMap map){
        List<User> checkUserName = userService.checkUserName(username);
        // 如果当前用户名没被使用
        if(checkUserName == null || checkUserName.isEmpty()) {
            User user = new User();
            user.setUserName(username);
            user.setUserPassword(password);
            user.setEmail(email);
            user.setCoin(0.0);
            user.setVipStatus(0);
            user.setRegisterTime(new Date());
            userService.register(user); // 注册
            request.getSession().setAttribute("user", user);
            return "redirect:/index";
        }else {
            map.put("message", "此用户名已被使用");
            return "zzd/user/register";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        return "redirect:/index";
    }

}
