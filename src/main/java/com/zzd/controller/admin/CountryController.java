package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.Country;
import com.zzd.entity.Product;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.CountryService;
import com.zzd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/15 2:20
 */

@Controller
@RequestMapping("/admin/country")
public class CountryController extends BaseController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProductService productService;

    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    public String list() {
        return "admin/country/list";
    }

    /**
     * 添加 修改页面
     * 跳转
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "id", required = false) Integer id, ModelMap map) {
        if(id != null) {
            Country country = countryService.findById(id);
            map.put("country", country);
        }
        return "admin/country/form";
    }

    /**
     * save
     * @param country
     * @param map
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult form(Country country, ModelMap map) {
        try {
            countryService.saveOrUpdate(country);
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
            List<Product> productList = productService.checkByCountryId(id);
            if(productList == null || productList.isEmpty()) {
                countryService.delete(id);
            }else {
                return JsonResult.failure("这个分类下存在杂志，无法删除");
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
    public ResultBean<List<Country>> findAll() {
        Pageable pageable = getPageable();
        int total = (int) countryService.findAll(pageable).getTotalElements();
        List<Country> countryList = countryService.findAll(pageable).getContent();
        return new ResultBean<>(countryList, total, pageable.getPageSize(), pageable.getPageNumber());
    }

}
