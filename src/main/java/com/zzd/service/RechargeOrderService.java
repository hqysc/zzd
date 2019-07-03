package com.zzd.service;

import com.zzd.entity.RechargeOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: hqy
 * @Date: 2019/4/9 13:56
 */
public interface RechargeOrderService {

    RechargeOrder findById(int id);

    Page<RechargeOrder> findAll(Pageable pageable);

    Page<RechargeOrder> findByUserId(int userId, Pageable pageable);

    Page<RechargeOrder> findByUserName(String userName, Pageable pageable);
}
