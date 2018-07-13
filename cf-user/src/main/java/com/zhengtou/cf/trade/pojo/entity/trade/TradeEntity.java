package com.zhengtou.cf.trade.pojo.entity.trade;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.pojo.entity.StoreEntity;
import com.zhengtou.cf.product.pojo.entity.SubProductEntity;
import com.zhengtou.cf.trade.pojo.entity.enums.TradeModelEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStageEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import com.zhengtou.cf.user.pojo.entity.BankCardEntity;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;

import javax.persistence.*;

/**
 * 借款订单记录
 */
@Entity
@Table(name = "t_trade")
public class TradeEntity extends BaseEntity {
    /**
     * 合作机构
     */
    @OneToOne
    private OrganizationEntity org;
    /**
     * 门店
     */
    @OneToOne
    private StoreEntity store;
    /**
     * 人
     */
    @OneToOne
    private ConsumerUserEntity consumerUser;
    /**
     * 贷款
     */
    @OneToOne
    private SubProductEntity subProduct;
    /**
     * 流水编号，本地生成
     */
    @Column(unique = true)
    private String tradeFlowNo;
    /**
     * 外部流水单号
     */
    @Column(unique = true)
    private String outTradeFlowNo;
    /**
     * 长亮系统单号
     */
    private String appNO;
    /**
     * 长亮合同号
     */
    private String contractNo;
    /**
     * 申请单类型
     */
    private TradeModelEnum tradeModelEnum;
    /**
     * 流水的阶段
     */
    @Enumerated(EnumType.STRING)
    private TradeStageEnum flowStage;
    /**
     * 订单状态
     */
    @Enumerated(EnumType.STRING)
    private TradeStatusEnum tradeStatus;
    /**
     * 用户申请金额
     */
    private long requestAmount;
    /**
     * 审批的金额
     */
    private long approvalAmount;
    /**
     * 贷款用途
     */
    private String purpose;
    @OneToOne
    private BankCardEntity bankCard;

    public OrganizationEntity getOrg() {
        return org;
    }

    public void setOrg(OrganizationEntity org) {
        this.org = org;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public ConsumerUserEntity getConsumerUser() {
        return consumerUser;
    }

    public void setConsumerUser(ConsumerUserEntity consumerUser) {
        this.consumerUser = consumerUser;
    }

    public SubProductEntity getSubProduct() {
        return subProduct;
    }

    public void setSubProduct(SubProductEntity subProduct) {
        this.subProduct = subProduct;
    }

    public String getTradeFlowNo() {
        return tradeFlowNo;
    }

    public void setTradeFlowNo(String tradeFlowNo) {
        this.tradeFlowNo = tradeFlowNo;
    }

    public String getOutTradeFlowNo() {
        return outTradeFlowNo;
    }

    public void setOutTradeFlowNo(String outTradeFlowNo) {
        this.outTradeFlowNo = outTradeFlowNo;
    }

    public String getAppNO() {
        return appNO;
    }

    public void setAppNO(String appNO) {
        this.appNO = appNO;
    }

    public TradeModelEnum getTradeModelEnum() {
        return tradeModelEnum;
    }

    public void setTradeModelEnum(TradeModelEnum tradeModelEnum) {
        this.tradeModelEnum = tradeModelEnum;
    }

    public TradeStageEnum getFlowStage() {
        return flowStage;
    }

    public void setFlowStage(TradeStageEnum flowStage) {
        this.flowStage = flowStage;
    }

    public TradeStatusEnum getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(TradeStatusEnum tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public long getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(long requestAmount) {
        this.requestAmount = requestAmount;
    }

    public long getApprovalAmount() {
        return approvalAmount;
    }

    public void setApprovalAmount(long approvalAmount) {
        this.approvalAmount = approvalAmount;
    }

    public BankCardEntity getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCardEntity bankCard) {
        this.bankCard = bankCard;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
