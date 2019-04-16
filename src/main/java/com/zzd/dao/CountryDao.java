package com.zzd.dao;

import com.zzd.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/15 2:14
 */
public interface CountryDao extends JpaRepository<Country, Integer> {

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    Country findCountryById(int id);

    List<Country> findAll();

    Page<Country> findAll(Pageable pageable);
}
