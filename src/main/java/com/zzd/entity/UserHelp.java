package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 用户帮助中心类目 实体类
 *
 * @Author: hqy
 * @Date: 2019/4/8 22:31
 */

@Entity
@Table(name = "user_help")
@Data
public class UserHelp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 标题 */
    private String helpTitle;

    /** 排序 */
    private Integer sortId;

    /** 创建时间 */
    @Column
    private Date createTime;

    /** 更新时间 */
    @Column
    private Date updateTime;

    @Transient
    private List<UserHelpItem> helpItemList;
}
