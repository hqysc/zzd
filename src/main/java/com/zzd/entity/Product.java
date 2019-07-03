package com.zzd.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 杂志实体类
 *
 * @Author: hqy
 * @Date: 2019/1/18 14:41
 */

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /** 标题名称 */
    private String productName;

    /** 所属类目 */
    private Integer categoryId;

    /** 所属年份 */
    private Integer yearsId;

    /** 所属国家 语言 */
    private Integer countryId;

    /**
     * 所属类型
     * 对应productType
     */
    private Integer typeId;

    /** 杂志 本数 */
    private Integer num;

    /** 文件大小 小数 */
    private Double fileSize;

    /** 价格 */
    private Double price;

    /**
     * 是否热门 推荐
     * 0否 1热门 2推荐
     */
    private Integer isHot;

    /**
     * 杂志状态
     * 0下架 默认1上架
     */
    private Integer productStatus;

    /** 是否免费 */
    private Integer isFree;

    /**
     * 杂志标签
     * 0 default 1 new
     */
    @Column
    private Integer tagStatus;

    /** 详情描述Id */
    @Column
    private Integer describeId;

    /** 百度云链接 */
    private String baiduyunUrl;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    @Transient
    private ProductCategory category;

    @Transient
    private List<ProductImage> productImage;

    /** 封面图 */
    @Transient
    private Gallery gallery;

    @Transient
    private Years years;

    @Transient
    private Country country;

    @Transient
    private ProductType type;

    @Transient
    private ProductDescribe describe;
}
