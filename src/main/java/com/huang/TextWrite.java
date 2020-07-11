package com.huang;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TextWrite {
    public static void main(String[] args) throws DocumentException, IOException {
        InputStream is = XmlParse.class.getClassLoader().getResourceAsStream("test.xml");

        SAXReader saxReader = new SAXReader();

        //3.获得文档对象(整个text.xml文件)  将数据流转换成一个文档对象
        Document doc = saxReader.read(is);

        Element root = doc.getRootElement();

        //创建元素
        Element bean = root.addElement("bean");
        bean.addAttribute("name","joshikosei");
        bean.addAttribute("class","com.huang.pojo.Student");

        Element property1 = bean.addElement("property");
        property1.addAttribute("name","ID");
        property1.addAttribute("value","1");

        Element property2 = bean.addElement("property");
        property2.addAttribute("name","name");
        property2.addAttribute("value","女子高生");

        Element property3 = bean.addElement("property");
        property3.addAttribute("name","sex");
        property3.addAttribute("value","女");

        //写入xml文件中
        FileOutputStream fos = new FileOutputStream("C:\\Users\\hjp960322\\Desktop\\text.xml");
        OutputFormat format = new OutputFormat("\t", true, "UTF-8");
        XMLWriter xw = new XMLWriter(fos, format);
        //将整个文档写入到文件中
        xw.write(doc);
        xw.close();
    }
}
