package com.zzd.service;

import com.zzd.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/19 21:15
 */

public interface UserService {

    User findById(int id);

    Page<User> findAll(Pageable pageable);

    Page<User> findByUserName(String userName, Pageable pageable);

    void register(User user);

    void saveOrUpdate(User user);

    void delete(int id);

    /**
     * 充值
     * @param id
     */
    void recharge(int id, Double addCoin);

    /**
     * 修改vip
     * @param id
     * @param vipStatus
     */
    void editVip(int id, int vipStatus);

    User checkLogin(String username, String password);

    /**
     * 检查用户名是否重复
     * @return
     */
    List<User> checkUserName(String username);

}
