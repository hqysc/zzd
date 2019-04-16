package com.zzd.dao;

import com.zzd.entity.Years;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/15 0:45
 */
public interface YearsDao extends JpaRepository<Years, Integer> {

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    Years findYearsById(int id);

    List<Years> findAll();

    Page<Years> findAll(Pageable pageable);

}
