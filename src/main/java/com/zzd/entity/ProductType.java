package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 杂志类型
 * @Author: hqy
 * @Date: 2019/2/16 16:26
 */

@Entity
@Data
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 年份名称 */
    private String typeName;

    /** 颜色 */
    private String tagColor;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

}
