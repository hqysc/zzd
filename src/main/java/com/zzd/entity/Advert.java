package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 广告 实体类
 *
 * @Author: hqy
 * @Date: 2019/4/9 19:56
 */
@Entity
@Table(name = "advert")
@Data
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    /** 图库id */
    @Column
    private Integer galleryId;

    /** 排序编号 */
    @Column
    private Integer sortId;

    /** 广告类型 0首页广告 1杂志广告 */
    @Column
    private Integer advertType;

    /** 广告链接 */
    @Column
    private String advertUrl;

    /** 广告背景颜色 */
    @Column
    private String advertBgColor;

    /** 创建时间 */
    @Column
    private Date createTime;

    /** 更新时间 */
    @Column
    private Date updateTime;

    @Transient
    private Gallery gallery;

}
