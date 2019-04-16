package com.zzd.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String userName;

    /** 密码 */
    private String userPassword;

    /** 会员等级 */
    private Integer vipStatus;

    /** 书币 */
    private Double coin;

    /** 邮箱 */
    private String email;

    /** 电话号码 */
    private String phoneNumber;

    /** 注册时间 */
    private Date registerTime;
}
