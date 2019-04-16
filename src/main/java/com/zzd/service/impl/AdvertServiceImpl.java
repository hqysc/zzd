package com.zzd.service.impl;

import com.zzd.dao.AdvertDao;
import com.zzd.dao.GalleryDao;
import com.zzd.entity.Advert;
import com.zzd.entity.Gallery;
import com.zzd.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/9 21:35
 */
@Service
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    private AdvertDao advertDao;
    @Autowired
    private GalleryDao galleryDao;

    @Override
    public Advert findById(int id) {
        Advert advert = advertDao.findById(id);
        getGallery(advert);
        return advert;
    }

    @Override
    public List<Advert> findAll() {
        List<Advert> advertList = advertDao.findAll(Sort.by(Sort.Direction.ASC, "sortId"));
        for(Advert advert : advertList) {
            getGallery(advert);
        }
        return advertList;
    }

    @Override
    public Page<Advert> findAll(Pageable pageable) {
        Page<Advert> advertList = advertDao.findAll(pageable);
        for(Advert advert : advertList) {
            getGallery(advert);
        }
        return advertList;
    }

    @Override
    public void saveOrUpdate(Advert advert) {
        if(advert.getId() != null) {
            Advert dbAdvert = advertDao.findAdvertById(advert.getId());
            dbAdvert.setSortId(advert.getSortId());
            dbAdvert.setAdvertUrl(advert.getAdvertUrl());
            dbAdvert.setUpdateTime(new Date());
            advertDao.saveAndFlush(dbAdvert);
        }else {
            advert.setCreateTime(new Date());
            advert.setUpdateTime(new Date());
            advertDao.save(advert);
        }
    }

    /**
     * 设置图片
     * @param id
     * @param galleryId
     */
    @Override
    public void grantGallery(int id, int galleryId) {
        Advert advert = advertDao.findById(id);
        advert.setGalleryId(galleryId);
        advertDao.saveAndFlush(advert);
    }

    @Override
    public void delete(int id) {
        advertDao.deleteById(id);
    }

    /** 获得图片 */
    public void getGallery(Advert advert) {
        if(advert.getGalleryId() != null) {
            Gallery gallery = galleryDao.findGalleryById(advert.getGalleryId());
            advert.setGallery(gallery);
        }
    }
}
