package com.zhengtou.cf.util;

import com.zhengtou.cf.common.enums.AnnexTypeEnum;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 文件处理工具类
 */
public class FileUtil {
    /**
     * 保存上传文件
     */
    public static String saveFile(MultipartFile file, String saveFilePath, AnnexTypeEnum annexTypeEnum) throws BaseException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 解决中文问题， liunx 下中文路径，图片显示问题
        fileName = annexTypeEnum.getCode() + "_" + RandomStringUtils.randomNumeric(6) + suffixName;
        File dest = new File(saveFilePath + fileName);
        //检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(RtnResultEnum.E070009);
        }
    }

    /**
     * 读资源文件
     */
    public static String readResource(String path) throws BaseException {
        try {
            return FileUtils.readFileToString(ResourceUtils.getFile(path),"utf8").replaceAll("\\\\s*|\\t|\\r|\\n","");
        } catch (IOException e) {
            throw new BaseException(RtnResultEnum.E070013);
        }
    }
}
