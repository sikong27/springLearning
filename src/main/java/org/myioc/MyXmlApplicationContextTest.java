package org.myioc;

public class MyXmlApplicationContextTest {
    public static void main(String[] args) throws Exception {
        MyXmlApplicationContext context = new MyXmlApplicationContext("beans.xml");
        Object monster01 = context.getBean("monster01");
        System.out.println(monster01);

    }
}
