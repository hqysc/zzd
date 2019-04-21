package com.zzd.controller.web;

import com.zzd.common.JsonResult;
import com.zzd.entity.User;
import com.zzd.service.UserService;
import com.zzd.util.EmailUtils;
import com.zzd.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


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
        if(user != null) { // 用户不能为空
            if(user.getUserStatus() == 1) { // 此用户已激活
                request.getSession().setAttribute("user", user);
                return "redirect:/index";
            }else {
                map.put("message", "账号未激活");
                return "zzd/user/login";
            }
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
     * @param userEmail
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String username, String password, String userEmail,
                         HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        List<User> checkUserName = userService.checkUserName(username);
        List<User> checkUserEmail = userService.checkUserEmail(userEmail);
        if(checkUserName == null || checkUserName.isEmpty()) {  // 如果当前用户名没被使用
            if(checkUserEmail == null || checkUserEmail.isEmpty()) {    // 如果当前邮箱没被使用
                User user = new User();
                user.setUserName(username);
                user.setUserPassword(password);
                user.setUserEmail(userEmail);
                user.setCoin(0.0);
                user.setVipStatus(0);
                user.setUserStatus(0);
                String code = UUIDUtils.generateUUID(); // 生成随机码
                user.setCode(code);
                user.setRegisterTime(new Date());
                if(username != null && password != null && userEmail != null) { // 输入数据不为空
                    userService.register(user); // 注册
                    String url = "http://localhost/user/checkCode?userName=" + username + "&userEmail=" + userEmail + "&code=" + code;
                    String content = "<html>\n" +
                                        "<body>\n" +
                                            "<h2>欢迎注册杂智多</h2>\n" +
                                            "<p>您只需点击下面的链接即可进行用户注册，以下链接有效期为3天。过期可以重新请求发送一封新的邮件验证：</p>\n" +
                                            "<a href='"+ url +"'>" + url + "</a>\n" +
                                            "<p>(如果上面不是链接形式，请将该地址手工粘贴到浏览器地址栏再访问)</p>\n" +
                                        "</body>\n" +
                                    "</html>";
                    EmailUtils.sendHtmlEmail(mailSender, fromEmail, userEmail, "杂智多账户激活邮件", content);    // 邮件发送
                }
                map.put("message", "注册成功，请在邮件中激活您的账户");
                return "zzd/user/login";
            }else {
                map.put("message", "此邮箱已被使用");
                return "zzd/user/register";
            }
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

    @GetMapping(value = "/checkLogin")
    @ResponseBody
    public JsonResult checkLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            return JsonResult.success();
        }else {
            return JsonResult.failure("请先登录");
        }
    }

    /**
     * 激活 验证激活码和邮箱
     * @return
     */
    @RequestMapping("/checkCode")
    public String checkCode(@RequestParam String userName,
                            @RequestParam String userEmail,
                            @RequestParam String code, ModelMap map) {
        userService.checkCode(userName, userEmail, code);
        map.put("message", "您的账号激活成功，请登录");
        return "zzd/user/login";
    }
}
