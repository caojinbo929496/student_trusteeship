package com.meng.student.trusteeship.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StorePicturesUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorePicturesUtils.class);

    public static List<String> uploadFileList(MultipartFile[] multipartFiles, String path) {
        List<String> newFileNames = new ArrayList<>();
        File file = new File(path + "/insuranceImages");
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                //文件的原始名称
                String originalFilename = multipartFile.getOriginalFilename();
                String newFileName = null;
                if (multipartFile != null && originalFilename != null && originalFilename.length() > 0) {

                    newFileName = UUID.randomUUID() + originalFilename;
                    //新图片路径
                    File targetFile = new File(path + "/insuranceImages/", newFileName);
                    //内存数据读入磁盘
                    multipartFile.transferTo(targetFile);
                    newFileNames.add(path + "/insuranceImages/" + newFileName);
                }
            }
        } catch (IOException e) {
            LOGGER.info("图片存入数据库IO异常：{}", e);
            throw new IllegalStateException("存放图片失败" + e);
        }
        return newFileNames;
    }
}
