package com.harmony.devops.domain.user.third;

import com.harmony.devops.common.vo.VO;
import com.harmony.devops.domain.server.Server;
import com.harmony.devops.domain.user.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static com.harmony.devops.common.vo.VO.inCaseNull;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/17
 * @描述
 */
@Data
@Entity
@Table(name = "t_code_user")
public class CodeUser extends CodeUserInfo {
    @ManyToOne
    private User user;

    @OneToOne
    private Server server;//服务器地址


    @Override
    public Vo toVO(boolean isRecursion) {
        Vo vo=new Vo();
        vo.id=this.id;
        vo.name=this.name;
        vo.email=this.email;
        vo.skype=this.skype;
        vo.organization=this.organization;
        vo.projects_limit=this.projects_limit;
        vo.admin=this.admin;
        vo.can_create_group=this.can_create_group;
        if(isRecursion){
            vo.creater=(User.Vo)inCaseNull(this.user,false);
        }
        return null;
    }

    public ApiVo toApiVo(boolean isRecursion){
        ApiVo apiVo=new ApiVo();
        apiVo.loginUrl=this.loginUrl;
        apiVo.apiToken=this.apiToken;
        if(isRecursion){
            apiVo.serverVo=(Server.Vo)inCaseNull(this.server,false);
        }
        return apiVo;
    }

    @Data
    public static class Vo implements VO {
        private Long id;
        private String name;
        private String email;
        private String skype;
        private String organization;
        private String projects_limit;
        private boolean admin;
        private boolean can_create_group;
        private User.Vo creater;
    }

    @Data
    public static class ApiVo implements VO {
        private String loginUrl;
        private String apiToken;
        private Server.Vo serverVo;
    }

}
