package com.harmony.devops.user.thirdApi.api.app.v02.user;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.StringResponseVO;
import com.harmony.devops.user.jpush.PushAPI;
import com.harmony.devops.user.jpush.pojo.enums.PersonMsgTypeEnum;
import com.harmony.devops.user.jpush.service.MsgService;
import com.harmony.devops.user.user.pojo.entity.UserEntity;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("test_v0.2")
@RequestMapping("api/v0.2/test")
@Api(description = "测试服务")
@Validated
public class TestApi {
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    BaseDao dao;
    @Autowired
    MsgService msgService;
    @Autowired
    PushAPI pushAPI;

    /**
     * 发送用户消息并保存数据库
     */
    @RequestMapping(value = "sendPersonMsgTest/{userId}", method = RequestMethod.POST)
    @ApiOperation("发送用户消息并保存数据库")
    @ApiResponses({@ApiResponse(code = 200, response = String.class, message = "消息平台消息id")})
    public NetVO getUserMsg(@PathVariable @ApiParam(value = "用户id", name = "userId") long userId, @ApiParam(value = "消息标题", name = "title") String
            title, @ApiParam(value = "消息内容", name = "msgContent") String msgContent, @ApiParam(value = "消息类型：*还款消息,*审批消息,*逾期催收消息;", name =
            "personMsgTypeEnum")
                                    PersonMsgTypeEnum personMsgTypeEnum) throws BaseException {
        UserEntity userEntity = dao.get(UserEntity.class, userId);
        String thirdMsgId = pushAPI.sendPushAliasAllMessage(userEntity.getUserNo(), title + msgContent);
        msgService.addPersonMsg(userEntity, title, msgContent, personMsgTypeEnum, thirdMsgId);
        return new StringResponseVO(thirdMsgId);
    }

    /**
     * 发送系统消息并保存数据库
     */
    @RequestMapping(value = "sendSysMsgTest", method = RequestMethod.POST)
    @ApiOperation("发送系统消息并保存数据库")
    @ApiResponses({@ApiResponse(code = 200, response = String.class, message = "消息平台消息id")})
    public NetVO getUserMsg(@ApiParam(value = "消息标题", name = "title") String title, @ApiParam(value = "消息内容", name = "msgContent") String
            msgContent) throws BaseException {
        String thirdMsgId = pushAPI.sendPushAllMessage(title + msgContent);
        msgService.addSysMsg(title, msgContent, null, thirdMsgId);
        return new StringResponseVO(thirdMsgId);
    }
}
