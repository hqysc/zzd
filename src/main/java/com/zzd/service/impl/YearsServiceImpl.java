package com.zzd.service.impl;

import com.zzd.dao.YearsDao;
import com.zzd.entity.Years;
import com.zzd.service.YearsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/15 0:51
 */
@Service
public class YearsServiceImpl implements YearsService {

    @Autowired
    private YearsDao yearsDao;

    @Override
    public Years findById(int id) {
        return yearsDao.findYearsById(id);
    }

    @Override
    public List<Years> findAll() {
        return yearsDao.findAll();
    }

    @Override
    public Page<Years> findAll(Pageable pageable) {
        return yearsDao.findAll(pageable);
    }

    @Override
    public void saveOrUpdate(Years years) {
        if(years.getId() != null) {
            Years dbYears = yearsDao.findYearsById(years.getId());
            dbYears.setYearsName(years.getYearsName());
            dbYears.setTagColor(years.getTagColor());
            dbYears.setUpdateTime(new Date());
            yearsDao.saveAndFlush(dbYears);
        }else {
            years.setCreateTime(new Date());
            years.setUpdateTime(new Date());
            yearsDao.save(years);
        }
    }

    @Override
    public void delete(int id) {
        yearsDao.deleteById(id);
    }

    /**
     * 获取Id
     * @return
     */
    @Override
    public List<Integer> findYearsIds() {
        List<Years> yearsList = yearsDao.findAll();
        List<Integer> yearsIds = new ArrayList<>();
        for(Years years : yearsList) {
            yearsIds.add(years.getId());
        }
        return yearsIds;
    }
}
