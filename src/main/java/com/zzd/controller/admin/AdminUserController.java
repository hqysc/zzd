package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.User;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.AdminUserService;
import com.zzd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/1/14 20:30
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping({"", "/index"})
    public String list() {
        return "admin/user/list";
    }

    /**
     * 充值页面
     * @return
     */
    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public String recharge(@RequestParam Integer id, ModelMap map) {
        if(id != null) {
            User user = userService.findById(id);
            map.put("user", user);
        }
        return "admin/user/recharge";
    }

    /**
     * 充值
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult recharge(@RequestParam(value = "addCoin") Double addCoin,
                                @RequestParam(value = "id") int id, ModelMap map) {
        try {
            userService.recharge(id, addCoin);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 充值页面
     * @return
     */
    @RequestMapping(value = "/editVip/{id}", method = RequestMethod.GET)
    public String editVip(@PathVariable Integer id, ModelMap map) {
        if(id != null) {
            User user = userService.findById(id);
            map.put("user", user);
        }
        return "admin/user/editVip";
    }

    /**
     * 修改Vip等级
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/editVip", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult editVip(@RequestParam(value = "vipStatus") int vipStatus,
                                @RequestParam(value = "id") int id, ModelMap map) {
        try {
            userService.editVip(id, vipStatus);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResultBean<List<User>> findAll() {
        String searchName = request.getParameter("search");
        int total;
        List<User> list;
        Pageable pageable = getPageableAndSort(Sort.by(Sort.Direction.DESC, "registerTime"));   // 排序
        if(searchName != null) {
            total = (int) userService.findByUserName(searchName, pageable).getTotalElements();
            list = userService.findByUserName(searchName, pageable).getContent();
        }else {
            total = (int) userService.findAll(pageable).getTotalElements();
            list = userService.findAll(pageable).getContent();
        }
        return new ResultBean<>(list, total, pageable.getPageSize(), pageable.getPageNumber());
    }

}
