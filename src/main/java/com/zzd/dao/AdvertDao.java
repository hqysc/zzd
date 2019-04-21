package com.zzd.dao;

import com.zzd.entity.Advert;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/9 21:26
 */
public interface AdvertDao extends JpaRepository<Advert, Integer> {

    Advert findById(int id);

    Advert findAdvertById(int id);

    /**
     * 查询杂志广告
     * @param advertType
     * @return
     */
    Advert findByAdvertType(int advertType);

    /**
     * 查询全部首页广告 排序
     * @param advertType
     * @return
     */
    List<Advert> findAdvertByAdvertType(int advertType, Sort sort);

}
