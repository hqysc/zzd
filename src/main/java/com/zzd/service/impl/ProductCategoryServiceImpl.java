package com.zzd.service.impl;

import com.zzd.dao.GalleryDao;
import com.zzd.dao.ProductCategoryDao;
import com.zzd.dao.ProductDao;
import com.zzd.dao.ProductImageDao;
import com.zzd.entity.Gallery;
import com.zzd.entity.Product;
import com.zzd.entity.ProductCategory;
import com.zzd.entity.ProductImage;
import com.zzd.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/1/15 12:37
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImageDao productImageDao;

    @Autowired
    private GalleryDao galleryDao;


    @Override
    public ProductCategory findById(int id) {
        return productCategoryDao.findProductCategoryById(id);
    }

    /**
     * 查询全部分类 一二级
     * @return
     */
    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> categoryList = productCategoryDao.findByType(1);
        for(ProductCategory category : categoryList) {
            List<ProductCategory> categorySecList = productCategoryDao.findByParentId(category.getId());
            category.setCategorySecList(categorySecList);
        }
        return categoryList;
    }

    @Override
    public List<ProductCategory> findAll(int type) {
        return productCategoryDao.findByType(type);
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        Page<ProductCategory> productCategories = productCategoryDao.findAll(pageable);
        // 子级获取父级分类信息
        for(ProductCategory productCategory : productCategories) {
            getParentCategory(productCategory);
        }
        return productCategories;
    }

    /**
     * 分页查询 type
     * @param type
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductCategory> findAll(int type, Pageable pageable) {
        return productCategoryDao.findProductCategoryByType(type, pageable);
    }

    @Override
    public List<ProductCategory> findALLExample(Example<ProductCategory> example) {
        return productCategoryDao.findAll(example);
    }

    /**
     * 根据父类id 查询
     * @param parentId
     * @return
     */
    @Override
    public List<ProductCategory> findByParentId(int parentId) {
        return productCategoryDao.findByParentId(parentId);
    }

    /**
     * 修改和保存
     * @param productCategory
     */
    @Override
    public void saveOrUpdate(ProductCategory productCategory) {
        if(productCategory.getId() != null) {
            ProductCategory dbProductCategory = productCategoryDao.findProductCategoryById(productCategory.getId());
            dbProductCategory.setId(productCategory.getId());
            dbProductCategory.setCatename(productCategory.getCatename());
            dbProductCategory.setType(productCategory.getType());
            dbProductCategory.setParentId(productCategory.getParentId());
            dbProductCategory.setCreateTime(productCategory.getCreateTime());
            dbProductCategory.setUpdateTime(new Date());
            productCategoryDao.saveAndFlush(dbProductCategory);
        }else {
            productCategory.setIsIndex(0);
            productCategory.setCreateTime(new Date());
            productCategory.setUpdateTime(new Date());
            productCategoryDao.save(productCategory);
        }
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(int id) {
        productCategoryDao.deleteById(id);
    }

    @Override
    public void setIsIndex(int id, int isIndex) {
        ProductCategory productCategory = productCategoryDao.findProductCategoryById(id);
        productCategory.setIsIndex(isIndex);
        productCategoryDao.saveAndFlush(productCategory);
    }

    /**
     * 查询全部Id
     * @return
     */
    @Override
    public List<Integer> findCategoryIds() {
        List<ProductCategory> categoryList = productCategoryDao.findAll();
        List<Integer> categoryIds = new ArrayList<>();
        for(ProductCategory category : categoryList) {
            categoryIds.add(category.getId());
        }
        return categoryIds;
    }

    /**
     * 获取父类
     * @param category
     */
    public void getParentCategory(ProductCategory category) {
        if(category.getType() == 2 && category.getParentId() != null) {
            ProductCategory parentCategory = productCategoryDao.findProductCategoryById(category.getParentId());
            category.setParentCategory(parentCategory);
        }
    }


}
