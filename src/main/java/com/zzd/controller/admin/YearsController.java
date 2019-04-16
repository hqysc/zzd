package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.Product;
import com.zzd.entity.Years;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.ProductService;
import com.zzd.service.YearsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/15 1:10
 */

@Controller
@RequestMapping("/admin/years")
public class YearsController extends BaseController {

    @Autowired
    private YearsService yearsService;

    @Autowired
    private ProductService productService;

    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    public String list() {
        return "admin/years/list";
    }

    /**
     * 添加 修改页面
     * 跳转
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "id", required = false) Integer id, ModelMap map) {
        if(id != null) {
            Years years = yearsService.findById(id);
            map.put("years", years);
        }
        return "admin/years/form";
    }

    /**
     * save
     * @param years
     * @param map
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult form(Years years, ModelMap map) {
        try {
            yearsService.saveOrUpdate(years);
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
            List<Product> productList = productService.checkByYearsId(id);
            if(productList == null || productList.isEmpty()) {
                yearsService.delete(id);
            }else {
                return JsonResult.failure("这个类目下存在杂志，无法删除");
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
    @RequestMapping("/list.do")
    @ResponseBody
    public ResultBean<List<Years>> findAll() {
        Pageable pageable = getPageable();
        int total = (int) yearsService.findAll(pageable).getTotalElements();
        List<Years> yearsList = yearsService.findAll(pageable).getContent();
        return new ResultBean<>(yearsList, total, pageable.getPageSize(), pageable.getPageNumber());
    }

}
