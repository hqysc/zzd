package com.zzd.dao;

import com.zzd.entity.RechargeOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户充值订单Dao
 *
 * @Author: hqy
 * @Date: 2019/4/9 13:53
 */
public interface RechargeOrderDao extends JpaRepository<RechargeOrder, Integer> {

    RechargeOrder findById(int id);

    Page<RechargeOrder> findByUserIdIn(List<Integer> userIds, Pageable pageable);

    Page<RechargeOrder> findByUserId(int userId, Pageable pageable);
}
