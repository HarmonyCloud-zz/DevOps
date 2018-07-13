package com.zhengtou.cf.api.pay.yibaoapi.util.merchant;

import com.zhengtou.cf.common.api.outer.pay.yibao.vo.merchant.BatchPayItem;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.merchant.request.BatchPayRequest;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.merchant.request.QueryPayRequest;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.merchant.request.SinglePayRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * 商户版代付代发
 *
 * @author 葛文镇
 */
@Service
public class YbMerchantService {
    //单笔发送报文
    private String getSingleRequest(SinglePayRequest singlePayRequest) {
        try {
            String data = FileUtils.readFileToString(
                    new File(YbMerchantService.class.getClassLoader().getResource("/")
                            .getPath().replaceAll("%20", " ")
                            + "xml/yibaoMerchant/xmlModel/SinglePay.xml"), "GBK");

            String[] searchList = {"@merchantAccount", "@merchantAccountChild", "@batchNo",
                    "@bankCode", "@orderId", "@cnaps", "@bankName", "@branchBankName", "@amount", "@accountName", "@accountNumber", "@accountType",
                    "@province", "@city", "@feeType", "@hmac"};
            String[] replacementList = {
                    singlePayRequest.merchantAccount,
                    singlePayRequest.merchantAccount, singlePayRequest.batchNo,
                    singlePayRequest.bankCode,
                    singlePayRequest.orderId, singlePayRequest.cnaps, singlePayRequest.bankName, singlePayRequest.branchBankName, singlePayRequest
                    .amount + "", singlePayRequest.accountName, singlePayRequest.accountNumber, singlePayRequest.accountType, singlePayRequest
                    .province, singlePayRequest.city, singlePayRequest.feeType, singlePayRequest.hmac};
            return StringUtils.replaceEach(data, searchList, replacementList);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    //批量发送报文
    private String getBatchRequest(BatchPayRequest batchPayRequest) {
        try {
            String data = FileUtils.readFileToString(
                    new File(YbMerchantService.class.getClassLoader().getResource("/")
                            .getPath().replaceAll("%20", " ")
                            + "xml/yibaoMerchant/xmlModel/BatchPayItem.xml"), "GBK");

            String[] searchList = {"@bankCode", "@orderId", "@cnaps", "@bankName", "@branchBankName", "@amount", "@accountName", "@accountNumber",
                    "@accountType",
                    "@province", "@city", "@feeType"};

            StringBuffer items = new StringBuffer("");
            for (BatchPayItem item : batchPayRequest.items) {
                String[] replacement = {
                        item.bankCode,
                        item.orderId, item.cnaps, item.bankName, item.branchBankName, item
                        .amount + "", item.accountName, item.accountNumber, item.accountType, item
                        .province, item.city, item.feeType};
                items.append(StringUtils.replaceEach(data, searchList, replacement));
            }

            String data1 = FileUtils.readFileToString(
                    new File(YbMerchantService.class.getClassLoader().getResource("/")
                            .getPath().replaceAll("%20", " ")
                            + "xml/yibaoMerchant/xmlModel/BatchPayHead.xml"), "GBK");

            String[] searchList1 = {"@merchantAccount", "@merchantAccountChild", "@batchNo",
                    "@totalNum", "@totalAmt", "@hmac", "@items"};
            String[] replacementList = {
                    batchPayRequest.merchantAccount,
                    batchPayRequest.merchantAccount, batchPayRequest.batchNo,
                    batchPayRequest.totalNum, batchPayRequest.totalAmt, batchPayRequest.hmac,items.toString()};
            return StringUtils.replaceEach(data1, searchList1, replacementList);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    //打款批次明细
    private String getQueryPayRequest(QueryPayRequest singlePayRequest) {
        try {
            String data = FileUtils.readFileToString(
                    new File(YbMerchantService.class.getClassLoader().getResource("/")
                            .getPath().replaceAll("%20", " ")
                            + "xml/yibaoMerchant/xmlModel/QueryPayDetail.xml"), "GBK");

            String[] searchList = {"@merchantAccount", "@merchantAccountChild", "@batchNo",
                    "@orderId", "@pageNo", "@hmac"};
            String[] replacementList = {
                    singlePayRequest.merchantAccount,
                    singlePayRequest.merchantAccount, singlePayRequest.batchNo,
                    singlePayRequest.bankCode,
                    singlePayRequest.orderId, singlePayRequest.hmac};
            return StringUtils.replaceEach(data, searchList, replacementList);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
