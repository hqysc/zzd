package com.zzd.controller.admin;

import com.zzd.common.JsonResult;
import com.zzd.controller.BaseController;
import com.zzd.entity.Gallery;
import com.zzd.entity.pojo.ResultBean;
import com.zzd.service.GalleryService;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 批量图片上传
 *
 * @Author: hqy
 * @Date: 2019/1/21 22:44
 */
@Controller
@RequestMapping("/admin/gallery")
public class GalleryController extends BaseController {

    @Autowired
    private GalleryService galleryService;

    @Value("${dev_file_path}")
    private String path;

    @Value("${file_visit_path}")
    private String imgVisitPath;

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * upload images
     * @return
     */
    @RequestMapping("/upload")
    public String toAdd() {
        return "admin/gallery/upload";
    }

    @RequestMapping({ "", "/index" })
    public String index() {
        return "admin/gallery/list";
    }

    /**
     * List
     * BootStrap Table
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResultBean<List<Gallery>> findAll() {
        String searchText = request.getParameter("search");
        Pageable pageable = getPageableAndSort(Sort.by(Sort.Direction.DESC, "createTime"));
        int total;
        List<Gallery> list;
        if(searchText != null) {
            total = (int) galleryService.findByImageNameContaining(searchText, pageable).getTotalElements();
            list = galleryService.findByImageNameContaining(searchText, pageable).getContent();
        }else {
            total = (int) galleryService.findAll(pageable).getTotalElements();
            list = galleryService.findAll(pageable).getContent();
        }
        return new ResultBean<>(list, total, getPageable().getPageSize(), getPageable().getPageNumber());
    }

    /**
     * 删除
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable int id, ModelMap map) {
        try {
            deleteImage(id);    // 删除图片
            galleryService.delete(id);  // 删除数据
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /**
     * 文件删除
     * @param id
     */
    public void deleteImage(Integer id) {
        Gallery gallery = galleryService.findById(id);
        String fileName = gallery.getImageName();
        String thumbFileName = "thumbnails - " + fileName;   // 处理后文件名

        File targetFile = new File(path + fileName);     //创建文件对象
        File thumbFile = new File(path + thumbFileName);   // 缩略图

        if(targetFile.exists()) {   // 原图
            targetFile.delete();
        }

        if(thumbFile.exists()) {    // 缩率图
            thumbFile.delete();
        }
    }

    /**
     * 添加
     * @param imageName
     * @param imageSrc
     * @param imageType
     * @param request
     * @param response
     */
    public void save(
            int imageType,
            String imageName,
            String imageSrc,
            String thumbImageSrc,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Gallery gallery = new Gallery();
        gallery.setImageName(imageName);
        gallery.setImageSrc(imageSrc);
        gallery.setThumbImageSrc(thumbImageSrc);
        gallery.setImageType(imageType);
        gallery.setImageStatus(0);
        gallery.setCreateTime(new Date());
        int id = galleryService.create(gallery);
    }

    /**
     * 检测
     * @return
     */
    @PostMapping("/test/{id}")
    @ResponseBody
    public String test(@PathVariable int id, ModelMap modelMap) {
        Gallery gallery = galleryService.findById(id);
        String fileName = gallery.getImageName();
        String thumbFileName = "thumbnails - " + fileName;   // 处理后文件名

        File targetFile = new File(path + fileName);     //创建文件对象
        File thumbFile = new File(path + thumbFileName);   // 缩略图

        if(targetFile.exists()) {
            /*
            if (targetFile.delete()) {
                return 1;
            } else {
                return 2;
            }
             */
            return fileName;
        }else {
            return "0";
        }
    }

    /**
     * 杂志图片批量上传 入库
     * 去掉后缀名
     * @param imageType
     * @param request
     * @param response
     */
    @RequestMapping("/uploader/{imageType}")
    @ResponseBody
    public void upload(@PathVariable int imageType, HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> files = multRequest.getFileMap();//得到文件map对象
        File dir = new File(path);
        // 目录不存在则创建
        if(!dir.exists()) {
            dir.mkdirs();
        }

        for(MultipartFile file : files.values()) {
            String fileName = file.getOriginalFilename(); // 得到原文件名
            String thumbFileName = "thumbnails - " + fileName;   // 处理后文件名

            LOGGER.debug("文件上传: " + path + fileName);  // 日志打印
            LOGGER.debug("文件上传: " + path + thumbFileName);  // 日志打印

            File tagetFile = new File(path + fileName);     //创建文件对象
            File thumbFile = new File(path + thumbFileName);   // 缩略图

            String imageSrc = imgVisitPath + fileName; // 获得图片访问路径
            String thumbImageSrc = imgVisitPath + thumbFileName; // 缩率图访问路径

            // 文件名不存在 则新建文件，并将文件复制到新建文件中
            if(!tagetFile.exists()) {
                try {
                    tagetFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    file.transferTo(tagetFile); // 写入文件
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            // 写入缩略图
            try {
                Thumbnails.of(tagetFile).scale(0.5f).toFile(thumbFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Gallery> galleryList = galleryService.findByImageName(fileName);
            if(galleryList == null || galleryList.isEmpty()) {
                // 即使文件存在 数据不存在 也添加数据
                save(imageType, fileName, imageSrc, thumbImageSrc, request, response);
            }
        }
    }

}
