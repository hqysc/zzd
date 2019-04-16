package com.zzd.controller.web;

import com.zzd.common.JsonResult;
import com.zzd.entity.Cart;
import com.zzd.entity.User;
import com.zzd.service.CartService;
import com.zzd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/20 15:31
 */

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    /**
     * 跳转到列表
     * @return
     */
    @RequestMapping("/list")
    public String list(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            List<Cart> cartList = cartService.findByUserId(user.getId());
            map.put("user", user);
            map.put("cartList", cartList);
            return "zzd/cart/list";
        }else {
            return "redirect:/index";
        }
    }

    /**
     * 添加
     * @param productId
     * @param request
     * @return
     */
    @RequestMapping(value = "/add/{productId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult add(@PathVariable int productId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            try {
                cartService.save(user.getId(), productId);
            } catch (Exception e) {
                return JsonResult.failure(e.getMessage());
            }
            return JsonResult.success("添加成功");
        }else {
            return JsonResult.failure("请先登录");
        }
    }

    /**
     * 删除
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable("id") int id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            try {
                cartService.delete(id, user.getId());
            } catch (Exception e) {
                return JsonResult.failure(e.getMessage());
            }
            return JsonResult.success("删除成功");
        }else {
            return JsonResult.failure("请先登录");
        }
    }

    /**
     * 测试
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public List<Cart> lists(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            List<Cart> cartList = cartService.findByUserId(user.getId());
            map.put("user", user);
            map.put("cartList", cartList);
            return cartList;
        }else {
            return null;
        }
    }

}
