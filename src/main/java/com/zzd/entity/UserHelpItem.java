package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户帮助中心内容 实体类
 *
 * @Author: hqy
 * @Date: 2019/4/8 22:34
 */

@Entity
@Table(name = "user_help_item")
@Data
public class UserHelpItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 名称 */
    @Column
    private String itemName;

    /** 所属类目Id */
    @Column
    private Integer userHelpId;

    /** 页面内容 */
    @Column(columnDefinition = "varchar(20000) default null")
    private String itemContent;

    /** 序号 */
    @Column
    private Integer sortId;

    /** 创建时间 */
    @Column
    private Date createTime;

    /** 更新时间 */
    @Column
    private Date updateTime;

    @Transient
    private UserHelp userHelp;

}
