package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品图片实体类
 * @Author: hqy
 * @Date: 2019/2/1 21:55
 */

@Entity
@Data
public class ProductImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 杂志Id */
    private Integer productId;

    /** 图库Id */
    private Integer galleryId;

    /** 排序编号 */
    private Integer sortId;

    @Transient
    private Gallery gallery;

}
