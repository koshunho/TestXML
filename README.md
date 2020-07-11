## XML Test
### 解析XML
共有4种方式：
- DOM解析
- SAX解析
- JDOM解析
- DOM4J解析

前两种属于基础方法，是官方提供的与平台无关的解析方式。

后两种属于拓展方法，它们是在基础的方法之上拓展出来的，只适用于Java平台。

#### DOM解析
DOM解析的原理：解析XML的时候，把文档中的所有元素按照其出现的层次关系，在内存中构造出树形结构。

优点：
- 可以遍历和修改节点的内容

缺点：
- 内存压力较大，解析速度较慢

#### SAX解析
- 是一种XML解析的替代方法
- 相对于DOM解析，SAX是一种速度更快，更有效的方法
- 不能修改节点内容

#### JDOM解析
- 仅适用具体的类，而不用接口，不灵活

#### DOM4J解析
- JDOM的一种智能的分支，合并了许多超出基本XML文档的功能
- 著名的底层框架Hibernate就是使用DOM4J来解析

DOM4J性能最高，其次是SAX，DOM和JDOM表现不好（解析10MB大小的xml文件就内存溢出了）

### Xpath
望文生义 --> XML + 路径

- Xpath是一门在XML文档中快速查找信息的方式
- 单纯的使用DOM4J访问结点时，需要一层一层的处理，如果有了Xpath，访问层级的节点就简单了
- Xpath需要引包：jaxen-1.1-beta-7.jar

**注意**：Xpath在读取Spring配置文件时无法通过selectNodes或selectSingleNode获取节点，是因为配置文件带有命名空间。

固定模板。
```java
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
```