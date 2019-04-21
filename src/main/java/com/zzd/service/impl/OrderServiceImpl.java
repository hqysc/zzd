package com.zzd.service.impl;

import com.zzd.dao.*;
import com.zzd.entity.*;
import com.zzd.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/3/31 16:22
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private GalleryDao galleryDao;
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private YearsDao yearsDao;
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        Page<Order> orderList = orderDao.findAll(pageable);
        for(Order order : orderList) {
            getUser(order); // 获取用户信息
        }
        return orderList;
    }

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    @Override
    public List<Order> findByUserId(int userId) {
        List<Order> orderList = orderDao.findByUserId(userId, Sort.by(Sort.Direction.DESC, "orderTime"));    // get order
        for(Order order : orderList) {
            List<OrderItem> orderItemList = orderItemDao.findByOrderId(order.getId()); //get orderItem
            for(OrderItem orderItem : orderItemList) {
                Product product = productDao.findProductById(orderItem.getProductId());
                getCoverImage(product, 1);
                orderItem.setProduct(product); // get product
            }
            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    /**
     * 根据用户ID查询 分页
     * @param userName
     * @param pageable
     * @return
     */
    @Override
    public Page<Order> findByUserName(String userName, Pageable pageable) {
        List<Integer> userIds = new ArrayList<>();
        List<User> userList = userDao.findByUserNameContaining(userName);
        for(User user : userList) {
            userIds.add(user.getId());
        }
        if(userIds != null || userIds.isEmpty()) {
            Page<Order> orderList = orderDao.findByUserIdIn(userIds, pageable);
            for(Order order : orderList) {
                getUser(order); // 获取用户信息
            }
            return orderList;
        }else{
            return null;
        }
    }

    /**
     * 直接支付时检查书币余额
     * @param request
     * @param response
     * @param productId
     * @return
     */
    @Override
    public int checkPayCoin(HttpServletRequest request, HttpServletResponse response, Integer productId) {
        if(productId != null) {
            User user = (User) request.getSession().getAttribute("user");
            Product product = productDao.findProductById(productId);
            if(user != null) {
                if(user.getCoin() >= product.getPrice()) {
                    return 1;   // 余额可支付
                }else {
                    return 0;   // 余额不足
                }
            }
        }
        return -1;
    }

    /**
     * 直接结算
     * @param request
     * @param response
     * @param productId
     * @return
     */
    @Override
    public int payAndSave(HttpServletRequest request, HttpServletResponse response, Integer productId) {
        if(productId != null) {
            Product product = productDao.findProductById(productId);
            Double total = product.getPrice();
            User user = (User) request.getSession().getAttribute("user");
            if(user != null) {
                if(user.getCoin() >= total) {
                    user.setCoin(user.getCoin() - total);
                    userDao.saveAndFlush(user);
                    Order order = new Order();
                    order.setOrderTime(new Date());
                    order.setUserId(user.getId());
                    order.setItemTotal(1);    // item count
                    order.setPriceTotal(total);  // total price
                    orderDao.save(order);

                    OrderItem orderItem = new OrderItem();
                    orderItem.setPrice(product.getPrice());
                    orderItem.setProductId(productId);
                    orderItem.setOrderId(order.getId());
                    orderItemDao.save(orderItem);

                    List<Cart> cartList = cartDao.findCartByUserIdAndProductId(user.getId(), productId);
                    if(cartList != null || !cartList.isEmpty()) {
                        for(Cart cart : cartList) {
                            cartDao.deleteById(cart.getId());
                        }
                    }
                    return 0;   // 支付成功
                }else {
                    return 1;   // 余额不足
                }
            }
        }
        return -1;  // 支付失败
    }

    /**
     * 从购物车里结算
     * @param request
     * @param response
     * @param cartIds
     * @return
     */
    @Override
    public int payAndSaveInCart(HttpServletRequest request, HttpServletResponse response, int[] cartIds) {
        if(cartIds != null) {
            Double total = 0.0;
            User user = (User) request.getSession().getAttribute("user");
            if(user != null) {
                List<Cart> cartList = new ArrayList<>();
                for (int cartId : cartIds) {   // cartIds
                    Cart cart = cartDao.findCartById(cartId);   // get cart
                    Product product = productDao.findProductById(cart.getProductId());  // get product
                    cart.setProduct(product);
                    cartList.add(cart);
                    total += product.getPrice();
                }

                if(user.getCoin() >= total) {
                    user.setCoin(user.getCoin() - total);
                    userDao.saveAndFlush(user);
                    Order order = new Order();
                    order.setOrderTime(new Date());
                    order.setUserId(user.getId());
                    order.setItemTotal(cartList.size());    // item count
                    order.setPriceTotal(total);  // total price
                    orderDao.save(order);
                    for(Cart cart : cartList) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setPrice(cart.getProduct().getPrice());
                        orderItem.setProductId(cart.getProductId());
                        orderItem.setOrderId(order.getId());
                        orderItemDao.save(orderItem);
                        cartDao.deleteById(cart.getId());   // delete cart
                    }
                    return 0;   // 支付成功
                }else {
                    return 1;   // 余额不足
                }
            }

        }else {
            return 2;   // 请至少勾选一项商品
        }
        return -1;  // 支付失败
    }

    /**
     * 查询订单详细内容
     * @param orderId
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderItem> findOrderItemByOrderId(int orderId, Pageable pageable) {
        Page<OrderItem> orderItemList = orderItemDao.findByOrderId(orderId, pageable);
        for(OrderItem orderItem : orderItemList) {
            Product product = productDao.findProductById(orderItem.getProductId());
            getCoverImage(product, 1);
            getInfo(product);
            orderItem.setProduct(product); // get product
        }
        return orderItemList;
    }

    /**
     * 获取用户信息
     * @param order
     */
    public void getUser(Order order) {
        User user = userDao.findUserById(order.getUserId());
        order.setUser(user);
    }

    /**
     * 获取封面图片
     * @param product
     * @param sortId
     */
    public void getCoverImage(Product product, int sortId) {
        // 图片
        List<ProductImage> productImageList = productImageDao.findByProductIdAndAndSortId(product.getId(),sortId);
        for(ProductImage productImage : productImageList) {
            int galleryId = productImage.getGalleryId();
            Gallery gallery = galleryDao.findById(galleryId);
            product.setGallery(gallery);
        }
    }

    /**
     * 获取年份
     * @param product
     */
    public void getInfo(Product product) {
        // 分类
        if(product.getCategoryId() != null) {
            ProductCategory category = productCategoryDao.findProductCategoryById(product.getCategoryId());
            product.setCategory(category);
        }
        // 年份
        if(product.getYearsId() != null) {
            Years years = yearsDao.findYearsById(product.getYearsId());
            product.setYears(years);
        }
        // 语言
        if(product.getCountryId() != null) {
            Country country = countryDao.findCountryById(product.getCountryId());
            product.setCountry(country);
        }
        // 类型
        if(product.getTypeId() != null) {
            ProductType productType = productTypeDao.findProductTypeById(product.getTypeId());
            product.setType(productType);
        }
    }
}
