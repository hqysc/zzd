package com.zzd.service.impl;

import ch.qos.logback.core.LogbackException;
import com.zzd.dao.AdminUserDao;
import com.zzd.entity.AdminUser;
import com.zzd.service.AdminUserService;
import com.zzd.service.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/1/14 20:04
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser findById(int id) {
        return adminUserDao.getOne(id);
    }

    @Override
    public List<AdminUser> findByUsername(String username) {
        return adminUserDao.findByUserName(username);
    }

    @Override
    public Page<AdminUser> findAll(Pageable pageable) {
        return adminUserDao.findAll(pageable);
    }

    @Override
    public AdminUser checkLogin(String username, String password) {
        return adminUserDao.findByUserNameAndPassword(username, password);
    }

}
