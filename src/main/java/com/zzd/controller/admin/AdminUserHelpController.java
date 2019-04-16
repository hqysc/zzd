package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.UserHelp;
import com.zzd.entity.UserHelpItem;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.UserHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/8 23:26
 */

@Controller
@RequestMapping("/admin/help")
public class AdminUserHelpController extends BaseController {

    @Autowired
    private UserHelpService userHelpService;

    @RequestMapping({"", "/index"})
    public String list() {
        return "admin/help/list";
    }

    @RequestMapping({"/item", "/item/index"})
    public String itemList() {
        return "admin/help/item/list";
    }

    /**
     * 添加 修改页面
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "id", required = false) Integer id, ModelMap map) {
        if(id != null) {
            UserHelp userHelp = userHelpService.findById(id);
            map.put("userHelp", userHelp);
        }
        return "admin/help/form";
    }

    /**
     * save
     * @param userHelp
     * @param map
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult form(UserHelp userHelp, ModelMap map) {
        try {
            userHelpService.saveOrUpdate(userHelp);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 删除
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable int id, ModelMap map) {
        try {
            userHelpService.delete(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 查询全部类目 分页
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResultBean<List<UserHelp>> findAll() {
        Pageable pageable = getPageableAndSort(Sort.by(Sort.Direction.ASC, "sortId"));   // 排序
        int total = (int) userHelpService.findAll(pageable).getTotalElements();
        List<UserHelp> list = userHelpService.findAll(pageable).getContent();
        return new ResultBean<>(list, total, pageable.getPageSize(), pageable.getPageNumber());
    }

    /**
     * 查询全部item 分页
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public ResultBean<List<UserHelpItem>> findItemAll(@RequestParam(value = "helpId", required = false) Integer helpId) {
        Pageable pageable;
        int total;
        List<UserHelpItem> list;
        if(helpId != null) {
            pageable = getPageableAndSort(Sort.by(Sort.Direction.ASC, "sortId"));   // 排序
            total = (int) userHelpService.findByHelpId(helpId, pageable).getTotalElements();
            list = userHelpService.findByHelpId(helpId, pageable).getContent();
        }else {
            pageable = getPageableAndSort(Sort.by(Sort.Direction.DESC, "updateTime"));   // 排序
            total = (int) userHelpService.findItemAll(pageable).getTotalElements();
            list = userHelpService.findItemAll(pageable).getContent();
        }
        return new ResultBean<>(list, total, pageable.getPageSize(), pageable.getPageNumber());
    }

    /**
     * 查询全部类目 列表
     * @return
     */
    @RequestMapping("/helpList")
    @ResponseBody
    public List<UserHelp> findHelpList() {
        return userHelpService.findAll();
    }

    /**
     * 添加 修改页面 item
     * @return
     */
    @RequestMapping(value = "/item/form", method = RequestMethod.GET)
    public String formItem(@RequestParam(value = "id", required = false) Integer id, ModelMap map) {
        List<UserHelp> userHelpList = userHelpService.findAll();
        map.put("userHelpList", userHelpList);
        if(id != null) {
            UserHelpItem helpItem = userHelpService.findItemById(id);
            map.put("helpItem", helpItem);
        }
        return "admin/help/item/form";
    }

    /**
     * Save item
     * @param helpItem
     * @param map
     * @return
     */
    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult formItem(UserHelpItem helpItem, ModelMap map) {
        try {
            userHelpService.saveOrUpdateItem(helpItem);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 删除 item
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/item/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteItem(@PathVariable int id, ModelMap map) {
        try {
            userHelpService.deleteItem(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

}
