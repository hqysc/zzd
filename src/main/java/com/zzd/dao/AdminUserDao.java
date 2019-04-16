package com.zzd.dao;

import com.zzd.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @Author: hqy
 * @Date: 2019/1/14 16:58
 */
public interface AdminUserDao extends JpaRepository<AdminUser,Integer> {

    AdminUser findByUserNameAndPassword(String userName, String password);

    List<AdminUser> findByUserName(String userName);

}
