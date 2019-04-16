package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户充值订单记录
 *
 * @Author: hqy
 * @Date: 2019/4/9 13:28
 */

@Entity
@Table(name = "recharge_order")
@Data
public class RechargeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 充值金额 */
    @Column
    private Double amount;

    /** 用户Id */
    @Column
    private Integer userId;

    /** 日期 */
    @Column
    private Date orderTime;

    @Transient
    private User user;
}
