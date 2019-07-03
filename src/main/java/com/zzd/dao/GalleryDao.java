package com.zzd.dao;

import com.zzd.entity.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 图库DAO
 *
 * @Author: hqy
 * @Date: 2019/1/21 16:24
 */
public interface GalleryDao extends JpaRepository<Gallery, Integer> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Gallery findById(int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Gallery findGalleryById(int id);

    /**
     * 根据类型查询
     * @param imageType
     * @return
     */
    List<Gallery> findByImageType(int imageType);

    /**
     * 分页查询
     * @param id
     * @param pageable
     * @return
     */
    List<Gallery> findGalleryById(int id, Pageable pageable);

    /**
     * 查询
     * @param keyword
     * @return
     */
    List<Gallery> findByImageName(String keyword);

    /**
     * 查询
     * @param keyword
     * @return
     */
    List<Gallery> findByImageNameIsLike(String keyword);

    /**
     * 模糊查询
     * @param keyword
     * @return
     */
    List<Gallery> findByImageNameContaining(String keyword);

    /**
     * 模糊查询 分页
     * @param keyword
     * @return
     */
    Page<Gallery> findByImageNameContaining(String keyword, Pageable pageable);

}
