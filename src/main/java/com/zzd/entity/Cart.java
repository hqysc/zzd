package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 我的书架 购物车
 * @Author: hqy
 * @Date: 2019/2/20 14:28
 */

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 用户Id */
    private Integer userId;

    /** 杂志Id */
    private Integer productId;

    /** 创建时间 */
    private Date createTime;

    @Transient
    private Product product;
}
