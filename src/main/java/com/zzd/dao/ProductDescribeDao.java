package com.zzd.dao;

import com.zzd.entity.ProductDescribe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: hqy
 * @Date: 2019/4/8 15:22
 */
public interface ProductDescribeDao extends JpaRepository<ProductDescribe, Integer> {

    ProductDescribe findProductDescribeById(int id);

}
