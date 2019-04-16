package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 杂志描述 实体类
 *
 * @Author: hqy
 * @Date: 2019/4/7 20:35
 */

@Entity
@Table(name = "product_describe")
@Data
public class ProductDescribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    /** 描述名称 */
    @Column
    private String describeName;

    /** 描述内容 */
    @Column(columnDefinition = "varchar(1000) default null")
    private String content;

}
