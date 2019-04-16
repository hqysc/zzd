package com.zzd.service;

import com.zzd.entity.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/16 16:30
 */
public interface ProductTypeService {

    ProductType findById(int id);

    List<ProductType> findAll();

    Page<ProductType> findAll(Pageable pageable);

    void saveOrUpdate(ProductType productType);

    void delete(int id);

    /**
     * 查询Id
     * @return
     */
    List<Integer> findTypeIds();

}
