package com.zzd.controller.admin;

import com.zzd.controller.BaseController;
import com.zzd.entity.RechargeOrder;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.RechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/4/9 14:02
 */
@Controller
@RequestMapping("/admin/recharge")
public class RechargeOrderController extends BaseController {

    @Autowired
    private RechargeOrderService rechargeOrderService;

    @RequestMapping({"", "/index"})
    public String list() {
        return "admin/recharge/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultBean<List<RechargeOrder>> findAll() {
        String searchName = request.getParameter("search");
        Pageable pageable = getPageableAndSort(Sort.by(Sort.Direction.DESC, "orderTime"));   // 排序
        int total;
        List<RechargeOrder> list;
        if(searchName != null) {
            total = (int) rechargeOrderService.findByUserName(searchName, pageable).getTotalElements();
            list = rechargeOrderService.findByUserName(searchName, pageable).getContent();
        }else {
            total = (int) rechargeOrderService.findAll(pageable).getTotalElements();
            list = rechargeOrderService.findAll(pageable).getContent();
        }
        return new ResultBean<>(list, total, pageable.getPageSize(), pageable.getPageNumber());
    }

}
