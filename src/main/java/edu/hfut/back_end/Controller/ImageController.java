package edu.hfut.back_end.Controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/img")
@Api(value = "本地图片服务器接口", tags = "本地图片服务器接口")
public class ImageController {

    // 注入配置中图片保存路径
    @Value("${user.filepath}")
    private String filePath;


    // 处理上传图片请求的方法
    // @RequestPart("pic")MultipartFile 上传文件时携带图片的key定义为pic
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String upload(@RequestPart("pic") MultipartFile multipartFile) {

        // 生成一个随机的名称，避免文件名重复
        UUID uuid = UUID.randomUUID();
        // 获取原文件名称
        String originalFileName = multipartFile.getOriginalFilename();
        // 获取原文件的后缀
        String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
        // 保存文件
        File file = new File(filePath + uuid + fileSuffix);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        // 返回图片的完整访问路径，这地方ip和端口可以改为动态获得，这样在部署到服务器上时无需改变，为了方便起见这里直接写死了
        return "http://localhost:9090/api/images/" + uuid + fileSuffix;
    }

}
