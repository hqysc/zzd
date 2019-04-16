package com.zzd.dao;

import com.zzd.entity.UserHelp;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户帮助中心类目 Dao
 *
 * @Author: hqy
 * @Date: 2019/4/8 22:45
 */

public interface UserHelpDao extends JpaRepository<UserHelp, Integer> {

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    UserHelp findById(int id);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    UserHelp findUserHelpById(int id);

}
