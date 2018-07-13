package com.zhengtou.cf.test;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zhengtou.cf.api.signature.aztapi.provider.AsianFontProvider;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Description :
 * @Author: chengzx
 * @Date: Created in  2018/1/8
 */
public class poiTest {


    @Test
    public void word2pdf() {
        //return 网络/业务，数据
        //签章：找文件，参数
        XWPFDocument document = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            FileInputStream fis = new FileInputStream(new File("src/main/resources/azt/input/consumer.docx"));
            document = new XWPFDocument(fis);
            //docx转xhtml
            XHTMLOptions options = XHTMLOptions.create();
            options.setExtractor(new FileImageExtractor(new File("src/main/resources/azt")));
            options.URIResolver(new BasicURIResolver("src/main/resources/azt/img"));
            XHTMLConverter.getInstance().convert(document, bos, options);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String content = bos.toString();
            System.out.println(content);

            OutputStream fos = null;
            BufferedWriter bw = null;
            org.jsoup.nodes.Document doc = Jsoup.parse(content);
            System.out.println(doc.html());
            Element body = doc.body();
            String style = body.attr("style");
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(style) && style.indexOf("width") != -1) {
                body.attr("style", "");
            }
            Elements div = doc.select("div");
            for (Element d : div) {
                d.removeAttr("style");
            }
            content = doc.html();

            //替换变量信息
            Map<String, String> map = new HashMap<>();
            map.put("DOCSEQNO", "123");
            map.put("PAYNAME", "鸿成小贷公司");
            map.put("PAYIDCARD", "951548154646567MJ3232");
            map.put("BORROWNAME", "葛文镇");
            map.put("BORROWIDCARD", "410881199012263158");
            map.put("GUACOMPANY", "郑投网络信息技术有限公司");
            map.put("GUANAME", "连中峰");
            String reContent = replaceKeyWord(content, map);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(reContent);

            try {
                File file1 = new File("src/main/resources/azt/input/1.html");
                fos = new FileOutputStream(file1);
                fos.write(reContent.getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        com.itextpdf.text.Document document1 = new com.itextpdf.text.Document();
        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document1, new FileOutputStream("src/main/resources/azt/input/5.pdf"));
            document1.open();

            XMLWorkerHelper workerHelper = XMLWorkerHelper.getInstance();
            workerHelper.parseXHtml(pdfWriter, document1, new FileInputStream("src/main/resources/azt/input/1.html"), Charset.forName("UTF-8"), new AsianFontProvider());
            document1.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String replaceKeyWord(String content, Map<String, String> map) {
        String replace = null;
        for (Map.Entry entry : map.entrySet()) {
            replace = content.replaceAll(entry.getKey().toString(), entry.getValue().toString());
        }
        return replace;
    }

    @Test
    public void testpdf() {
        com.itextpdf.text.Document document1 = new com.itextpdf.text.Document();
        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document1, new FileOutputStream("/src/resources/azt/input/1.pdf"));
            document1.open();

            XMLWorkerHelper workerHelper = XMLWorkerHelper.getInstance();
            workerHelper.parseXHtml(pdfWriter, document1, new FileInputStream("/src/resources/azt/input/1.html"), Charset.forName("UTF-8"), new AsianFontProvider());
            document1.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put("DOCSEQNO", "123");
        map.put("PAYNAME", "张三");
        map.put("BORROWNAME", "李四");
        map.put("GUACOMPANY", "郑投网络信息技术有限公司");
//        map.put("123", "DOCSEQNO");
//        map.put("张三", "PAYNAME");
//        map.put("李四", "BORROWNAME");
//        map.put("郑投网络信息技术有限公司", "GUACOMPANY");
//        generateWord(map, "E://消费贷.docx", "E://消费贷1.docx");
        searchAndReplace("E://consumer.docx", "E://consumer1.docx", map);
        FileInputStream fis = new FileInputStream(new File("E://consumer1.docx"));
        XWPFDocument xdoc = new XWPFDocument(fis);
        XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
        String doc = extractor.getText();
        System.out.println(doc);
        close(fis);

    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理段落,替换关键字
     *
     * @param paragraphList
     * @throws FileNotFoundException
     * @throws InvalidFormatException
     */
    public static void processParagraphs(List<XWPFParagraph> paragraphList, Map<String, Object> param, XWPFDocument doc)
            throws InvalidFormatException, FileNotFoundException {
        if (paragraphList != null && paragraphList.size() > 0) {
            // 遍历所有段落
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
//                     System.out.println("text==" + text);
                    if (text != null) {
                        boolean isSetText = false;
                        for (Map.Entry<String, Object> entry : param.entrySet()) {
                            String key = entry.getKey();
                            if (text.indexOf(key) != -1) {// 在配置文件中有这个关键字对应的键
                                isSetText = true;
                                Object value = entry.getValue();
                                if (value instanceof String) {// 文本替换
                                    System.out.println("key==" + key);
                                    if (text.contains(key)) {
                                        text = text.replace(key, value.toString());
                                    }
                                }
                            }
                        }
                        if (isSetText) {
                            run.setText(text, 0);
                        }
                    }
                }
            }
        }
    }


    public static void searchAndReplace(String srcPath, String destPath, Map<String, String> map) {
        try {
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
            /**
             * 替换段落中的指定文字
             */
            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                Set<String> set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    List<XWPFRun> run = paragraph.getRuns();
                    for (int i = 0; i < run.size(); i++) {
                        if (run.get(i).getText(run.get(i).getTextPosition()) != null &&
                                run.get(i).getText(run.get(i).getTextPosition()).equals(key)) {
                            /**
                             * 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始
                             * 就可以把原来的文字全部替换掉了
                             */
                            run.get(i).setText(map.get(key), 0);
                        }
                    }
                }
            }

            /**
             * 替换表格中的指定文字
             */
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            while (itTable.hasNext()) {
                XWPFTable table = (XWPFTable) itTable.next();
                int count = table.getNumberOfRows();
                for (int i = 0; i < count; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (Map.Entry<String, String> e : map.entrySet()) {
                            if (cell.getText().equals(e.getKey())) {
                                cell.removeParagraph(0);
                                cell.setText(e.getValue());
                            }
                        }
                    }
                }
            }
            FileOutputStream outStream = null;
            outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
