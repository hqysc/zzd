package com.zzd.service;

import com.zzd.entity.Product;
import com.zzd.entity.ProductDescribe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 杂志描述 Service
 *
 * @Author: hqy
 * @Date: 2019/4/8 15:23
 */
public interface ProductDescribeService {

    ProductDescribe findById(int id);

    List<ProductDescribe> findAll();

    Page<ProductDescribe> findAll(Pageable pageable);

    /**
     * 根据杂志描述检查
     * @param typeId
     * @return
     */
    List<Product> checkByDescribeId(int typeId);

    void saveOrUpdate(ProductDescribe describe);

    void delete(int id);

}
