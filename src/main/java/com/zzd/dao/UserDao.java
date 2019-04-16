package com.zzd.dao;

import com.zzd.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/19 21:05
 */
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * 根据用户Id查询
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 根据用户名查询 模糊查询
     * @param userName
     * @return
     */
    List<User> findByUserNameContaining(String userName);

    /**
     * 根据用户名查询 模糊查询 分页
     * @param userName
     * @param pageable
     * @return
     */
    Page<User> findByUserNameContaining(String userName, Pageable pageable);

    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    List<User> findByUserName(String userName);

    /**
     * 根据用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    User findByUserNameAndAndUserPassword(String username, String password);

}
