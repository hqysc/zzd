package com.zzd.service.impl;

import com.zzd.dao.ProductTypeDao;
import com.zzd.entity.ProductType;
import com.zzd.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/16 16:31
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public ProductType findById(int id) {
        return productTypeDao.findProductTypeById(id);
    }

    @Override
    public List<ProductType> findAll() {
        return productTypeDao.findAll();
    }

    @Override
    public Page<ProductType> findAll(Pageable pageable) {
        return productTypeDao.findAll(pageable);
    }

    @Override
    public void saveOrUpdate(ProductType productType) {
        if(productType.getId() != null) {
            ProductType dbProductType = productTypeDao.findProductTypeById(productType.getId());
            dbProductType.setTypeName(productType.getTypeName());
            dbProductType.setTagColor(productType.getTagColor());
            dbProductType.setUpdateTime(new Date());
            productTypeDao.saveAndFlush(dbProductType);
        }else {
            productType.setCreateTime(new Date());
            productType.setUpdateTime(new Date());
            productTypeDao.save(productType);
        }
    }

    @Override
    public void delete(int id) {
        productTypeDao.deleteById(id);
    }

    @Override
    public List<Integer> findTypeIds() {
        List<ProductType> typeList = productTypeDao.findAll();
        List<Integer> typeIds = new ArrayList<>();
        for(ProductType type : typeList) {
            typeIds.add(type.getId());
        }
        return typeIds;
    }

}
