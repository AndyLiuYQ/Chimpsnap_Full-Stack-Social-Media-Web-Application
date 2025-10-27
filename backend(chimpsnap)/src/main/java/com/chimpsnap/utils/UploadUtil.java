package com.chimpsnap.utils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping
public class UploadUtil {
    public String upload(MultipartFile file) throws Exception {
        // 获取文件的原始名称
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);

        // 确保文件名唯一，防止覆盖
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 上传文件
        String url = AliOssUtil.uploadFile(filename, file.getInputStream());
        // 如果上传成功，则可以选择性地处理 url，例如保存到数据库
        return url; // 上传成功
    }
}