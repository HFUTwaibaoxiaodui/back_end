package edu.hfut.back_end.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebConfiguration implements WebMvcConfigurer {

    // 注入我们配置文件中写好的图片保存路径
    @Value("${user.filepath}")
    private String filePath;

    // 自定义资源映射
    // 访问图片示例：http://localhost:3000/api/images/图片名称.jpg
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/images/**")
                .addResourceLocations("file:" + filePath);
    }

}
