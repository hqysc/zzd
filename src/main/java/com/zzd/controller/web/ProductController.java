package com.zzd.controller.web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zzd.entity.*;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: hqy
 * @Date: 2019/2/13 1:05
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private YearsService yearsService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private AdvertService advertService;

    /**
     * 首页热门杂志 随机
     * @param map
     * @return
     */
    @RequestMapping("hotList")
    @ResponseBody
    public List<Product> hotList(ModelMap map) {
        List<Product> productList = productService.findRandByIsHot();
        map.put("productList", productList);
        return productList;
    }

    /**
     * 首页免费杂志
     * @param map
     * @return
     */
    @RequestMapping("freeList")
    @ResponseBody
    public List<Product> freeList(ModelMap map) {
        List<Product> productList = productService.findProductByIsFree();
        map.put("productList", productList);
        return productList;
    }

    /**
     * 首页热门杂志
     * @param map
     * @return
     */
    @RequestMapping("updateList")
    @ResponseBody
    public List<Product> updateList(ModelMap map) {
        List<Product> productList = productService.findByUpdate();
        map.put("productList", productList);
        return productList;
    }

    /**
     * 首页推荐分类
     * @param map
     * @return
     */
    @RequestMapping("indexList")
    @ResponseBody
    public List<ProductCategory> indexList(ModelMap map) {
        List<ProductCategory> productCategoryList = productService.findByIsIndex();
        map.put("categoryList ", productCategoryList);
        return productCategoryList;
    }

    /**
     * 详情页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable int id, ModelMap map) {
        Product product = productService.findById(id);
        List<Advert> advertList = advertService.findByAdvertType(1);
        List<Product> productList = productService.findRandByCategoryId(product.getCategoryId());
        map.put("advertList", advertList);
        map.put("product", product);
        map.put("productList", productList);
        return "zzd/product/detail";
    }

    /**
     * 多查询
     * @param pageNum
     * @param categoryIds
     * @param yearsIds
     * @param countryIds
     * @param typeIds
     * @param map
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String checkList(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "categoryId", required = false) List<Integer> categoryIds,
                            @RequestParam(value = "yearsId", required = false) List<Integer> yearsIds,
                            @RequestParam(value = "countryId", required = false) List<Integer> countryIds,
                            @RequestParam(value = "typeId", required = false) List<Integer> typeIds,
                            @RequestParam(value = "isHot", required = false) List<Integer> isHot,
                            @RequestParam(value = "isFree", required = false) List<Integer> isFree,
                            ModelMap map) {

        Pageable pageable = PageRequest.of(pageNum - 1, 24, Sort.by(Sort.Direction.DESC, "updateTime"));

        // 如果isHot或者isFree为空 就传一个{0, 1}的集合
        List<Integer> nullTypeList = new ArrayList<>();
        for(int i=0; i<=1; i++) {
            nullTypeList.add(i);
        }

        if(categoryIds == null || categoryIds.isEmpty()) { categoryIds = productCategoryService.findCategoryIds(); }
        if(yearsIds == null || yearsIds.isEmpty()) { yearsIds = yearsService.findYearsIds(); }
        if(countryIds == null || countryIds.isEmpty()) { countryIds = countryService.findCountryIds(); }
        if(typeIds == null || typeIds.isEmpty()) { typeIds = productTypeService.findTypeIds(); }
        if(isHot == null || isHot.isEmpty()) { isHot = nullTypeList; }
        if(isFree == null || isFree.isEmpty()) { isFree = nullTypeList; }

        List<Product> productList = productService.findByCheck(categoryIds, yearsIds, countryIds, typeIds, isHot, isFree, pageable).getContent();
        int pageTotal = (int) productService.findByCheck(categoryIds, yearsIds, countryIds, typeIds, isHot, isFree, pageable).getTotalPages();

        map.put("pageNum", pageNum); // 传到前端
        map.put("productList", productList);
        map.put("pageTotal", pageTotal);

        getInfoList(map);   // 得到信息数据

        return "zzd/product/list";
    }

    /**
     * 搜索
     * @param searchText
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchList(@RequestParam(value = "searchText") String searchText,
                             @RequestParam(value = "page", defaultValue = "1") Integer pageNum, ModelMap map) {

        Pageable pageable = PageRequest.of(pageNum - 1, 24, Sort.by(Sort.Direction.DESC, "updateTime"));
        List<Product> productList = productService.findByProductNameIsLike(searchText, pageable).getContent();
        int pageTotal = (int) productService.findByProductNameIsLike(searchText, pageable).getTotalPages();

        map.put("pageNum", pageNum); // 传到前端
        map.put("productList", productList);
        map.put("pageTotal", pageTotal);
        getInfoList(map);
        return "zzd/product/list";
    }

    /**
     * 分类页面信息
     */
    public void getInfoList(ModelMap map) {
        List<ProductCategory> categoryList = productCategoryService.findAll(2);
        List<Years> yearsList = yearsService.findAll();
        List<Country> countryList = countryService.findAll();
        List<ProductType> typeList = productTypeService.findAll();
        map.put("categoryList", categoryList);
        map.put("yearsList", yearsList);
        map.put("countryList", countryList);
        map.put("typeList", typeList);
    }

}
