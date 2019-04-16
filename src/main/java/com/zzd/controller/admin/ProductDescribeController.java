package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.Product;
import com.zzd.entity.ProductDescribe;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.ProductDescribeService;
import com.zzd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 杂志描述 Controller
 *
 * @Author: hqy
 * @Date: 2019/4/8 15:18
 */

@Controller
@RequestMapping("/admin/describe")
public class ProductDescribeController extends BaseController {

    @Autowired
    private ProductDescribeService productDescribeService;

    @RequestMapping({"", "/index"})
    public String list() {
        return "admin/describe/list";
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
            ProductDescribe describe = productDescribeService.findById(id);
            map.put("describe", describe);
        }
        return "admin/describe/form";
    }

    /**
     * save
     * @param describe
     * @param map
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult form(ProductDescribe describe, ModelMap map) {
        try {
            productDescribeService.saveOrUpdate(describe);
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
            List<Product> productList = productDescribeService.checkByDescribeId(id);
            if(productList == null || productList.isEmpty()) {
                productDescribeService.delete(id);
            }else {
                return JsonResult.failure("这个描述下存在杂志，无法删除");
            }
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
    public ResultBean<List<ProductDescribe>> findAll() {
        Pageable pageable = getPageable();
        int total = (int) productDescribeService.findAll(pageable).getTotalElements();
        List<ProductDescribe> list = productDescribeService.findAll(pageable).getContent();
        return new ResultBean<>(list, total, pageable.getPageSize(), pageable.getPageNumber());
    }

}
