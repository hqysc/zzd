package com.zzd.dao;

import com.zzd.entity.ProductImage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 产品图片Dao
 * @Author: hqy
 * @Date: 2019/2/1 22:01
 */

public interface ProductImageDao extends JpaRepository<ProductImage, Integer> {

    /**
     * 根据杂志Id查询
     * @param productId
     * @return
     */
    List<ProductImage> findByProductId(int productId);

    /**
     * 根据杂志Id查询
     * 排序
     * @param productId
     * @return
     */
    List<ProductImage> findByProductId(int productId, Sort sort);

    /**
     * 根据杂志Id 序号Id 查询
     * @param productId
     * @param sortId
     * @return
     */
    List<ProductImage> findByProductIdAndAndSortId(int productId, int sortId);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    ProductImage findProductImageById(int id);

    /**
     * 检查图片已存在
     * @param productId
     * @param galleryId
     * @return
     */
    ProductImage findProductImageByProductIdAndGalleryId(int productId, int galleryId);

    /**
     * 检查图片删除
     * @param productId
     * @return
     */
    List<ProductImage> findProductImageByProductId(int productId);

    /**
     * 根据图片ID查询
     * @param galleryId
     * @return
     */
    List<ProductImage> findByGalleryId(int galleryId);

}
