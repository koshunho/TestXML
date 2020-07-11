package com.huang;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class Xpath {
    public static void main(String[] args) throws Exception{
        InputStream is = XmlParse.class.getClassLoader().getResourceAsStream("test.xml");

        SAXReader saxReader = new SAXReader();

        Document doc = saxReader.read(is);

        HashMap<String, String> hm = new HashMap<String, String>();

        hm.put("ns","http://www.springframework.org/schema/beans");

        XPath xPath = doc.createXPath("//ns:beans/ns:bean");

        xPath.setNamespaceURIs(hm);

        List<Element> beans = xPath.selectNodes(doc);

        System.out.println(beans.size());

        for (Element bean : beans) {
            String id = bean.attributeValue("name");
            String clazz = bean.attributeValue("class");
            System.out.println("id-->"+id+" class-->"+clazz);
            XPath propertyXpath = bean.createXPath("ns:property");
            propertyXpath.setNamespaceURIs(hm);
            List<Element> properties = propertyXpath.selectNodes(bean);
            for(Element property: properties) {
                String propertyName = property.attributeValue("name");
                String propertyValue = property.attributeValue("value");
                System.out.println("propertyName-->" + propertyName + " propertyValue-->" + propertyValue);
            }
            System.out.println();
        }
    }
}
