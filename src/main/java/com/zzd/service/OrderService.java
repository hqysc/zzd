package com.zzd.service;

import com.zzd.entity.Order;
import com.zzd.entity.OrderItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/3/31 10:46
 */
public interface OrderService {

    /**
     * 查询全部 分页
     * @param pageable
     * @return
     */
    Page<Order> findAll(Pageable pageable);

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    List<Order> findByUserId(int userId);

    /**
     * 根据用户名查询 分页
     * @param userName
     * @return
     */
    Page<Order> findByUserName(String userName, Pageable pageable);

    /**
     * 提交到订单 直接支付下载
     */
    String payAndSave(HttpServletRequest request, HttpServletResponse response, Integer productId);

    /**
     * 提交到订单 购物车里支付
     */
    int payAndSaveInCart(HttpServletRequest request, HttpServletResponse response, int[] cartIds);

    /**
     * 查询订单详细内容
     * @param orderId
     * @param pageable
     * @return
     */
    Page<OrderItem> findOrderItemByOrderId(int orderId, Pageable pageable);

}
