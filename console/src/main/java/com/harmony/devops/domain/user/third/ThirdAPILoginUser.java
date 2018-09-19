package com.harmony.devops.domain.user.third;

import com.harmony.devops.api.GitlabAPI;
import com.harmony.devops.common.domain.BaseEntity;
import com.harmony.devops.common.vo.VO;
import lombok.Data;

import javax.persistence.MappedSuperclass;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/17
 * @描述
 */
@Data
@MappedSuperclass
public abstract class ThirdAPILoginUser extends BaseEntity implements VO.ToVO{
    protected String loginUrl;//登陆地址

    protected String apiToken;//登陆token

    //获取gitapi接口代理
    public GitlabAPI getGitApi(){
        return GitlabAPI.connect(loginUrl,apiToken);
    }
}
