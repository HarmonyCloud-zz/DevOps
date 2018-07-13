package com.zhengtou.cf.common.enums;

/**
 * @Description : 附加资料类型
 * @Author: 葛文镇
 * @Date: Created in  2017/12/20
 */
public enum AnnexTypeEnum {
    /***************************************个人资料相关*********************************************/
    公安照片("gongan"),
    申请人照片("apply_person"),
    身份证正面("card_positive"),
    身份证反面("card_back"),
    用户图像("image"),
    社保卡("social_insurance"),
    芝麻分("ali_fraction"),
    认证图像("certified"),
    居住证明("live"),
    房产登记证或房产使用权证明("estate"),
    本人照片("user"),
    补充照片("supple"),
    手持身份证半身照("hand_card"),
    FACE第一张("FACE1"),
    FACE第二张("FACE2"),
    FACE第三张("FACE3"),
    FACE第四张("FACE4"),
    与SA的全身合影("HY"),
    /*****************************************贷款相关************************************************/
    代扣银行卡("bankcard"),
    购货小票("shop_certified"),
    授权文件("authori"),
    授权承诺书("authori_letter"),
    借款人告知书("borrow_notice"),
    延期协议("extension_agreement"),
    贷款合同("contract"),
    /*********************************平台相关******************************/
    头像("avator")
    ;

    private String code;

    AnnexTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static AnnexTypeEnum getEnum(String code) {
        for (AnnexTypeEnum c : AnnexTypeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
