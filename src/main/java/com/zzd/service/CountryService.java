package com.zzd.service;

import com.zzd.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/15 2:16
 */
public interface CountryService {

    Country findById(int id);

    List<Country> findAll();

    Page<Country> findAll(Pageable pageable);

    void saveOrUpdate(Country country);

    void delete(int id);

    /**
     * 查询全部Id
     * @return
     */
    List<Integer> findCountryIds();

}
