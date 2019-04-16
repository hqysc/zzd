package com.zzd.service.impl;

import com.zzd.dao.CountryDao;
import com.zzd.entity.Country;
import com.zzd.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/15 2:17
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;


    @Override
    public Country findById(int id) {
        return countryDao.findCountryById(id);
    }

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    public Page<Country> findAll(Pageable pageable) {
        return countryDao.findAll(pageable);
    }

    @Override
    public void saveOrUpdate(Country country) {
        if(country.getId() != null) {
            Country dbCountry = countryDao.findCountryById(country.getId());
            dbCountry.setCountryName(country.getCountryName());
            dbCountry.setTagColor(country.getTagColor());
            dbCountry.setUpdateTime(new Date());
            countryDao.saveAndFlush(dbCountry);
        }else {
            country.setCreateTime(new Date());
            country.setUpdateTime(new Date());
            countryDao.save(country);
        }
    }

    @Override
    public void delete(int id) {
        countryDao.deleteById(id);
    }

    @Override
    public List<Integer> findCountryIds() {
        List<Country> countryList = countryDao.findAll();
        List<Integer> countryIds = new ArrayList<>();
        for(Country country : countryList) {
            countryIds.add(country.getId());
        }
        return countryIds;
    }
}
