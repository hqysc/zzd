package com.zzd.dao;

import com.zzd.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单条目 dao
 *
 * @Author: hqy
 * @Date: 2019/3/31 18:23
 */
public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {

    /**
     * 根据订单id 查询
     * @param orderId
     * @return
     */
    Page<OrderItem> findByOrderId(int orderId, Pageable pageable);

    /**
     * 根据订单id 查询
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(int orderId);

}
