package com.zzd.dao;

import com.zzd.entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/9 21:26
 */
public interface AdvertDao extends JpaRepository<Advert, Integer> {

    Advert findById(int id);

    Advert findAdvertById(int id);

    Advert findByAdvertType(int advertType);

}
