package com.zzd.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 杂志类目类
 *
 * @Author: hqy
 * @Date: 2019/1/15 0:41
 */

@Entity
@Table(name = "product_category")
@Data
public class ProductCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 类目标题 */
    private String catename;

    /** 父类id */
    private Integer parentId;

    /**
     * 类目类型
     * 一级1 二级2
     */
    private Integer type;

    /** 是否首页推荐 */
    private Integer isIndex;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /**
     * 父类
     */
    @Transient
    private ProductCategory parentCategory;

    /**
     * 二级分类
     */
    @Transient
    private List<ProductCategory> categorySecList;

    /**
     * 杂志
     */
    @Transient
    private List<Product> product;

    private static final long serialVersionUID = 1L;

}
