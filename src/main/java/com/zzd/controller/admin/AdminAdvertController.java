package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.Advert;
import com.zzd.entity.Gallery;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.AdvertService;
import com.zzd.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/9 21:46
 */

@Controller
@RequestMapping("/admin/advert")
public class AdminAdvertController extends BaseController {

    @Autowired
    private AdvertService advertService;
    @Autowired
    private GalleryService galleryService;

    @RequestMapping({"", "/index"})
    public String list() {
        return "admin/advert/list";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "admin/advert/upload";
    }

    /**
     * 跳转 form
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "id", required = false) Integer id, ModelMap map) {
        if(id != null) {
            Advert advert = advertService.findById(id);
            map.put("advert", advert);
        }
        return "admin/advert/form";
    }

    /**
     * save
     * @param advert
     * @param map
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult form(Advert advert, ModelMap map) {
        try {
            advertService.saveOrUpdate(advert);
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
            advertService.delete(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 设置杂志类型
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/setType/{id}/{advertType}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setAdvertType(@PathVariable int id, @PathVariable int advertType, ModelMap map) {
        try {
            advertService.setAdvertType(id, advertType);
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
    public ResultBean<List<Advert>> findAll() {
        Pageable pageable = getPageableAndSort(Sort.by(Sort.Direction.ASC, "sortId"));   // 排序
        int total = (int) advertService.findAll(pageable).getTotalElements();
        List<Advert> list = advertService.findAll(pageable).getContent();
        return new ResultBean<>(list, total, pageable.getPageSize(), pageable.getPageNumber());
    }

    /**
     * 图片选择页面
     * @param map
     * @return
     */
    @RequestMapping("/imageList")
    public String imageList(@RequestParam int id, ModelMap map) {
        List<Gallery> galleryList = galleryService.findByType(2);   // 广告图片
        Advert advert = advertService.findById(id);
        map.put("galleryList", galleryList);
        map.put("advert", advert);
        return "admin/advert/imageList";
    }

    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult grant(int id, int galleryId, ModelMap map) {
        try {
            advertService.grantGallery(id, galleryId);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
