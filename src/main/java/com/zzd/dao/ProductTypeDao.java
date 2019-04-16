package com.zzd.dao;

import com.zzd.entity.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/16 16:28
 */
public interface ProductTypeDao extends JpaRepository<ProductType, Integer> {

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    ProductType findProductTypeById(int id);

    List<ProductType> findAll();

    Page<ProductType> findAll(Pageable pageable);

}
