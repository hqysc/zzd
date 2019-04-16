package com.zzd.service;

import com.zzd.entity.ProductCategory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/1/15 1:14
 */
public interface ProductCategoryService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ProductCategory findById(int id);

    /**
     * 查询全部分类
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 按分类查询所有分类
     * @param type
     * @return
     */
    List<ProductCategory> findAll(int type);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<ProductCategory> findAll(Pageable pageable);

    /**
     * 分页查询 type
     * @param type
     * @param pageable
     * @return
     */
    Page<ProductCategory> findAll(int type, Pageable pageable);

    /**
     * 按条件查询
     * @param example
     * @return
     */
    List<ProductCategory> findALLExample(Example<ProductCategory> example);

    /**
     * 根据父类查询
     * @param parentId
     * @return
     */
    List<ProductCategory> findByParentId(int parentId);

    /**
     * 增加
     * @param productCategory
     */
    void saveOrUpdate(ProductCategory productCategory);

    /**
     * 删除
     * @param id
     */
    void delete(int id);

    /**
     * 设置首页推荐
     * 0是 1不是
     * @param id
     */
    void setIsIndex(int id, int isIndex);

    /**
     * 查询全部Id
     * @return
     */
    List<Integer> findCategoryIds();
}
