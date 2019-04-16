package com.zzd.service.impl;

import com.zzd.dao.CartDao;
import com.zzd.dao.GalleryDao;
import com.zzd.dao.ProductDao;
import com.zzd.dao.ProductImageDao;
import com.zzd.entity.*;
import com.zzd.service.CartService;
import com.zzd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author: hqy
 * @Date: 2019/2/20 15:24
 */

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private GalleryDao galleryDao;

    @Override
    public Cart findById(int id) {
        return cartDao.findCartById(id);
    }

    /**
     * 查找列表 排序
     * @param userId
     * @return
     */
    @Override
    public List<Cart> findByUserId(int userId) {
        List<Cart> cartList = cartDao.findByUserId(userId, Sort.by(Sort.Direction.DESC, "createTime"));
        for(Cart cart : cartList) {
            getProduct(cart);
        }
        return cartList;
    }

    /**
     * 添加
     * @param userId
     * @param productId
     */
    @Override
    public void save(int userId, int productId) {
        List<Cart> checkCart = cartDao.findCartByUserIdAndProductId(userId, productId);
        if(checkCart != null && checkCart.isEmpty()) {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setCreateTime(new Date());
            cartDao.save(cart);
        }
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(int id, int userId) {
        Cart cart = cartDao.findCartById(id);
        // 自己才能删除自己的收藏
        if(userId == cart.getUserId()) {
            cartDao.deleteById(id);
        }
    }

    /**
     * 获取商品
     * @param cart
     * @return
     */
    public void getProduct(Cart cart) {
        Product product = productDao.findProductById(cart.getProductId());
        getCoverImage(product, 1);
        cart.setProduct(product);
    }

    /**
     * 获取封面图片
     * @param product
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
}
