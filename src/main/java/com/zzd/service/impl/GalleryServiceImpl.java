package com.zzd.service.impl;

import com.zzd.dao.GalleryDao;
import com.zzd.entity.Gallery;
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

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Gallery findById(int id) {
        return galleryDao.findById(id);
    }

    @Override
    public List<Gallery> findAll() {
        return galleryDao.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
    }

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
     * 创建
     * @param gallery
     * @return
     */
    @Override
    public int create(Gallery gallery) {
        return galleryDao.save(gallery).getId();
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
