package com.zzd.service;

import com.zzd.entity.Gallery;
import com.zzd.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 图库接口类
 *
 * @Author: hqy
 * @Date: 2019/1/21 21:52
 */
public interface GalleryService {

    Gallery findById(int id);

    List<Gallery> findAll();

    List<Gallery> findByType(int imageType);

    Page<Gallery> findAll(Pageable pageable);

    int create(Gallery gallery);

    List<Gallery> findByImageName(String keyword);

    List<Gallery> findByImageNameContaining(String keyword);

}


