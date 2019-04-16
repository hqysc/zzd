package com.zzd.service;

import com.zzd.entity.Years;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/15 0:47
 */
public interface YearsService {

    Years findById(int id);

    List<Years> findAll();

    Page<Years> findAll(Pageable pageable);

    void saveOrUpdate(Years years);

    void delete(int id);

    /**
     * 获取全部Id
     * @return
     */
    List<Integer> findYearsIds();

}
