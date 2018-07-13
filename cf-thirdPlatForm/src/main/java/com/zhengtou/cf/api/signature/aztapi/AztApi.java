package com.zhengtou.cf.api.signature.aztapi;

import com.esa2000.PfxSignShell;
import com.esa2000.cert.muca.ShortMuCAManager;
import com.esa2000.pdfsign.util.CommonUtil;
import com.esa2000.pdfsign.util.GlobalConfig;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zhengtou.cf.api.signature.aztapi.provider.AsianFontProvider;
import com.zhengtou.cf.api.signature.aztapi.vo.SignInfo;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述： 安证通api
 * @作者： chengzx
 * @创建时间 2018/1/7
 * @版本 1.0
 */
@Service
public class AztApi {
    @Value("${sign.azt.fontpo}")
    private String FONTPO;

    /**
     * 公司签章
     */
    public void companySign(String signDaturl, String fileUrl, String outUrl, List<SignInfo> signInfoList) throws BaseException {
        // 读取印章文件二进制字节到内存
        byte[] pfxSealBytes = CommonUtil.readBytesFromFile(signDaturl);
        //原始文件路径
        String inputc = fileUrl;
        //签署后文件路径
        String outputd = outUrl;
        PfxSignShell signShell = new PfxSignShell();
        //初始化
        signShell.init(inputc, outputd, true);
        // 初始化软印章文件
        signShell.initSoftSeal(pfxSealBytes);
        // 定位签章
        if (signInfoList == null || signInfoList.isEmpty()) {
            throw new BaseException(RtnResultEnum.E090000);
        }
        for (SignInfo signInfo : signInfoList) {
            signShell.addSeal(signInfo.getxPosion(), signInfo.getyPosion(), signInfo.getNumber());
        }
        //做数字签名
        signShell.sign();
        //释放资源
        signShell.close();
    }

    /**
     * 个人签章
     */
    public String personSign(String realName, String idNo, String url, String outUrl, List<SignInfo> signInfos) throws BaseException {
        ShortMuCAManager MuCAManager = new ShortMuCAManager();
        String PfxBase64String = null;
        byte[] pfx = new byte[0];
        try {
            Map<String, String> map = new HashMap<>();
            map.put("certType", "1");
            map.put("custName", realName);
            map.put("identType", "0");
            map.put("identNo", idNo);
            PfxBase64String = MuCAManager.ApplyCertPfx(map);

            pfx = new BASE64Decoder().decodeBuffer(PfxBase64String);
        } catch (Exception e) {
            throw new BaseException(RtnResultEnum.E090001);
        }

        String certpwd = MuCAManager.getPfxPwd();
        // 定义PDF文档来源路径
        String inputc = url;
        String outputd = outUrl;
        //设置字体
        GlobalConfig.getInstance().set("TEXT_FONT", FONTPO);
        GlobalConfig.getInstance().set("SEAL_TEXT_FONT", FONTPO);
        //设置大小
        GlobalConfig.getInstance().set("SEAL_TEXT_FONT_SIZE", "20");
        //设置颜色
        GlobalConfig.getInstance().set("SEAL_TEXT_FONT_COLOR", "255,0,0");
        //个人签名
        PfxSignShell signShell = new PfxSignShell();
        //初始化
        signShell.init(inputc, outputd, true);
        //通过ShellExtendForSubCerter类初始化数字证书、印章数据
        signShell.initSoftSeal(pfx, realName.getBytes(), certpwd);
        //关键字定位签名（电脑字）
        if (signInfos == null || signInfos.isEmpty()) {
            throw new BaseException(RtnResultEnum.E090000);
        }
        for (SignInfo signInfo : signInfos) {
            signShell.addText(realName, signInfo.getxPosion(), signInfo.getyPosion(), signInfo.getNumber());
        }
        //做数字签名
        signShell.sign();
        //释放资源
        signShell.close();
        return outputd;
    }

    /**
     * html转pdf
     */
    public void html2pdf(String htmlPath, String pdfPath) throws BaseException {
        Document document = new Document();
        File pdfFile=new File(pdfPath);
        if (!pdfFile.getParentFile().exists()) {
            pdfFile.getParentFile().mkdirs();
        }
        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
            XMLWorkerHelper workerHelper = XMLWorkerHelper.getInstance();
            workerHelper.parseXHtml(pdfWriter, document, new FileInputStream(htmlPath), Charset.forName("UTF-8"), new AsianFontProvider());
            document.close();
        }  catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(RtnResultEnum.E100000);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new BaseException(RtnResultEnum.E100001);
        }
    }

    /**
     * pdf加水印
     */
    public void pdfWaterMark(String htmlPath, String pdfPath) throws BaseException {
        Document document1 = new Document();
        File pdfFile=new File(pdfPath);
        if (!pdfFile.getParentFile().exists()) {
            pdfFile.getParentFile().mkdirs();
        }
        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document1, new FileOutputStream(pdfPath));
            document1.open();
            XMLWorkerHelper workerHelper = XMLWorkerHelper.getInstance();
            workerHelper.parseXHtml(pdfWriter, document1, new FileInputStream(htmlPath), Charset.forName("UTF-8"), new AsianFontProvider());
            document1.close();
        }  catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(RtnResultEnum.E100000);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new BaseException(RtnResultEnum.E100001);
        }
    }

    /**
     * macdown版word文件  to html
     */
    public void macdownToHtml(String macPath,String htmlPath,HashMap<String,String> map) throws BaseException {
        XWPFDocument document = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String doc1="";
        try {
            FileInputStream fis = new FileInputStream(new File(macPath));
            document = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            doc1 = extractor.getText();
        }catch (IOException e){
            throw new BaseException(RtnResultEnum.E100000);
        }
        PegDownProcessor peg=new PegDownProcessor(Integer.MAX_VALUE);
        if(map!=null && !map.isEmpty()){
            for(String key:map.keySet()){
                doc1=doc1.replaceAll(key,map.get(key));
            }
        }
        String html= peg.markdownToHtml(doc1);
        OutputStream fos = null;
        File file1 = new File(htmlPath);
        //检测是否存在目录
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        try{
            fos = new FileOutputStream(file1);
            fos.write(html.getBytes("UTF-8"));
        }catch (IOException e){
            e.printStackTrace();
            throw  new BaseException(RtnResultEnum.E100000);
        }
    }
}
