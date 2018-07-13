package com.zhengtou.cf.trade.controller;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.AnnexTypeEnum;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.ListResponseVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.service.AnnexService;
import com.zhengtou.cf.trade.service.ContractService;
import com.zhengtou.cf.trade.service.TradeService;
import com.zhengtou.cf.user.pojo.entity.AnnexEntity;
import com.zhengtou.cf.user.pojo.entity.enums.AnnexStatusEnum;
import com.zhengtou.cf.user.pojo.vo.AnnexVO;
import com.zhengtou.cf.user.pojo.vo.BackUserVO;
import com.zhengtou.cf.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("contract")
@Api(description = "合同服务")
@Validated
public class ContractController {
    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    BaseDao dao;
    @Autowired
    TradeService tradeService;
    @Autowired
    ContractService contractService;
    @Autowired
    AnnexService annexService;

    @Value("${upload.contractFile.path}")
    private String path;
    @Value("${show.contractFile.path}")
    private String showPath;

    /**
     * 合同模板上传
     */
    @RequestMapping(value = "uploadContract/{token}", method = RequestMethod.POST)
    @ApiOperation(value = "保存合同模板")
    public NetVO uploadContract(@RequestParam MultipartFile file, @PathVariable String token, @RequestParam ZtProductEnum ztProductEnum,
                                @RequestParam AnnexStatusEnum annexStatusEnum) throws BaseException {
        BackUserVO backUserVO = (BackUserVO) myRedisComponent.get(token);
        if (backUserVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        OrganizationEntity ztorg = (OrganizationEntity) myRedisComponent.get("ztOrganiza");
        if (backUserVO.getOrgId() != ztorg.getId()) {
            return new SuccFessionVO(RtnResultEnum.E060000);
        }
        if (file.isEmpty()) {
            return new SuccFessionVO(RtnResultEnum.E070008);
        }
        String fileOrgName = file.getOriginalFilename();
        String endFileName=fileOrgName.substring(fileOrgName.lastIndexOf("."));
        if (!endFileName.contains("doc") || !endFileName.contains("docx")) {
            return new SuccFessionVO(RtnResultEnum.E070010);
        }
        String filePath = path + ztorg.getOrgNo() + "/";
        String fileName = FileUtil.saveFile(file, filePath, AnnexTypeEnum.贷款合同);

        AnnexEntity annexEntity = new AnnexEntity();
        annexEntity.setAnnexTypeEnum(AnnexTypeEnum.贷款合同);
        annexEntity.setZtProductEnum(ztProductEnum);
        annexEntity.setUrl(showPath + ztorg.getOrgNo() + "/" + fileName);
        annexEntity.setAnnexStatus(annexStatusEnum);
        annexEntity.setOrg(ztorg);
        annexEntity.setAnnexCode(backUserVO.getId()+fileName.replace(endFileName,""));
        dao.save(annexEntity);
        return new SuccFessionVO();
    }

    /**
     * 查看合同列表
     */
    @RequestMapping(value = "getAllContract/{token}", method = RequestMethod.POST)
    @ApiOperation(value = "查看合同列表")
    public NetVO getAllContract(@PathVariable String token) throws BaseException {
        BackUserVO backUserVO = (BackUserVO) myRedisComponent.get(token);
        if (backUserVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        OrganizationEntity ztorg = (OrganizationEntity) myRedisComponent.get("ztOrganiza");
        if (backUserVO.getOrgId() != ztorg.getId()) {
            return new SuccFessionVO(RtnResultEnum.E060000);
        }
        List<AnnexVO> annexEntities = annexService.getAllContractAnnex(ztorg);
        return new ListResponseVO(annexEntities);
    }

    /**
     * 长亮同步接口
     */
    @RequestMapping(value = "lender", method = RequestMethod.POST)
    public Object lender(@RequestParam String reqId) {
        try {
            logger.info("查询合同签署小贷公司主体" );
            String appNo=reqId;
            TradeEntity trade=dao.get("select t from TradeEntity t where t.appNO=?0",new Object[]{appNo});
            if(trade==null){
                logger.info("与长亮同步合同出错，未找到该appNo订单" );
                return new ErrorFessionVO();
            }
            OrganizationEntity org=(OrganizationEntity)myRedisComponent.get("ztOrganiza");
            StringBuffer temp=new StringBuffer("{\n" +
                    "  \"status\": \"200\",\n" +
                    "  \"msg\": \"请求成功\",\n" +
                    "  \"data\": {\n" +
                    "    \"lender\":");
            temp.append("\""+org.getName()+"\",\n" +
                    "    \"lenderNo\": ");
            String socialCreditCode=org.getSocialCreditCode();
            temp.append("\""+ (StringUtils.isBlank(socialCreditCode)?"1111":socialCreditCode)+"\"}}");
            return JSON.parseObject(temp.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorFessionVO();
        }
    }

    /**
     * 长亮同步接口
     */
    @RequestMapping(value = "merchant", method = RequestMethod.POST)
    public Object merchant(@RequestParam String reqId) {
        try {
            logger.info("查询合同签署商户主体" );
            String appNo=reqId;
            TradeEntity trade=dao.get("select t from TradeEntity t where t.appNO=?0",new Object[]{appNo});
            if(trade==null){
                logger.info("与长亮同步合同出错，未找到该appNo订单" );
                return new ErrorFessionVO();
            }
            StringBuffer temp=new StringBuffer("{\n" +
                    "  \"status\": \"200\",\n" +
                    "  \"msg\": \"请求成功\",\n" +
                    "  \"data\": {\n" +
                    "    \"legalPerson\":");
            String legalPerson=trade.getOrg().getPrincipal();
            temp.append("\""+(StringUtils.isBlank(legalPerson)?"空法人":legalPerson)+"\",\n" +
                    "    \"merchantName\": ");
            String socialCreditCode=trade.getOrg().getName();
            temp.append("\""+ (StringUtils.isBlank(socialCreditCode)?"空商户名称":socialCreditCode)+"\"}}");
            return JSON.parseObject(temp.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorFessionVO();
        }
    }
}
