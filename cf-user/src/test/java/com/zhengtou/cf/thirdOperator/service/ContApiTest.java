package com.zhengtou.cf.thirdOperator.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zhengtou.cf.common.api.outer.cl.apply.request.ContractRequest;
import com.zhengtou.cf.common.api.outer.cl.cls.enums.ClsAttachmentEnum;
import com.zhengtou.cf.common.api.outer.cl.cls.enums.ClsContractStatus;
import com.zhengtou.cf.common.api.outer.cl.cls.enums.ClsRangEnum;
import com.zhengtou.cf.common.api.outer.cl.cls.infoVO.Bnp;
import com.zhengtou.cf.common.api.outer.cl.cls.infoVO.Contr;
import com.zhengtou.cf.common.api.outer.cl.cls.infoVO.Loan;
import com.zhengtou.cf.common.api.outer.cl.cls.infoVO.Term;
import com.zhengtou.cf.common.api.outer.cl.cls.request.TNQContractRequest;
import com.zhengtou.cf.common.api.outer.cl.cls.response.TNQContractResponse;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ResponseVO;
import com.zhengtou.cf.conf.ApiConfig;
import com.zhengtou.cf.trade.pojo.entity.bill.BnpEntity;
import com.zhengtou.cf.trade.pojo.entity.bill.BillEntity;
import com.zhengtou.cf.trade.pojo.entity.bill.TermEntity;
import com.zhengtou.cf.trade.pojo.entity.contract.ContractEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class ContApiTest {

    @Autowired
    ApiConfig apiConfig;
    @Autowired
    BaseDao dao;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取合同详情
     * @throws Exception
     */
    @Test
    public void getContInfo() throws Exception {
//        ClsContractStatus contrStatus, ClsAttachmentEnum attachment, ClsRangEnum

        TNQContractRequest request = new TNQContractRequest();
        request.setContrStatus(ClsContractStatus.Y);
        request.setAttachment(ClsAttachmentEnum.N);
        request.setRange(ClsRangEnum.B);
        request.setApplyNo("NA201802071600004903");
//        request.setContrNo("1890973195448615944");
    }

    @Test
    public void save() throws Exception {
//        ClsContractStatus contrStatus, ClsAttachmentEnum attachment, ClsRangEnum

        TNQContractRequest request = new TNQContractRequest();
        request.setContrStatus(ClsContractStatus.Y);
        request.setAttachment(ClsAttachmentEnum.N);
        request.setRange(ClsRangEnum.B);
        request.setApplyNo("NA201802071600004903");
//        request.setContrNo("1890973195448615944");

        ResponseEntity<String> entity = restTemplate.postForEntity(apiConfig.getClApiConf()+"/core/queryContract", request, String.class);
        ResponseVO<TNQContractResponse> responseVO = JSONObject.parseObject(entity.getBody(), new TypeReference<ResponseVO<TNQContractResponse>>(){});
        TNQContractResponse response = responseVO.getData();

        Contr contr = response.getContrList().get(0);
        ContractEntity contractInfo = new ContractEntity();
        BeanUtils.copyProperties(contr, contractInfo);



        Loan loan = contr.getLoanList().get(0);

        System.out.println(JSONObject.toJSONString(loan));
//        loan.getTermList();

        BillEntity billInfo = new BillEntity();
        BeanUtils.copyProperties(loan, billInfo);

        List<TermEntity> termInfoList = new ArrayList<>();
        List<Term> list = loan.getTermList();
        for(Term term:list) {
            TermEntity termInfo = new TermEntity();
            BeanUtils.copyProperties(term, termInfo);
            List<Bnp> bnpList = term.getBnpList();
            List<BnpEntity> bnpInfoList = new ArrayList<>();
            for(Bnp bnp:bnpList) {
                BnpEntity bnpInfo = new BnpEntity();
                BeanUtils.copyProperties(bnp, bnpInfo);
                dao.save(bnpInfo);
                bnpInfoList.add(bnpInfo);
            }
            dao.save(termInfo);
            termInfoList.add(termInfo);
        }
        dao.save(billInfo);

        List<BillEntity> loanInfoList = new ArrayList<>();
        loanInfoList.add(billInfo);
        dao.save(contractInfo);

//        System.out.println("---->" + JSONObject.toJSONString(contractInfoDao.findAll()));
    }

    @Test
    public void confirmCont() throws Exception {
        ContractRequest contractRequest = new ContractRequest("PA201801191000000509");
    }
}
