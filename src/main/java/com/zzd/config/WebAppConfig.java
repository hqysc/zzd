package com.zzd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * @Author: hqy
 * @Date: 2019/1/23 23:40
 */

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    @Autowired
    private LoginInterceptors loginInterceptors;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大KB, MB
        // factory.setMaxFileSize("1024MB");
        // 上传总数据大小
        // factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/images/**").addResourceLocations("file:D:/zzdimgs/");
        if (mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}")) {
            String imagesPath = WebAppConfig.class.getClassLoader().getResource("").getPath();
            // System.out.print("1.上传配置类imagesPath=="+imagesPath+"\n");
            if (imagesPath.indexOf(".jar") > 0) {
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            } else if (imagesPath.indexOf("classes") > 0) {
                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
            mImagesPath = imagesPath;
        }
        /*
            System.out.print("imagesPath============="+mImagesPath+"\n");
            LoggerFactory.getLogger(WebAppConfig.class).info("imagesPath============="+mImagesPath+"\n");
        */
        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
        // TODO Auto-generated method stub
        /*
            System.out.print("2.上传配置类mImagesPath=="+mImagesPath+"\n");
            super.addResourceHandlers(registry);
        */
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptors).addPathPatterns("/admin/**").excludePathPatterns("/admin/login");
    }
}
