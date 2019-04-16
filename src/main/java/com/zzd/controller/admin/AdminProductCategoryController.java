package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.Product;
import com.zzd.entity.ProductCategory;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.ProductCategoryService;
import com.zzd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类目类
 * @Author: hqy
 * @Date: 2019/1/15 12:57
 */
@Controller
@RequestMapping("/admin/category")
public class AdminProductCategoryController extends BaseController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    /**
     * 返回列表页面
     * @return
     */
    @RequestMapping("/list")
    public String list() {
        return "admin/category/list";
    }

    /**
     * 添加 修改页面
     * 跳转
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "id", required = false) Integer id, ModelMap map) {
        List<ProductCategory> productCategoryList = productCategoryService.findAll(1);
        map.put("categoryList", productCategoryList);
        if(id != null) {
            ProductCategory productCategory = productCategoryService.findById(id);
            map.put("category", productCategory);
        }
        return "admin/category/form";
    }

    /**
     * save
     * @param productCategory
     * @param map
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult form(ProductCategory productCategory, ModelMap map) {
        try {
            productCategoryService.saveOrUpdate(productCategory);
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
            ProductCategory category = productCategoryService.findById(id);
            if(category.getType() != 1) {
                List<Product> productList = productService.findByProductCategorySecId(id);
                if(productList == null || productList.isEmpty()) {
                    productCategoryService.delete(id);
                }else {
                    return JsonResult.failure("这个分类下存在杂志，无法删除");
                }
            }else {
                List<ProductCategory> categoryList = productCategoryService.findByParentId(id);
                if(categoryList == null || categoryList.isEmpty()) {
                    productCategoryService.delete(id);
                }else {
                    return JsonResult.failure("这个分类下存在二级分类，无法删除");
                }
            }
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 设置首页推荐
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/setIsIndex/{id}/{isIndex}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setIsIndex(@PathVariable int id, @PathVariable int isIndex, ModelMap map) {
        try {
            productCategoryService.setIsIndex(id, isIndex);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 列表请求
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public ResultBean<List<ProductCategory>> findAll() {
        Pageable pageable = getPageable();
        int total = (int) productCategoryService.findAll(pageable).getTotalElements();
        List<ProductCategory> list = productCategoryService.findAll(pageable).getContent();
        return new ResultBean<>(list, total, getPageable().getPageSize(), getPageable().getPageNumber());
    }

    /**
     * 列表请求 type
     * @param type
     * @return
     */
    @RequestMapping("/listtype.do")
    @ResponseBody
    public ResultBean<List<ProductCategory>> findAll(int type) {
        Pageable pageable = getPageable();
        int total = (int) productCategoryService.findAll(type, pageable).getTotalElements();
        List<ProductCategory> list = productCategoryService.findAll(type, pageable).getContent();
        return new ResultBean<>(list, total, getPageable().getPageSize(), getPageable().getPageNumber());
    }

}
