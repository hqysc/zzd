package com.zzd.service.impl;

import com.zzd.dao.ProductDao;
import com.zzd.dao.ProductDescribeDao;
import com.zzd.entity.Product;
import com.zzd.entity.ProductDescribe;
import com.zzd.service.ProductDescribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/8 15:26
 */
@Service
public class ProductDescribeServiceImpl implements ProductDescribeService {

    @Autowired
    private ProductDescribeDao productDescribeDao;
    @Autowired
    private ProductDao productDao;

    /**
     * 根据Id查找
     * @param id
     * @return
     */
    @Override
    public ProductDescribe findById(int id) {
        return productDescribeDao.findProductDescribeById(id);
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<ProductDescribe> findAll() {
        return productDescribeDao.findAll();
    }

    /**
     * 查询全部 分页
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductDescribe> findAll(Pageable pageable) {
        return productDescribeDao.findAll(pageable);
    }

    /**
     * 查询描述下是否含有杂志
     * @param describeId
     * @return
     */
    @Override
    public List<Product> checkByDescribeId(int describeId) {
        return productDao.findByDescribeId(describeId);
    }

    @Override
    public void saveOrUpdate(ProductDescribe describe) {
        if(describe.getId() != null) {
            ProductDescribe dbDescribe = productDescribeDao.findProductDescribeById(describe.getId());
            dbDescribe.setDescribeName(describe.getDescribeName());
            dbDescribe.setContent(describe.getContent());
            productDescribeDao.saveAndFlush(dbDescribe);
        }else {
            productDescribeDao.save(describe);
        }
    }

    @Override
    public void delete(int id) {
        productDescribeDao.deleteById(id);
    }
}
