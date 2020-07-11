package com.huang;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XmlParse {
    public static void main(String[] args) throws DocumentException {
        //1.加载XML文件到JVM，形成数据流
        InputStream is = XmlParse.class.getClassLoader().getResourceAsStream("test.xml");

        //2.创建解析对象。用dom4j（不要看这里写SAX，dom4j就是保留了sax的优点做出的进化体）
        SAXReader saxReader = new SAXReader();

        //3.获得文档对象(整个text.xml文件)  将数据流转换成一个文档对象
        Document doc = saxReader.read(is);

        //4.获得根元素。对应于text.xml文件，这个root相当于 <beans> </beans>
        Element root = doc.getRootElement();

        //5.获得根元素下的所有子元素
        List<Element> elements = root.elements();

        // for (Object element : elements) {
        //     System.out.println(element);
        // }
        // org.dom4j.tree.DefaultElement@214c265e [Element: <bean uri: http://www.springframework.org/schema/beans attributes: [org.dom4j.tree.DefaultAttribute@448139f0 [Attribute: name name value "konnichiha"], org.dom4j.tree.DefaultAttribute@7cca494b [Attribute: name class value "com.huang.pojo.Konnichiha"]]/>]
        // org.dom4j.tree.DefaultElement@7ba4f24f [Element: <bean uri: http://www.springframework.org/schema/beans attributes: [org.dom4j.tree.DefaultAttribute@3b9a45b3 [Attribute: name name value "student"], org.dom4j.tree.DefaultAttribute@7699a589 [Attribute: name class value "com.huang.pojo.Student"]]/>]

        for (Element element : elements) {
            System.out.println(
                    element.attribute("name").getValue() +
                    " " +
                    element.attribute("class").getValue());
            List<Element> beanInfo = element.elements();
            for (Element field : beanInfo) {
                System.out.println(
                        "property:" +
                        field.attribute("name").getValue() +
                        " " +
                        field.attribute("value").getValue());
            }
            System.out.println();
        }
    }
}
