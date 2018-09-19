package com.harmony.devops.common.utils;

import com.harmony.devops.common.exception.BaseException;
import com.thoughtworks.xstream.XStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 葛文镇<br>
 *         解析类
 */
public class ParseUtil {
    /**
     * xml String to Map
     */
    public static Map<String, Object> getMapFromXML(String xmlString)
            throws BaseException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            builder = factory.newDocumentBuilder();
            InputStream is = getStringStream(xmlString);
            Document document = builder.parse(is);
            // 获取到document里面的全部结点
            NodeList allNodes = document.getFirstChild().getChildNodes();
            Node node;
            int i = 0;
            while (i < allNodes.getLength()) {
                node = allNodes.item(i);
                if (node instanceof Element) {
                    map.put(node.getNodeName(), node.getTextContent());
                }
                i++;
            }
        } catch (ParserConfigurationException e) {
            throw new BaseException("ParserConfigurationException",
                    e.getMessage());
        } catch (SAXException e) {
            throw new BaseException("SAXException", e.getMessage());
        } catch (IOException e) {
            throw new BaseException("IOException", e.getMessage());
        }
        return map;
    }

    public static InputStream getStringStream(String sInputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(
                    sInputString.getBytes());
        }
        return tInputStringStream;
    }

    @SuppressWarnings("rawtypes")
    public static Object parseXml2Object(String response, Class c) {
        // 将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream();
        xStreamForResponseData.alias("xml", c);
        return xStreamForResponseData.fromXML(response);
    }

    public static String map2String(Map<String, Object> map) {
        StringBuffer out = new StringBuffer();
        for (String key : map.keySet()) {
            out.append(key);
            out.append("=");
            out.append(map.get(key) + ",");
        }
        return out.toString();
    }
}