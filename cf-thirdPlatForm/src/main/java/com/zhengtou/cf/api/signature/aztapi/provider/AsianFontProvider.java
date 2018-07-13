package com.zhengtou.cf.api.signature.aztapi.provider;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.io.IOException;

/**
 * @Description : 处理html 转 pdf 中文显示
 * @Author: chengzx
 * @Date: Created in  2018/1/10
 */
public class AsianFontProvider extends XMLWorkerFontProvider {
    @Override
    public Font getFont(final String fontName, final String encoding, final boolean embedded, final float size, final int style, final BaseColor color) {
        BaseFont baseFont = null;

        try {
            baseFont = BaseFont.createFont("/home/source/azt/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font font = new Font(baseFont, size, style, color);
        return font;
    }

}
