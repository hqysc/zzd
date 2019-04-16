package com.zzd.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 年份
 * @Author: hqy
 * @Date: 2019/2/4 0:51
 */

@Entity
@Data
public class Years {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 年份名称 */
    private String yearsName;

    /** 颜色 */
    private String tagColor;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;


}
