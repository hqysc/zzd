package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 图片库
 *
 * @Author: hqy
 * @Date: 2019/1/20 16:49
 */

@Entity
@Data
public class Gallery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称 (保留原文件名)
     */
    @Column
    private String imageName;

    /**
     * 文件路径
     */
    @Column
    private String imageSrc;

    /**
     * 缩略图路径
     */
    @Column
    private String thumbImageSrc;

    /**
     * 是否被使用
     * 0不用 1用
     */
    @Column
    private Integer imageStatus;

    /**
     * 图片类型
     * 1 杂志图片 2 广告图片
     */
    @Column
    private Integer imageType;

    /** 创建时间 */
    @Column
    private Date createTime;

}
