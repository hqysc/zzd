package com.zzd.dao;

import com.zzd.entity.UserHelpItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户帮助中心内容 Dao
 *
 * @Author: hqy
 * @Date: 2019/4/8 22:47
 */
public interface UserHelpItemDao extends JpaRepository<UserHelpItem, Integer> {

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    UserHelpItem findById(int id);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    UserHelpItem findUserHelpItemById(int id);

    /**
     * 查询此类目下所有item 排序
     * @param helpId
     * @return
     */
    List<UserHelpItem> findByUserHelpId(int helpId, Sort sort);

    /**
     * 查询此类目下所有item 分页
     * @param helpId
     * @return
     */
    Page<UserHelpItem> findByUserHelpId(int helpId, Pageable pageable);

}
