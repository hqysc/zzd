package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 *
 * @Author: hqy
 * @Date: 2019/3/31 9:45
 */
@Entity
@Table(name = "user_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    /** 用户ID */
    @Column
    private Integer userId;

    /** 共计数量 */
    @Column
    private Integer itemTotal;

    /** 总价格 */
    @Column
    private Double priceTotal;

    /** 订单创建时间 */
    @Column
    private Date orderTime;

    @Transient
    private List<OrderItem> orderItemList;

    @Transient
    private User user;

}
