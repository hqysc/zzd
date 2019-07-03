package com.zzd.service;

import com.zzd.entity.Product;
import com.zzd.entity.ProductCategory;
import com.zzd.entity.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 产品Service
 * @Author: hqy
 * @Date: 2019/1/18 21:23
 */
public interface ProductService {

    Product findById(int id);

    Page<Product> findAll(Pageable pageable);

    /**
     * 最近更新
     * @return
     */
    List<Product> findByUpdate();

    /**
     * 查询推荐杂志 分页
     * @param pageable
     * @return
     */
    Page<Product> findByIsHot(int isHot, Pageable pageable);

    /**
     * 查询免费杂志 分页
     * @param pageable
     * @return
     */
    Page<Product> findByIsFree(int isFree, Pageable pageable);

    /**
     * 热门查询
     * @return
     */
    List<Product> findProductByIsHot();

    /**
     * 热门查询 随机
     * @return
     */
    List<Product> findRandByIsHot();

    /**
     * 查询免费杂志
     * @return
     */
    List<Product> findProductByIsFree();

    /**
     * 查询推荐分类
     * 根据推荐分类查找杂志
     */
    List<ProductCategory> findByIsIndex();

    /**
     * 根据二级分类查找 随机
     * @param categoryId
     * @return
     */
    List<Product> findRandByCategoryId(int categoryId);

    /**
     * 根据一级分类查询
     * @param categoryId
     * @param pageable
     * @return
     */
//    List<Product> findByProductCategoryId(int categoryId, Pageable pageable);

    /**
     * 根据二级分类查询
     * @param categoryId
     * @return
     */
    List<Product> findByProductCategorySecId(int categoryId);

    /**
     * 根据一级或二级分类查找
     * @param categoryId
     * @param pageable
     * @return
     */
    Page<Product> findByProductCategoryId(int categoryId, Pageable pageable);

    /**
     * 根据国家语言检查
     * @param countryId
     * @return
     */
    List<Product> checkByCountryId(int countryId);

    /**
     * 根据年份检查
     * @param yearsId
     * @return
     */
    List<Product> checkByYearsId(int yearsId);

    /**
     * 根据杂志类型检查
     * @param typeId
     * @return
     */
    List<Product> checkByProductTypeId(int typeId);

    /**
     * 多条件查询
     * @param categoryIds
     * @param yearsIds
     * @param countryIds
     * @param typeIds
     * @return
     */
    Page<Product> findByCheck(
            List<Integer> categoryIds, List<Integer> yearsIds, List<Integer> countryIds, List<Integer> typeIds,
            List<Integer> isHot, List<Integer> isFree, Pageable pageable);

    /**
     * 模糊搜索
     * @param keyword
     * @return
     */
    Page<Product> findByProductNameIsLike(String keyword, Pageable pageable);

    /**
     * 图片查询
     * @param productId
     * @return
     */
    List<ProductImage> findProductImages(int productId);

    /**
     * 添加 修改
     * @param product
     */
    void saveOrUpdate(Product product, int[] galleryIds, int[] sortIds);

    /**
     * 删除
     * @param productId
     */
    void delete(int productId);

    /**
     * 热卖推荐
     */
    void setIsHot(int productId, int isHot);

    /**
     * 商品是否上架
     */
    void setStatus(int productId, int status);

    /**
     * 设置免费
     */
    void setIsFree(int productId, int isFree);

    /**
     * 设置标签状态
     */
    void setTagStatus(int productId, int tagStatus);

    /**
     * 添加图片
     * @param productId
     * @param galleryIds
     * @param sortIds
     */
    void grant(Integer productId, int[] galleryIds, int[] sortIds);

    /**
     * 删除图片
     * @param imageId
     */
    void deleteImage(int imageId);

}
