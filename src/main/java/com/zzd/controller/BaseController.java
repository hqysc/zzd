package com.zzd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: hqy
 * @Date: 2019/1/18 0:50
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    /**
     * 按id排序 默认方法
     * @return
     */
    public Pageable getPageable() {
        int page = 1;
        int size = 10;

        page = Integer.parseInt(request.getParameter("pageNumber")) - 1;
        size = Integer.parseInt(request.getParameter("pageSize"));

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));

        return pageable;
    }

    /**
     * 自定义排序
     * @param sort
     * @return
     */
    public Pageable getPageableAndSort(Sort sort) {
        int page = 1;
        int size = 10;

        page = Integer.parseInt(request.getParameter("pageNumber")) - 1;
        size = Integer.parseInt(request.getParameter("pageSize"));

        Pageable pageable = PageRequest.of(page, size, sort);

        return pageable;
    }


}
