package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.Product;
import com.zzd.entity.ProductType;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.ProductService;
import com.zzd.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/16 16:35
 */

@Controller
@RequestMapping("/admin/productType")
public class ProductTypeController extends BaseController {

    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductService productService;

    /**
     * 列表
     * @return
     */
    @RequestMapping("/list")
    public String list() {
        return "admin/productType/list";
    }

    /**
     * 添加 修改页面
     * 跳转
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "id", required = false) Integer id, ModelMap map) {
        if(id != null) {
            ProductType productType = productTypeService.findById(id);
            map.put("productType", productType);
        }
        return "admin/productType/form";
    }

    /**
     * save
     * @param productType
     * @param map
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult form(ProductType productType, ModelMap map) {
        try {
            productTypeService.saveOrUpdate(productType);
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
            List<Product> productList = productService.checkByProductTypeId(id);
            if(productList == null || productList.isEmpty()) {
                productTypeService.delete(id);
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
    public ResultBean<List<ProductType>> findAll() {
        Pageable pageable = getPageable();
        int total = (int) productTypeService.findAll(pageable).getTotalElements();
        List<ProductType> productTypeList = productTypeService.findAll(pageable).getContent();
        return new ResultBean<>(productTypeList, total, pageable.getPageSize(), pageable.getPageNumber());
    }

}
