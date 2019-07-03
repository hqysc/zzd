package com.zzd.service.impl;

import com.zzd.dao.RechargeOrderDao;
import com.zzd.dao.UserDao;
import com.zzd.entity.RechargeOrder;
import com.zzd.entity.User;
import com.zzd.service.RechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/9 13:57
 */
@Service
public class RechargeOrderServiceImpl implements RechargeOrderService {

    @Autowired
    private RechargeOrderDao rechargeOrderDao;
    @Autowired
    private UserDao userDao;

    @Override
    public RechargeOrder findById(int id) {
        return rechargeOrderDao.findById(id);
    }

    @Override
    public Page<RechargeOrder> findAll(Pageable pageable) {
        Page<RechargeOrder> orderList = rechargeOrderDao.findAll(pageable);
        for(RechargeOrder order : orderList) {
            getUser(order);
        }
        return orderList;
    }

    @Override
    public Page<RechargeOrder> findByUserId(int userId, Pageable pageable) {
        Page<RechargeOrder> orderList = rechargeOrderDao.findByUserId(userId, pageable);
        for(RechargeOrder order : orderList) {
            getUser(order);
        }
        return orderList;
    }

    @Override
    public Page<RechargeOrder> findByUserName(String userName, Pageable pageable) {
        List<Integer> userIds = new ArrayList<>();
        List<User> userList = userDao.findByUserNameContaining(userName);
        for(User user : userList) {
            userIds.add(user.getId());
        }
        if(userIds != null || userIds.isEmpty()) {
            Page<RechargeOrder> orderList = rechargeOrderDao.findByUserIdIn(userIds, pageable);
            for(RechargeOrder order : orderList) {
                getUser(order); // 获取用户信息
            }
            return orderList;
        }else {
            return null;
        }
    }

    /**
     * 获取用户信息
     * @param order
     */
    public void getUser(RechargeOrder order) {
        User user = userDao.findUserById(order.getUserId());
        order.setUser(user);
    }
}
