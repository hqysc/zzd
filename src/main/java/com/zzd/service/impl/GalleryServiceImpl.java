package com.zzd.service.impl;

import com.zzd.dao.GalleryDao;
import com.zzd.dao.ProductImageDao;
import com.zzd.entity.Gallery;
import com.zzd.entity.Product;
import com.zzd.entity.ProductImage;
import com.zzd.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/1/21 21:57
 */
@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryDao galleryDao;
    @Autowired
    private ProductImageDao productImageDao;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Gallery findById(int id) {
        return galleryDao.findById(id);
    }

    /**
     * 列表查询
     * @return
     */
    @Override
    public List<Gallery> findAll() {
        return galleryDao.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
    }

    /**
     * 列表查询 图片类型
     * @param imageType
     * @return
     */
    @Override
    public List<Gallery> findByType(int imageType) {
        return galleryDao.findByImageType(imageType);
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Override
    public Page<Gallery> findAll(Pageable pageable) {
        return galleryDao.findAll(pageable);
    }

    /**
     * 模糊查询 分页
     * @param keyword
     * @param pageable
     * @return
     */
    @Override
    public Page<Gallery> findByImageNameContaining(String keyword, Pageable pageable) {
        return galleryDao.findByImageNameContaining(keyword, pageable);
    }

    /**
     * 创建
     * @param gallery
     * @return
     */
    @Override
    public int create(Gallery gallery) {
        return galleryDao.save(gallery).getId();
    }

    /**
     * 删除 关联productImage
     * @param id
     */
    @Override
    public void delete(int id) {
        galleryDao.deleteById(id);  // delete gallery
        List<ProductImage> productImageList = productImageDao.findByGalleryId(id);
        for(ProductImage productImage : productImageList) {
            productImageDao.deleteById(productImage.getId());   // delete productImage
        }
    }

    @Override
    public List<Gallery> findByImageName(String keyword) {
        return galleryDao.findByImageName(keyword);
    }

    /**
     * 模糊搜索
     * @param keyword
     * @return
     */
    @Override
    public List<Gallery> findByImageNameContaining(String keyword) {
        return galleryDao.findByImageNameContaining(keyword);
    }

}
