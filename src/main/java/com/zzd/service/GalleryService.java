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

    /** 根据ID查询 */
    Gallery findById(int id);

    /** 查询全部 */
    List<Gallery> findAll();

    /** 分页查询 */
    Page<Gallery> findAll(Pageable pageable);

    /** 模糊查询 */
    Page<Gallery> findByImageNameContaining(String keyword, Pageable pageable);

    /** 根据类型查询 */
    List<Gallery> findByType(int imageType);

    /** 名称查询 */
    List<Gallery> findByImageName(String keyword);

    /** 名称查询 模糊 */
    List<Gallery> findByImageNameContaining(String keyword);

    /** 创建 */
    int create(Gallery gallery);

    /** 删除 */
    void delete(int id);

}


