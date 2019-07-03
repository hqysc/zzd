package com.zzd.service.impl;

import com.zzd.dao.*;
import com.zzd.entity.*;
import com.zzd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.OrderBy;
import java.util.*;

/**
 * @Author: hqy
 * @Date: 2019/1/18 21:29
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private GalleryDao galleryDao;
    @Autowired
    private YearsDao yearsDao;
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private ProductDescribeDao productDescribeDao;

    @Override
    public Product findById(int id) {
        Product product = productDao.findProductById(id);
        getProductImages(product);
        getInfo(product);
        return product;
    }

    /**
     * 杂志分页查询
     * @param pageable
     * @return
     */
    @Override
    public Page<Product> findAll(Pageable pageable) {
        Page<Product> productList = productDao.findAll(pageable);
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    @Override
    public List<Product> findByUpdate() {
        List<Product> productList = productDao.findByUpdate();
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    /**
     * 查询热门杂志 分页
     * @param isHot
     * @param pageable
     * @return
     */
    @Override
    public Page<Product> findByIsHot(int isHot, Pageable pageable) {
        Page<Product> productList = productDao.findProductByIsHot(isHot, pageable);
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    /**
     * 查询免费杂志 分页
     * @param isFree
     * @param pageable
     * @return
     */
    @Override
    public Page<Product> findByIsFree(int isFree, Pageable pageable) {
        Page<Product> productList = productDao.findProductByIsFree(isFree, pageable);
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    /**
     * 根据热门查找
     * @return
     */
    @Override
    public List<Product> findProductByIsHot() {
        Pageable pageable = PageRequest.of(0, 12, Sort.by(Sort.Direction.DESC, "updateTime"));
        List<Product> productList = productDao.findByIsHot(1, pageable);
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    /**
     * 首页热门查询 随机
     * @return
     */
    @Override
    public List<Product> findRandByIsHot() {
        List<Product> productList = productDao.findRandByIsHot(1);
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    /**
     * 查找免费
     * @return
     */
    @Override
    public List<Product> findProductByIsFree() {
        List<Product> productList = productDao.findRandByIsFree(1);
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    /**
     * 查询 首页推荐
     * 包括类目中杂志
     */
    @Override
    public List<ProductCategory> findByIsIndex() {
        Pageable pageable = PageRequest.of(0, 12, Sort.by(Sort.Direction.DESC, "updateTime"));
        List<ProductCategory> productCategoryList = productCategoryDao.findByIsIndex(1);
        // 查询杂志
        for(ProductCategory productCategory : productCategoryList) {
            List<Product> productList = productDao.findByCategoryId(productCategory.getId(), pageable);
            for(Product product : productList) {
                getCoverImage(product, 1);
                // 年份
                if(product.getYearsId() != null) {
                    product.setYears(yearsDao.findYearsById(product.getYearsId()));
                }
                // 语言
                if(product.getCountryId() != null) {
                    product.setCountry(countryDao.findCountryById(product.getCountryId()));
                }
                // 类型
                if(product.getTypeId() != null) {
                    product.setType(productTypeDao.findProductTypeById(product.getTypeId()));
                }
            }
            productCategory.setProduct(productList);
        }
        return productCategoryList;
    }

    /**
     * 根据二级分类查找 随机
     * @param categoryId
     * @return
     */
    @Override
    public List<Product> findRandByCategoryId(int categoryId) {
        List<Product> productList = productDao.findRandByCategoryId(categoryId);
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    /**
     * 根据二级分类查找
     * @param categoryId
     * @return
     */
    @Override
    public List<Product> findByProductCategorySecId(int categoryId) {
        return productDao.findByCategoryId(categoryId);
    }

    /**
     * 根据一级分类查找
     * 分页
     * @param categoryId
     * @param pageable
     * @return
     */
    /*
    @Override
    public List<Product> findByProductCategoryId(int categoryId, Pageable pageable) {
        List<ProductCategory> productCategories = productCategoryDao.findByParentId(categoryId);
        List<Integer> secIds = new ArrayList<>();
        for(ProductCategory productCategory : productCategories) {
            secIds.add(productCategory.getId());
        }
        return productDao.findByCategoryIdIn(secIds, pageable);
    }
    */

    /**
     * 根据一级或者二级分类查找 分页
     * @param categoryId
     * @param pageable
     * @return
     */
    @Override
    public Page<Product> findByProductCategoryId(int categoryId, Pageable pageable) {
        ProductCategory category = productCategoryDao.findProductCategoryById(categoryId);
        Page<Product> productList;

        if(category.getType() != 1) {   // 查询二级分类
            productList = productDao.findProductByCategoryId(categoryId, pageable);
        }else {     // 查询一级分类
            List<Integer> categoryIds = new ArrayList<>();
            for(ProductCategory productCategory : productCategoryDao.findByParentId(categoryId)) {
                categoryIds.add(productCategory.getId());
            }
            productList = productDao.findProductByCategoryIdIn(categoryIds, pageable);
        }

        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    /**
     * 根据国家语言检查
     * @param countryId
     * @return
     */
    @Override
    public List<Product> checkByCountryId(int countryId) {
        return productDao.findByCountryId(countryId);
    }

    /**
     * 根据年份ID检查
     * @param yearsId
     * @return
     */
    @Override
    public List<Product> checkByYearsId(int yearsId) {
        return productDao.findByYearsId(yearsId);
    }

    /**
     * 根据杂志类型检查
     * @param typeId
     * @return
     */
    @Override
    public List<Product> checkByProductTypeId(int typeId) {
        return productDao.findByTypeId(typeId);
    }

    /**
     * 多条件查询
     * @param categoryIds
     * @param yearsIds
     * @param countryIds
     * @param typeIds
     * @return
     */
    @Override
    public Page<Product> findByCheck(
            List<Integer> categoryIds, List<Integer> yearsIds, List<Integer> countryIds, List<Integer> typeIds, List<Integer> isHot, List<Integer> isFree, Pageable pageable) {

        Page<Product> productList = productDao.findByCategoryIdInAndYearsIdInAndCountryIdInAndTypeIdInAndIsHotInAndIsFreeIn(
                categoryIds, yearsIds, countryIds, typeIds, isHot, isFree, pageable);
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }

    /**
     * 模糊搜索
     * @param keyword
     * @return
     */
    @Override
    public Page<Product> findByProductNameIsLike(String keyword, Pageable pageable) {
        Page<Product> productList = productDao.findByProductNameContaining(keyword, pageable);
        for(Product product : productList) {
            getCoverImage(product, 1);
            getInfo(product);
        }
        return productList;
    }


    /**
     * 添加 编辑
     * @param product
     */
    @Override
    public void saveOrUpdate(Product product, int[] galleryIds, int[] sortIds) {
        if(product.getId() != null) {
            Product dbProduct = productDao.findProductById(product.getId());
            dbProduct.setProductName(product.getProductName());
            dbProduct.setCategoryId(product.getCategoryId());
            dbProduct.setYearsId(product.getYearsId());
            dbProduct.setCountryId(product.getCountryId());
            dbProduct.setTypeId(product.getTypeId());
            dbProduct.setPrice(product.getPrice());
            dbProduct.setNum(product.getNum());
            dbProduct.setFileSize(product.getFileSize());
            dbProduct.setDescribeId(product.getDescribeId());
            dbProduct.setBaiduyunUrl(product.getBaiduyunUrl());
            dbProduct.setUpdateTime(new Date());
            productDao.saveAndFlush(dbProduct);
            grant(product.getId(), galleryIds, sortIds);
        }else {
            product.setCreateTime(new Date());
            product.setUpdateTime(new Date());
            product.setTagStatus(0);
            product.setIsHot(0);
            product.setIsFree(0);
            product.setProductStatus(1);
            grant(productDao.save(product).getId(), galleryIds, sortIds);
        }
    }

    /**
     * 删除
     * @param productId
     */
    @Override
    public void delete(int productId) {
        productDao.deleteById(productId);
        for(ProductImage productImage : findProductImages(productId)) {
            if(productImage != null) {
                productImageDao.deleteById(productImage.getId());
            }
        }
    }

    @Override
    public void setIsHot(int productId, int isHot) {
        Product product = productDao.findProductById(productId);
        product.setIsHot(isHot);
        productDao.saveAndFlush(product);
    }

    @Override
    public void setStatus(int productId, int status) {
        Product product = productDao.findProductById(productId);
        product.setProductStatus(status);
        productDao.saveAndFlush(product);
    }

    /**
     * 设置杂志免费
     * @param productId
     * @param isFree
     */
    @Override
    public void setIsFree(int productId, int isFree) {
        Product product = productDao.findProductById(productId);
        product.setIsFree(isFree);
        productDao.saveAndFlush(product);
    }

    /**
     * 设置标签状态
     * @param productId
     * @param tagStatus
     */
    @Override
    public void setTagStatus(int productId, int tagStatus) {
        Product product = productDao.findProductById(productId);
        product.setTagStatus(tagStatus);
        productDao.saveAndFlush(product);
    }

    /**
     * 杂志图片查询
     * @param productId
     * @return
     */
    @Override
    public List<ProductImage> findProductImages(int productId) {
        Sort sort = new Sort(Sort.Direction.ASC, "sortId");
        List<ProductImage> productImageList = productImageDao.findByProductId(productId, sort);
        for(ProductImage productImage : productImageList) {
            int galleryId = productImage.getGalleryId();
            Gallery gallery = galleryDao.findById(galleryId);
            productImage.setGallery(gallery);
        }
        return productImageList;
    }

    /**
     * 添加图片
     * @param productId
     * @param galleryIds
     * @param sortIds
     */
    @Override
    public void grant(Integer productId, int[] galleryIds, int[] sortIds) {
        List<ProductImage> productImageList = productImageDao.findByProductId(productId);   // 查找已存在的图片
        List<ProductImage> newList = new ArrayList<>();  // 空集合
        if(galleryIds != null) {
            // 添加到空集合
            for (int i = 0; i < galleryIds.length; i++) {
                int galleryId = galleryIds[i]; // 获取gallery Id
                ProductImage checkProductImage = productImageDao.findProductImageByProductIdAndGalleryId(productId, galleryId);
                if(checkProductImage != null) {
                    newList.add(checkProductImage);
                }
            }
            // Delete
            for(ProductImage productImage : productImageList) {
                if(!newList.contains(productImage)) {
                    productImageDao.deleteById(productImage.getId());
                }
            }

            // Save or Update
            for (int i = 0; i < galleryIds.length; i++) {
                int galleryId = galleryIds[i]; // 获取gallery Id
                int sortId = sortIds[i]; // 获取gallery Id
                Gallery gallery = galleryDao.findById(galleryId);
                // 检查重复已有的图片
                ProductImage checkProductImage = productImageDao.findProductImageByProductIdAndGalleryId(productId, galleryId);
                // create
                if(checkProductImage == null) {
                    ProductImage productImage = new ProductImage();
                    productImage.setProductId(productId);
                    productImage.setGalleryId(galleryId);
                    productImage.setSortId(sortId);
                    productImageDao.save(productImage);
                // save
                }else {
                    checkProductImage.setId(checkProductImage.getId());
                    checkProductImage.setProductId(productId);
                    checkProductImage.setGalleryId(galleryId);
                    checkProductImage.setSortId(sortId);
                    productImageDao.saveAndFlush(checkProductImage);
                }
            }
        }
    }

    /**
     * 删除图片
     * @param imageId
     */
    @Override
    public void deleteImage(int imageId) {
        productImageDao.deleteById(imageId);
    }

    /**
     * 获取图片
     * 排序
     * @param product
     */
    public void getProductImages(Product product) {
        // 图片
        Sort sort = new Sort(Sort.Direction.ASC, "sortId");
        List<ProductImage> productImageList = productImageDao.findByProductId(product.getId(), sort);
        for(ProductImage productImage : productImageList) {
            int galleryId = productImage.getGalleryId();
            Gallery gallery = galleryDao.findById(galleryId);
            productImage.setGallery(gallery);
        }
        product.setProductImage(productImageList);
    }

    /**
     * 获取图片 序号1
     * @param product
     */
    public void getCoverImage(Product product, int sortId) {
        // 图片
        List<ProductImage> productImageList = productImageDao.findByProductIdAndAndSortId(product.getId(),sortId);
        for(ProductImage productImage : productImageList) {
            int galleryId = productImage.getGalleryId();
            Gallery gallery = galleryDao.findById(galleryId);
            product.setGallery(gallery);
        }
    }

    /**
     * 获取年份
     * @param product
     */
    public void getInfo(Product product) {
        // 分类
        if(product.getCategoryId() != null) {
            ProductCategory category = productCategoryDao.findProductCategoryById(product.getCategoryId());
            product.setCategory(category);
        }
        // 年份
        if(product.getYearsId() != null) {
            Years years = yearsDao.findYearsById(product.getYearsId());
            product.setYears(years);
        }
        // 语言
        if(product.getCountryId() != null) {
            Country country = countryDao.findCountryById(product.getCountryId());
            product.setCountry(country);
        }
        // 类型
        if(product.getTypeId() != null) {
            ProductType productType = productTypeDao.findProductTypeById(product.getTypeId());
            product.setType(productType);
        }
        if(product.getDescribeId() != null) {
            ProductDescribe describe = productDescribeDao.findProductDescribeById(product.getDescribeId());
            product.setDescribe(describe);
        }
    }


}
