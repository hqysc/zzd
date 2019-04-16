package com.zzd.service.impl;

import com.zzd.dao.UserHelpDao;
import com.zzd.dao.UserHelpItemDao;
import com.zzd.entity.UserHelp;
import com.zzd.entity.UserHelpItem;
import com.zzd.service.UserHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/8 23:35
 */

@Service
public class UserHelpServiceImpl implements UserHelpService {

    @Autowired
    private UserHelpDao userHelpDao;
    @Autowired
    private UserHelpItemDao userHelpItemDao;

    /**
     * 查询类目 Id
     * @param id
     * @return
     */
    @Override
    public UserHelp findById(int id) {
        return userHelpDao.findById(id);
    }

    /**
     * 查询全部类目 列表
     * 排序 sortId
     * @return
     */
    @Override
    public List<UserHelp> findAll() {
        List<UserHelp> userHelpList = userHelpDao.findAll(Sort.by(Sort.Direction.ASC, "sortId"));
        for(UserHelp userHelp : userHelpList) {
            getUserHelpItem(userHelp);
        }
        return userHelpList;
    }

    /**
     * 查询全部类目 分页
     * @param pageable
     * @return
     */
    @Override
    public Page<UserHelp> findAll(Pageable pageable) {
        return userHelpDao.findAll(pageable);
    }

    /**
     * 添加 修改 条目
     * @param userHelp
     */
    @Override
    public void saveOrUpdate(UserHelp userHelp) {
        if(userHelp.getId() != null) {
            UserHelp dbUserHelp = userHelpDao.findUserHelpById(userHelp.getId());
            dbUserHelp.setHelpTitle(userHelp.getHelpTitle());
            dbUserHelp.setSortId(userHelp.getSortId());
            dbUserHelp.setUpdateTime(new Date());
            userHelpDao.saveAndFlush(dbUserHelp);
        }else {
            userHelp.setCreateTime(new Date());
            userHelp.setUpdateTime(new Date());
            userHelpDao.save(userHelp);
        }
    }

    /**
     * 删除条目
     * @param id
     */
    @Override
    public void delete(int id) {
        userHelpDao.deleteById(id);
    }

    /**
     * 根据id查询 item
     * @param id
     * @return
     */
    @Override
    public UserHelpItem findItemById(int id) {
        return userHelpItemDao.findById(id);
    }

    /**
     * 查询全部item 分页
     * @param pageable
     * @return
     */
    @Override
    public Page<UserHelpItem> findItemAll(Pageable pageable) {
        Page<UserHelpItem> helpItemList = userHelpItemDao.findAll(pageable);
        for(UserHelpItem helpItem : helpItemList) {
            getUserHelp(helpItem);  // get help
        }
        return helpItemList;
    }

    /**
     * 查询此类目包含的item
     * @param helpId
     * @return
     */
    @Override
    public Page<UserHelpItem> findByHelpId(int helpId, Pageable pageable) {
        Page<UserHelpItem> helpItemList = userHelpItemDao.findByUserHelpId(helpId, pageable);
        for(UserHelpItem helpItem : helpItemList) {
            getUserHelp(helpItem);  // get help
        }
        return helpItemList;
    }

    /**
     * 添加 修改 Item
     * @param helpItem
     */
    @Override
    public void saveOrUpdateItem(UserHelpItem helpItem) {
        if(helpItem.getId() != null) {
            UserHelpItem dbHelpItem = userHelpItemDao.findUserHelpItemById(helpItem.getId());
            dbHelpItem.setItemName(helpItem.getItemName());
            dbHelpItem.setSortId(helpItem.getSortId());
            dbHelpItem.setItemContent(helpItem.getItemContent());
            dbHelpItem.setUserHelpId(helpItem.getUserHelpId());
            dbHelpItem.setUpdateTime(new Date());
            userHelpItemDao.saveAndFlush(dbHelpItem);
        }else {
            helpItem.setUpdateTime(new Date());
            helpItem.setCreateTime(new Date());
            userHelpItemDao.save(helpItem);
        }
    }

    /**
     * 删除 item
     * @param id
     */
    @Override
    public void deleteItem(int id) {
        userHelpItemDao.deleteById(id);
    }

    /**
     * 获取userHelp
     * @param helpItem
     */
    public void getUserHelp(UserHelpItem helpItem) {
        if(helpItem.getUserHelpId() != null) {
            UserHelp userHelp = userHelpDao.findUserHelpById(helpItem.getUserHelpId());
            helpItem.setUserHelp(userHelp);
        }
    }

    /**
     * 获取userHelpItem
     * @param userHelp
     */
    public void getUserHelpItem(UserHelp userHelp) {
        List<UserHelpItem> helpList = userHelpItemDao.findByUserHelpId(userHelp.getId(), Sort.by(Sort.Direction.ASC, "sortId"));
        if(helpList != null || helpList.isEmpty()) {
            userHelp.setHelpItemList(helpList);
        }
    }
}
