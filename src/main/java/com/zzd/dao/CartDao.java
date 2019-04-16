package com.zzd.dao;

import com.zzd.entity.Cart;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/20 14:57
 */

public interface CartDao extends JpaRepository<Cart, Integer> {

    Cart findCartById(int id);

    /**
     * 根据用户查询
     * @param userId
     * @return
     */
    List<Cart> findByUserId(int userId);

    /**
     * 根据用户查询 排序
     * @param userId
     * @return
     */
    List<Cart> findByUserId(int userId, Sort sort);

    List<Cart> findCartByUserIdAndProductId(int userId, int productId);

}
