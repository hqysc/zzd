package com.zzd.dao;

import com.zzd.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/3/31 10:43
 */
public interface OrderDao extends JpaRepository<Order, Integer> {

    /**
     * 根据Id查找订单
     * @param id
     * @return
     */
    Order findOrderById(Integer id);

    /**
     * 根据用户查找订单
     * @param userId
     * @return
     */
    List<Order> findByUserId(Integer userId, Sort sort);

    /**
     * 根据用户查找订单 分页
     * @param userIds
     * @param pageable
     * @return
     */
    Page<Order> findByUserIdIn(List<Integer> userIds, Pageable pageable);

}
