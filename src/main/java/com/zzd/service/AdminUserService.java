package com.zzd.service;

import com.zzd.entity.AdminUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/1/14 19:58
 */
public interface AdminUserService  {

    /**
     * id查询
     * @param id
     * @return
     */
    AdminUser findById(int id);

    /**
     * 用户名查询
     * @param username
     * @return
     */
    List<AdminUser> findByUsername(String username);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<AdminUser> findAll(Pageable pageable);

    /**
     * 检查登录
     * @param username
     * @param password
     * @return
     */
    AdminUser checkLogin(String username, String password);

}
