package org.myioc;

import org.bean.Monster;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 这里只简单实现singletonObjects，复杂的beanDefinitionMap之后再说
 * @author Kyan27
 * @date 2024/09/25
 */
public class MyXmlApplicationContext {
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public MyXmlApplicationContext(String iocBeanXmlFile) throws Exception {
        // 1.得到类加载路径
        String path = this.getClass().getResource("/").getPath();
        // 2.解析配置文件
        SAXReader saxReader = new SAXReader();
        Document document = null;
        document = saxReader.read(new File(path + iocBeanXmlFile));
        Element rootElement = document.getRootElement();
        // 拿到monster01
        Element bean = (Element) rootElement.elements("bean").get(0);
        String id = bean.attributeValue("id");
        String aClass = bean.attributeValue("class");
        //System.out.println(id + " " + aClass);
        // 3.拿到配的属性
        List<Element> properties = bean.elements("property");
        String monsterID = properties.get(0).attributeValue("value");
        String name = properties.get(1).attributeValue("value");
        String skill = properties.get(2).attributeValue("value");
        //System.out.println(monsterID + " " + name + " " + skill);
        // 4.使用反射创建对象
        Class<?> aClass1 = Class.forName(aClass);
        // 这里说明了为什么一定要一个无参构造函数，因为是通过newInstance调用无参先创建一个对象
        Monster monster = (Monster) aClass1.newInstance();
        for (Method method : aClass1.getDeclaredMethods()) {
            if (method.getName().startsWith("setMonsterID")) {
                method.invoke(monster, Integer.parseInt(monsterID));
            } else if (method.getName().startsWith("setName")) {
                method.invoke(monster, name);
            } else if (method.getName().startsWith("setSkill")) {
                method.invoke(monster, skill);
            }
        }
        //System.out.println(monster);
        // 5.把创建好的对象放到ioc
        singletonObjects.put(id, monster);
    }

    public Object getBean(String id) {
        return singletonObjects.get(id);
    }
}
