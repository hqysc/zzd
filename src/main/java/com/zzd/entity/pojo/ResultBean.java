package com.zzd.entity.pojo;

import java.io.Serializable;

/**
 * 接口返回数据模型
 *
 * @author hqy
 * @date 2018/1/11
 */
public class ResultBean<T> implements Serializable {
    /**
     * 表示接口调用成功
     */
    public static final int SUCCESS = 0;
    /**
     * 表示接口调用失败
     */
    public static final int FAIL = 1;
    /**
     * 表示没有权限调用该接口
     */
    public static final int NO_PERMISSION = 2;

    public static final String NO_LOGIN_MSG = "未登录";
    public static final String NO_PERMISSION_MSG = "没有权限";
    public static final String SUCC_MSG = "成功";
    public static final String FAIL_MSG = "失败";

    private String message = SUCC_MSG;
    private int state = SUCCESS;
    /**
     * 返回的数据
     */
    private T data;

    private Integer total;

    /**
     * 分页
     */
    private Integer pageSize;
    private Integer pageNumber;

    public ResultBean() {
        super();
    }

    public ResultBean(T data, Integer total, Integer pageSize, Integer pageNumber) {
        super();
        this.data = data;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    /**
     * 包装异常信息
     *
     * @param e
     */
    public ResultBean(Throwable e) {
        super();
        this.message = e.getMessage();
        this.state = FAIL;
    }

    public ResultBean(String message) {
        super();
        this.message = message;
        this.state = FAIL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "ResultBean{" +
                "message='" + message + '\'' +
                ", state=" + state +
                ", data=" + data +
                ", total=" + total +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
