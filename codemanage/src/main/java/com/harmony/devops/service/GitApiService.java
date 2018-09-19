package com.harmony.devops.service;

import com.harmony.devops.api.GitlabAPI;
import org.springframework.stereotype.Service;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/18
 * @描述
 */
@Service
public class GitApiService {

    /**
     * 初始化gitlab代理
     */
    public GitlabAPI init(String hostUrl,String apiToken){
        return GitlabAPI.connect(hostUrl,apiToken);
    }
}
