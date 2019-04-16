package com.zzd.service;

import com.zzd.entity.Cart;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/20 15:17
 */
public interface CartService {

    Cart findById(int id);

    List<Cart> findByUserId(int userId);

    /**
     * 添加到购物车
     * @param userId
     * @param productId
     */
    void save(int userId, int productId);

    void delete(int id, int userId);
}
