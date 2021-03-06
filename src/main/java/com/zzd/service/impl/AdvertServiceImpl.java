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

    /**
     * 查询首页广告
     * @return
     */
    @Override
    public List<Advert> findByAdvertType(int advertType) {
        List<Advert> advertList = advertDao.findAdvertByAdvertType(advertType, Sort.by(Sort.Direction.ASC, "sortId"));
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
            dbAdvert.setAdvertBgColor(advert.getAdvertBgColor());
            dbAdvert.setUpdateTime(new Date());
            advertDao.saveAndFlush(dbAdvert);
        }else {
            advert.setAdvertType(0);
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

    /**
     * 设置广告类型
     * @param id
     * @param advertType
     */
    @Override
    public void setAdvertType(int id, int advertType) {
        Advert advert = advertDao.findById(id);
        if(advertType == 1) {   // 只允许有一个广告为杂志广告
            List<Advert> advertList = advertDao.findAll();
            for(Advert adverts : advertList) {
                adverts.setAdvertType(0);
                advertDao.saveAndFlush(adverts);
            }
        }
        advert.setAdvertType(advertType);
        advertDao.saveAndFlush(advert);
    }

    /** 获得图片 */
    public void getGallery(Advert advert) {
        if(advert.getGalleryId() != null) {
            Gallery gallery = galleryDao.findGalleryById(advert.getGalleryId());
            advert.setGallery(gallery);
        }
    }
}
