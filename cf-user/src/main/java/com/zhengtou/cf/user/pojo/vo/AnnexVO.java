package com.zhengtou.cf.user.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.enums.AnnexTypeEnum;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.user.pojo.entity.enums.AnnexStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 附件
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-贷款附件vo")
public class AnnexVO extends PeakBaseVo{
    public AnnexVO(AnnexTypeEnum annexTypeEnum, String url) {
        this.annexTypeEnum = annexTypeEnum;
        this.url = url;
    }

    /**
     * 合同用
     */
    public AnnexVO(String annexCode,AnnexTypeEnum annexTypeEnum, String url,ZtProductEnum ztProductEnum,AnnexStatusEnum annexStatusEnum) {
        this.annexCode=annexCode;
        this.annexTypeEnum = annexTypeEnum;
        this.url = url;
        this.ztProductEnum=ztProductEnum;
        this.annexStatusEnum=annexStatusEnum;
    }

    @ApiModelProperty(value="附件编码",name="annexCode")
    private String annexCode;
    @ApiModelProperty(value="" +
            "   *附件类型：公安照片(\"gongan\"),\n" +
            "    *申请人照片(\"apply_person\"),\n" +
            "    *身份证正面(\"card_positive\"),\n" +
            "    *身份证反面(\"card_back\"),\n" +
            "    *用户图像(\"image\"),\n" +
            "    *社保卡(\"social_insurance\"),\n" +
            "    *芝麻分(\"ali_fraction\"),\n" +
            "    *认证图像(\"certified\"),\n" +
            "    *居住证明(\"live\"),\n" +
            "    *房产登记证或房产使用权证明(\"estate\"),\n" +
            "    *本人照片(\"user\"),\n" +
            "    *补充照片(\"supple\"),\n" +
            "    *手持身份证半身照(\"hand_card\"),\n" +
            "    *FACE第一张(\"FACE1\"),\n" +
            "    *FACE第二张(\"FACE2\"),\n" +
            "    *FACE第三张(\"FACE3\"),\n" +
            "    *FACE第四张(\"FACE4\"),\n" +
            "    *与SA的全身合影(\"HY\"),\n" +
            "    *代扣银行卡(\"bankcard\"),\n" +
            "    *购货小票(\"shop_certified\"),\n" +
            "    *授权文件(\"authori\"),\n" +
            "    *授权承诺书(\"authori_letter\"),\n" +
            "    *借款人告知书(\"borrow_notice\"),\n" +
            "    *延期协议(\"extension_agreement\"),\n" +
            "    *贷款合同(\"contract\"),\n" +
            "    *头像(\"avator\")",name="annexTypeEnum")
    private AnnexTypeEnum annexTypeEnum;
    @ApiModelProperty(value="附件地址",name="url")
    private String url;
    @ApiModelProperty(value="对应产品类型",name="ztProductEnum")
    private ZtProductEnum ztProductEnum;
    @ApiModelProperty(value="合同状态： 启用,停用;",name="annexStatusEnum")
    private AnnexStatusEnum annexStatusEnum;

    public String getAnnexCode() {
        return annexCode;
    }

    public void setAnnexCode(String annexCode) {
        this.annexCode = annexCode;
    }

    public AnnexTypeEnum getAnnexTypeEnum() {
        return annexTypeEnum;
    }

    public void setAnnexTypeEnum(AnnexTypeEnum annexTypeEnum) {
        this.annexTypeEnum = annexTypeEnum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZtProductEnum getZtProductEnum() {
        return ztProductEnum;
    }

    public void setZtProductEnum(ZtProductEnum ztProductEnum) {
        this.ztProductEnum = ztProductEnum;
    }

    public AnnexStatusEnum getAnnexStatusEnum() {
        return annexStatusEnum;
    }

    public void setAnnexStatusEnum(AnnexStatusEnum annexStatusEnum) {
        this.annexStatusEnum = annexStatusEnum;
    }
}
