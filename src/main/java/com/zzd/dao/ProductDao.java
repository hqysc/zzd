package com.zzd.dao;

import com.zzd.entity.Country;
import com.zzd.entity.Product;
import com.zzd.entity.Years;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * 产品Dao
 * @Author: hqy
 * @Date: 2019/1/18 20:27
 */
public interface ProductDao extends JpaRepository<Product, Integer> {

    /**
     * 根据id查找产品
     * @param id
     * @return
     */
    Product findProductById(Integer id);

    /**
     * 根据更新时间查找
     * @return
     */
    @Query(value = "SELECT * FROM product ORDER BY update_time DESC LIMIT 12", nativeQuery = true)
    List<Product> findByUpdate();

    /**
     * 根据二级分类查找 随机
     * @param categoryId
     * @return
     */
    @Query(value = "SELECT * FROM product WHERE category_id=? ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Product> findRandByCategoryId(int categoryId);

    /**
     * 根据二级分类查找
     * @param categoryId
     * @return
     */
    List<Product> findByCategoryId(int categoryId);

    /**
     * 根据二级分类查找 分页
     * List
     * @param categoryId
     * @param pageable
     * @return
     */
    List<Product> findByCategoryId(int categoryId, Pageable pageable);

    /**
     * 根据二级分类查找 分页
     * Page
     * @param categoryId
     * @param pageable
     * @return
     */
    Page<Product> findProductByCategoryId(int categoryId, Pageable pageable);

    /**
     * 根据一级分类查找 分页
     * List
     * @param categoryIds
     * @param pageable
     * @return
     */
    List<Product> findByCategoryIdIn(List<Integer> categoryIds, Pageable pageable);

    /**
     * 根据一级分类查找 分页
     * List
     * @param categoryIds
     * @param pageable
     * @return
     */
    Page<Product> findProductByCategoryIdIn(List<Integer> categoryIds, Pageable pageable);

    /**
     * 根据国家语言查询
     * @param countryId
     * @return
     */
    List<Product> findByCountryId(int countryId);

    /**
     * 根据年份Id查询
     * @param yearsId
     * @return
     */
    List<Product> findByYearsId(int yearsId);

    /**
     * 根据杂志类型ID查询
     * @param typeId
     * @return
     */
    List<Product> findByTypeId(int typeId);

    /**
     * 根据杂志类型ID查询
     * @param describeId
     * @return
     */
    List<Product> findByDescribeId(int describeId);

    /**
     * 查找某个时间之后创建的杂志
     * @param createTime
     * @param pageable
     * @return
     */
    List<Product> findByCreateTimeAfter(Date createTime, Pageable pageable);

    /**
     * 查找某个时间之后更新的杂志
     * @param createTime
     * @param pageable
     * @return
     */
    List<Product> findByUpdateTimeAfter(Date createTime, Pageable pageable);

    /**
     * 查找热门杂志
     * @param isHot
     * @return
     */
    List<Product> findByIsHot(int isHot, Pageable pageable);

    /**
     * 查找热门杂志 随机
     * @param isHot
     * @return
     */
    @Query(value = "SELECT * FROM product WHERE is_hot=? ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Product> findRandByIsHot(int isHot);

    /**
     * 查询热门杂志 分页
     * @param isHot
     * @param pageable
     * @return
     */
    Page<Product> findProductByIsHot(int isHot, Pageable pageable);

    /**
     * 查询免费杂志 分页
     * @param isFree
     * @param pageable
     * @return
     */
    Page<Product> findProductByIsFree(int isFree, Pageable pageable);

    /**
     * 查询免费杂志 随机
     * @param isFree
     * @return
     */
    @Query(value = "SELECT * FROM product WHERE is_free=? ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Product> findRandByIsFree(int isFree);

    /**
     * 根据标题查找杂志
     * @param keyword
     * @return
     */
    List<Product> findByProductNameIsLike(String keyword);

    /**
     * 模糊查询 按更新时间排序
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Product> findByProductNameContaining(String keyword, Pageable pageable);

    /**
     * 多条件查询
     * In
     * categoryId yearsId countryId typeId
     * @return
     */
    Page<Product> findByCategoryIdInAndYearsIdInAndCountryIdInAndTypeIdIn(
            List<Integer> categoryIds, List<Integer> yearsIds, List<Integer> countryIds, List<Integer> typeIds, Pageable pageable);

}
