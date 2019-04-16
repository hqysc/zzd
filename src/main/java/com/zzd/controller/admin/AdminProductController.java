package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.*;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * 产品管理控制层
 * @Author: hqy
 * @Date: 2019/1/18 21:58
 */

@Controller
@RequestMapping("/admin/product")
public class AdminProductController extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private YearsService yearsService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductDescribeService productDescribeService;
    @Autowired
    private GalleryService galleryService;

    private Logger LOGGER = LoggerFactory.getLogger(getClass());


    /**
     * 列表界面
     * 跳转
     * @return
     */
    @RequestMapping({ "", "/index" })
    public String list() {
        return "admin/product/list";
    }

    /**
     * 添加 修改页面
     * 跳转
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "id", required = false) Integer id, ModelMap map) {
        List<ProductCategory> productCategory = productCategoryService.findAll(2);
        List<Years> yearsList = yearsService.findAll();
        List<Country> countryList = countryService.findAll();
        List<ProductType> typeList = productTypeService.findAll();
        List<ProductDescribe> describeList = productDescribeService.findAll();
        map.put("productCategory", productCategory);
        map.put("yearsList", yearsList);
        map.put("countryList", countryList);
        map.put("typeList", typeList);
        map.put("describeList", describeList);
        if(id != null) {
            Product product = productService.findById(id);
            List<ProductImage> productImageList = productService.findProductImages(id);
            map.put("product", product);
            map.put("productImageList", productImageList);
        }
        return "admin/product/form";
    }

    /**
     * 保存
     * @param product
     * @param map
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult form(Product product, int[] galleryIds, int[] sortIds, ModelMap map) {
        try {
            productService.saveOrUpdate(product, galleryIds, sortIds);
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
            productService.delete(id);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 删除图片
     * @param imageId
     * @param map
     * @return
     */
    @RequestMapping(value = "deleteImage/{imageId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteImage(@PathVariable int imageId, ModelMap map) {
        try {
            productService.deleteImage(imageId);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 设置是否热卖
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/setIsHot/{id}/{isHot}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setIsHot(@PathVariable int id, @PathVariable int isHot, ModelMap map) {
        try {
            productService.setIsHot(id, isHot);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 设置杂志上下架
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/setStatus/{id}/{status}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setStatus(@PathVariable int id, @PathVariable int status, ModelMap map) {
        try {
            productService.setStatus(id, status);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 设置杂志免费
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/setIsFree/{id}/{isFree}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setIsFree(@PathVariable int id, @PathVariable int isFree, ModelMap map) {
        try {
            productService.setIsFree(id, isFree);
        }catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 图片选择页面
     * @param searchText
     * @param map
     * @return
     */
    @RequestMapping("/imageList")
    public String imageList(@RequestParam(value="searchText", required = false) String searchText, ModelMap map)   {
        if(searchText != null) {
            List<Gallery> galleryList = galleryService.findByImageNameContaining(searchText);
            map.put("galleryList", galleryList);
        }else {
            List<Gallery> galleryList = galleryService.findByType(1);   // 查询杂志图片
            map.put("galleryList", galleryList);
        }
        return "admin/product/imageList";
    }

    /**
     * 二级分类
     * @param parentId
     * @param map
     * @return
     */
    @RequestMapping(value = "/findCategorySec/{parentId}")
    @ResponseBody
    public  List<ProductCategory> findCategorySec(@PathVariable("parentId") int parentId, ModelMap map) {
        List<ProductCategory> productCategorySec = productCategoryService.findByParentId(parentId);
        map.addAttribute("productCategorySec", productCategorySec);
        return productCategorySec;
    }

    /**
     * 查询杂志图片
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping(value = "/findProductImages/{productId}", method = RequestMethod.POST)
    @ResponseBody
    public List<ProductImage> findProductImages(@PathVariable Integer productId, ModelMap map) {
        List<ProductImage> productImageList = productService.findProductImages(productId);
        return productImageList;
    }

    /**
     * 列表 分页查询 Json
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResultBean<List<Product>> findAll(@RequestParam(value = "cateId", required = false) Integer cateId) {
        String searchText = request.getParameter("search");
        Pageable pageable = getPageableAndSort(Sort.by(Sort.Direction.DESC, "updateTime"));   // 排序
        int total;
        List<Product> list;
        if(cateId != null) {
            total = (int) productService.findByProductCategoryId(cateId, pageable).getTotalElements();
            list = productService.findByProductCategoryId(cateId, pageable).getContent();
        }else {
            if(searchText != null) {
                total = (int) productService.findByProductNameIsLike(searchText, pageable).getTotalElements();
                list = productService.findByProductNameIsLike(searchText, pageable).getContent();
            }else {
                total = (int) productService.findAll(pageable).getTotalElements();
                list = productService.findAll(pageable).getContent();
            }
        }
        return new ResultBean<>(list, total, pageable.getPageSize(), pageable.getPageNumber());
    }

    /**
     * 查询全部分类
     * @return
     */
    @RequestMapping("/categoryList")
    @ResponseBody
    public List<ProductCategory> findCategory() {
        List<ProductCategory> categoryList = productCategoryService.findAll();
        return categoryList;
    }

}
