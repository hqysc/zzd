package com.zzd.service;

import com.zzd.entity.UserHelp;
import com.zzd.entity.UserHelpItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 用户帮助中心
 * @Author: hqy
 * @Date: 2019/4/8 22:49
 */

public interface UserHelpService {

    /**
     * 根据Id查询类目
     * @param id
     * @return
     */
    UserHelp findById(int id);

    /**
     * 查询全部类目 排序
     * @return
     */
    List<UserHelp> findAll();

    /**
     * 查询全部类目 分页
     * @param pageable
     * @return
     */
    Page<UserHelp> findAll(Pageable pageable);

    /**
     * 添加 & 修改类目
     * @param userHelp
     */
    void saveOrUpdate(UserHelp userHelp);

    /**
     * 删除 类目
     * @param id
     */
    void delete(int id);

    /**
     * 根据Id查询item
     * @param id
     * @return
     */
    UserHelpItem findItemById(int id);

    /**
     * 查询全部item 分页
     * @param pageable
     * @return
     */
    Page<UserHelpItem> findItemAll(Pageable pageable);

    /**
     * 查询此类目包含的item
     * @param helpId
     * @return
     */
    Page<UserHelpItem> findByHelpId(int helpId, Pageable pageable);

    /**
     * 添加 & 修改item
     * @param helpItem
     */
    void saveOrUpdateItem(UserHelpItem helpItem);

    /**
     * 删除item
     * @param id
     */
    void deleteItem(int id);
}
