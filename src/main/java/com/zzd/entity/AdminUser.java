package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 管理员
 * 后面整合shiro 保证安全
 *
 * @Author: hqy
 * @Date: 2019/1/14 16:41
 */

@Entity
@Table(name = "admin_user")
@Data
public class AdminUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码 未加密
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 逻辑状态
     * 删除 锁定 正常
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
