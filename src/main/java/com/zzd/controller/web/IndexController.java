package com.zzd.controller.web;

import com.zzd.entity.Advert;
import com.zzd.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/1/14 21:33
 */

@Controller
public class IndexController {

    @Autowired
    private AdvertService advertService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value={"/","/index"})
    public String index(ModelMap map) {
        List<Advert> advertList = advertService.findAll();
        map.put("advertList", advertList);
        return "zzd/index";
    }

}
