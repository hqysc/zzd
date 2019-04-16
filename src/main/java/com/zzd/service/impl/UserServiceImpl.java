package com.zzd.service.impl;

import com.zzd.dao.RechargeOrderDao;
import com.zzd.dao.UserDao;
import com.zzd.entity.RechargeOrder;
import com.zzd.entity.User;
import com.zzd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/19 21:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RechargeOrderDao rechargeOrderDao;

    @Override
    public User findById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public Page<User> findByUserName(String userName, Pageable pageable) {
        return userDao.findByUserNameContaining(userName, pageable);
    }

    @Override
    public void register(User user) {
        userDao.save(user);
    }

    @Override
    public void saveOrUpdate(User user) {

    }

    @Override
    public void delete(int id) {
        userDao.deleteById(id);
    }

    @Override
    public void recharge(int id, Double addCoin) {
        User user = userDao.findUserById(id);
        user.setCoin(user.getCoin() + addCoin);
        userDao.saveAndFlush(user);
        // Save rechargeOrder
        RechargeOrder rechargeOrder = new RechargeOrder();
        rechargeOrder.setUserId(id);
        rechargeOrder.setAmount(addCoin);
        rechargeOrder.setOrderTime(new Date());
        rechargeOrderDao.save(rechargeOrder);
    }

    @Override
    public void editVip(int id, int vipStatus) {
        User user = userDao.findUserById(id);
        user.setVipStatus(vipStatus);
        userDao.saveAndFlush(user);
    }

    @Override
    public User checkLogin(String username, String password) {
        return userDao.findByUserNameAndAndUserPassword(username, password);
    }

    @Override
    public List<User> checkUserName(String username) {
        return userDao.findByUserName(username);
    }

}
