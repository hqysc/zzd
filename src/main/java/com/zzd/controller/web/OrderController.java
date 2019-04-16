package com.zzd.controller.web;

import com.zzd.common.JsonResult;
import com.zzd.entity.Order;
import com.zzd.entity.User;
import com.zzd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/3/31 18:26
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 列表
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap map) {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            List<Order> orderList = orderService.findByUserId(user.getId());
            map.put("user", user);
            map.put("orderList", orderList);
            return "zzd/order/list";
        }else {
            return "redirect:/index";
        }
    }

    /**
     * 直接支付 保存订单
     * @param productId
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/pay/{productId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult pay(@PathVariable int productId, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            String message; // 输出信息
            try {
                message = orderService.payAndSave(request, response, productId);
            } catch (Exception e) {
                return JsonResult.failure(e.getMessage());
            }
            return JsonResult.success(message);
        }else {
            return JsonResult.failure("请先登录");
        }
    }

    /**
     * 购物车支付 保存订单
     * @param cartIds
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/pays", method = RequestMethod.POST)
    public String pays(int[] cartIds, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        int order_status = orderService.payAndSaveInCart(request, response, cartIds);
        if(order_status == 1) {
            return "redirect:/order/list";
        }else if(order_status == 2) {
            return "redirect:/cart/list";
        }else {
            return "redirect:/cart/list";
        }
    }
}
