package com.zzd.controller.admin;

import com.zzd.controller.BaseController;
import com.zzd.entity.Order;
import com.zzd.entity.OrderItem;
import com.zzd.entity.User;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.OrderService;
import com.zzd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 订单 Controller
 *
 * @Author: hqy
 * @Date: 2019/4/7 21:28
 */

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController extends BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping({"", "/index"})
    public String list() {
        return "admin/order/list";
    }

    @RequestMapping({"/detail/{orderId}", "/index/detail/{orderId}"})
    public String itemList(@PathVariable int orderId, ModelMap map) {
        map.put("orderId", orderId);
        return "admin/order/itemList";
    }

    /**
     * 列表查询
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResultBean<List<Order>> findAll() {
        String searchName = request.getParameter("search");
        Pageable pageable = getPageableAndSort(Sort.by(Sort.Direction.DESC, "orderTime"));   // 排序
        int total;
        List<Order> list;
        if(searchName != null) {
            total = (int) orderService.findByUserName(searchName, pageable).getTotalElements();
            list = orderService.findByUserName(searchName, pageable).getContent();
        }else {
            total = (int) orderService.findAll(pageable).getTotalElements();
            list = orderService.findAll(pageable).getContent();
        }
        return new ResultBean<>(list, total, pageable.getPageSize(), pageable.getPageNumber());
    }

    /**
     * 订单详细查询
     * @param orderId
     * @return
     */
    @RequestMapping("/list/detail/{orderId}")
    @ResponseBody
    public ResultBean<List<OrderItem>> findItem(@PathVariable int orderId) {
        Pageable pageable = getPageable();
        int total = (int) orderService.findOrderItemByOrderId(orderId, pageable).getTotalElements();
        List<OrderItem> list = orderService.findOrderItemByOrderId(orderId, pageable).getContent();
        return new ResultBean<>(list, total, pageable.getPageSize(), pageable.getPageNumber());
    }

}
