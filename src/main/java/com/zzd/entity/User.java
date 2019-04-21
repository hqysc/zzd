package com.zzd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: hqy
 * @Date: 2019/2/19 20:26
 */

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 用户名 */
    @Column
    private String userName;

    /** 密码 */
    @Column
    private String userPassword;

    /** 会员等级 */
    @Column
    private Integer vipStatus;

    /** 书币 */
    @Column
    private Double coin;

    /** 邮箱 */
    @Column
    private String userEmail;

    /** 电话号码 */
    @Column
    private String phoneNumber;

    /** 激活状态 */
    @Column
    private Integer userStatus;

    /** 激活码 */
    @Column
    private String code;

    /** 注册时间 */
    @Column
    private Date registerTime;
}
