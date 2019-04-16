package com.zzd.dao;

import com.zzd.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/1/15 1:03
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ProductCategory findProductCategoryById(int id);

    /**
     * 根据类目分级查询
     * @param type
     * @return
     */
    List<ProductCategory> findByType(int type);

    /**
     * 分页查询全部
     * @param pageable
     * @return
     */
    Page<ProductCategory> findAll(Pageable pageable);

    /**
     * 分页查询
     * 根据type
     * @param type
     * @param pageable
     * @return
     */
    Page<ProductCategory> findProductCategoryByType(int type, Pageable pageable);

    /**
     * 根据父级类查询
     * @param parentId
     * @return
     */
    List<ProductCategory> findByParentId(int parentId);

    /**
     * 查询推荐
     * @param isIndex
     * @return
     */
    List<ProductCategory> findByIsIndex(int isIndex);

}
