package com.zzd.controller.web;

import com.zzd.entity.UserHelp;
import com.zzd.entity.UserHelpItem;
import com.zzd.service.UserHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/11 6:55
 */

@Controller
@RequestMapping("/help")
public class UserHelpController {

    @Autowired
    private UserHelpService userHelpService;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap map) {
        List<UserHelp> userHelpList = userHelpService.findAll();
        map.put("userHelpList", userHelpList);
        return "zzd/help/userHelp";
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserHelpItem getContent(@PathVariable int id, ModelMap map) {
        UserHelpItem helpItem = userHelpService.findItemById(id);
        return helpItem;
    }

}
