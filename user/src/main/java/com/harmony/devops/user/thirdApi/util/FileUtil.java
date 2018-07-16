package com.harmony.devops.user.thirdApi.util;

import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 文件处理工具类
 */
public class FileUtil {

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
