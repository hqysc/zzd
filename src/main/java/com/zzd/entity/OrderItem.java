package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单条目 实体类
 *
 * @Author: hqy
 * @Date: 2019/3/27 9:14
 */

@Entity
@Table(name = "user_order_item")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    /** 订单id */
    @Column
    private Integer orderId;

    /** 杂志id */
    @Column
    private Integer productId;

    /** 价格 */
    @Column
    private Double price;

    @Transient
    private Product product;

}
