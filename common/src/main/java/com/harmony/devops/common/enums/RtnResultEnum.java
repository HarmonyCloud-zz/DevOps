package com.harmony.devops.common.enums;

/**
 * Created by sxfans on 16/8/21.
 * 系统异常详情
 */
public enum RtnResultEnum {

    S000000(RtnStatusEnum.SUCCESS, "成功"),
    U000000(RtnStatusEnum.UNKNOW, "未知异常"),

    /**
     * 数据库异常
     */
    E000000(RtnStatusEnum.FAIL, "系统异常，未查询到该条数据"),

    /**
     * 权限
     */
    E010000(RtnStatusEnum.FAIL, "请再次检查登陆用户名和密码！"),
    E010001(RtnStatusEnum.FAIL, "请先登陆"),
    E010002(RtnStatusEnum.FAIL, "您没有该权限！"),

    /**
     * 业务E02
     */
    E020000(RtnStatusEnum.FAIL, "请选择正确的机构！"),
    E020001(RtnStatusEnum.FAIL, "请选择正确的部门！"),
    E020002(RtnStatusEnum.FAIL, "验证码错误！"),

    /**
     * 入参验证E03
     */
    E030000(RtnStatusEnum.FAIL, "请确保参数填写正确！"),
    E030001(RtnStatusEnum.FAIL, "角色不可为空！"),
    E030002(RtnStatusEnum.FAIL, "请选择正确的权限角色！"),
    E030003(RtnStatusEnum.FAIL, "资源不可为空！"),
    E030004(RtnStatusEnum.FAIL, "请选择正确的资源名称！"),

    /**
     * 组织架构相关E04
     */
    E040000(RtnStatusEnum.FAIL, "机构不存在！"),
    E040001(RtnStatusEnum.FAIL, "未查询到该数据！"),
    E040002(RtnStatusEnum.FAIL, "您的短信验证码已过期，请重新获取！"),
    E040003(RtnStatusEnum.FAIL, "您的图片验证码已过期，请重新获取！"),
    E040004(RtnStatusEnum.FAIL, "您的图片验证码错误！"),
    E040005(RtnStatusEnum.FAIL, "请先删除用户数据！"),
    E040006(RtnStatusEnum.FAIL, "相关机构已被禁用或删除！"),
    E040007(RtnStatusEnum.FAIL, "相关门店已被禁用或删除！"),

    /**
     * 数据库相关E05
     */
    E050000(RtnStatusEnum.FAIL, "数据已存在"),
    E050001(RtnStatusEnum.FAIL, "唯一键值重复异常"),
    E050002(RtnStatusEnum.FAIL, "您的短信验证码已过期，请重新获取！"),
    E050003(RtnStatusEnum.FAIL, "您的图片验证码已过期，请重新获取！"),
    E050004(RtnStatusEnum.FAIL, "您的图片验证码错误！"),
    E050005(RtnStatusEnum.FAIL, "暂未支持父级菜单链接！"),

    /**
     * 权限相关E06
     */
    E060000(RtnStatusEnum.FAIL, "您没有权限"),

    /**
     * 业务相关E07
     */
    E070000(RtnStatusEnum.FAIL, "您的可用额度不足"),
    E070001(RtnStatusEnum.FAIL, "请您耐心等待审批"),
    E070002(RtnStatusEnum.FAIL, "该数据已被删除"),
    E070003(RtnStatusEnum.FAIL, "请选择该用户所属机构"),
    E070004(RtnStatusEnum.FAIL, "请先进行风控审核"),
    E070005(RtnStatusEnum.FAIL, "没有相关编码得贷款产品"),
    E070006(RtnStatusEnum.FAIL, "该商户下不存在该订单"),
    E070007(RtnStatusEnum.FAIL, "该订单不存在"),
    E070008(RtnStatusEnum.FAIL, "文件不能为空"),
    E070009(RtnStatusEnum.FAIL, "保存文件失败"),
    E070010(RtnStatusEnum.FAIL, "请上传word文件"),
    E070011(RtnStatusEnum.FAIL, "您有未确认合同订单存在，请先确认该订单"),
    E070012(RtnStatusEnum.FAIL, "您得权限还未设置，请联系系统管理员"),
    E070013(RtnStatusEnum.FAIL, "文件不存在"),
    E070014(RtnStatusEnum.FAIL, "非后台用户无后台栏目访问权限"),
    E070015(RtnStatusEnum.FAIL, "未上传相关合同模板文件"),
    E070016(RtnStatusEnum.FAIL, "您的订单被拒绝，您可以修改个人资料再次提交申请"),
    E070017(RtnStatusEnum.FAIL, "请确认需还款订单"),
    E070018(RtnStatusEnum.FAIL, "暂不支持提前还款"),
    E070019(RtnStatusEnum.FAIL, "该订单不支持提前结清"),
    E070020(RtnStatusEnum.FAIL, "未定义提前结清费率标准"),
    E070021(RtnStatusEnum.FAIL, "未进行合同签约"),

    /**
     * 接口业务相关E08
     */
    E080000(RtnStatusEnum.FAIL, "商户编号未注册"),
    E080001(RtnStatusEnum.FAIL, "请您耐心等待审批"),
    E080002(RtnStatusEnum.FAIL, "该数据已被删除"),
    E080003(RtnStatusEnum.FAIL, "请选择该用户所属机构"),
    E080004(RtnStatusEnum.FAIL, "订单审批中，请耐心等候"),
    E080005(RtnStatusEnum.FAIL, "订单被拒绝"),
    E080006(RtnStatusEnum.FAIL, "您已撤销该订单"),
    E080007(RtnStatusEnum.FAIL, "该卡已是默认收款卡"),
    E080008(RtnStatusEnum.FAIL, "该卡已是默认还款卡"),

    /**
     * 三方业务相关E09
     */
    E090000(RtnStatusEnum.FAIL, "签章参数错误"),
    E090001(RtnStatusEnum.FAIL, "个人签章申请失败"),
    E090002(RtnStatusEnum.FAIL, "签章源文件地址与输出文件地址不能相同"),
    E090003(RtnStatusEnum.FAIL, "请选择该用户所属机构"),
    E090004(RtnStatusEnum.FAIL, "订单审批中，请耐心等候"),

    /**
     * 工具类相关E10
     */
    E100000(RtnStatusEnum.FAIL, "文件操作失败"),
    E100001(RtnStatusEnum.FAIL, "输出文件错误"),
    E100002(RtnStatusEnum.FAIL, "请填写正确得文件地址"),
    E100003(RtnStatusEnum.FAIL, "请选择该用户所属机构"),
    E100004(RtnStatusEnum.FAIL, "订单审批中，请耐心等候"),

    /**
     * 推送相关E11
     */
    E110000(RtnStatusEnum.FAIL, "与服务器链接失败"),
    E110001(RtnStatusEnum.FAIL, "发送目标过多"),
    E110002(RtnStatusEnum.FAIL, "请填写正确得文件地址"),
    E110003(RtnStatusEnum.FAIL, "请选择该用户所属机构"),
    E110004(RtnStatusEnum.FAIL, "订单审批中，请耐心等候"),
    /**
     * 授权相关E12
     */
    E120000(RtnStatusEnum.FAIL, "需重新授信"),
    E120001(RtnStatusEnum.FAIL, "发送目标过多"),
    E120002(RtnStatusEnum.FAIL, "请填写正确得文件地址"),
    E120003(RtnStatusEnum.FAIL, "请选择该用户所属机构"),
    E120004(RtnStatusEnum.FAIL, "订单审批中，请耐心等候"),

    /**
     * 风控相关E12
     */
    E130000(RtnStatusEnum.FAIL, "风控接口调用异常"),
    E130001(RtnStatusEnum.FAIL, "发送目标过多"),
    E130002(RtnStatusEnum.FAIL, "请填写正确得文件地址"),
    E130003(RtnStatusEnum.FAIL, "请选择该用户所属机构"),
    E130004(RtnStatusEnum.FAIL, "订单审批中，请耐心等候"),

    /**
     * 长亮相关E22
     */
    E220000(RtnStatusEnum.FAIL, "金额超出产品范围"),
    E220001(RtnStatusEnum.FAIL, "发送目标过多"),
    E220002(RtnStatusEnum.FAIL, "请填写正确得文件地址"),
    E220003(RtnStatusEnum.FAIL, "请选择该用户所属机构"),
    E220004(RtnStatusEnum.FAIL, "订单审批中，请耐心等候"),

    E0000xx(RtnStatusEnum.FAIL, "失败");

    RtnResultEnum(RtnStatusEnum status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private RtnStatusEnum status;
    private String msg;

    /**
     * Getter method for property status.
     *
     * @return property value of status
     **/
    public RtnStatusEnum getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
