package com.zzd.service;

import com.zzd.entity.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/9 21:27
 */
public interface AdvertService {

    Advert findById(int id);

    List<Advert> findAll();

    Page<Advert> findAll(Pageable pageable);

    /**
     * 根据类型查询广告
     * @return
     */
    List<Advert> findByAdvertType(int advertType);

    void saveOrUpdate(Advert advert);

    void grantGallery(int id, int galleryId);

    void delete(int id);

    void setAdvertType(int id, int advertType);
}
