package org.factory;

import org.bean.Monster;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

public class MyFactoryBean implements FactoryBean<Monster> {

    private String key;
    private Map<String, Monster> monsterMap;

    {
        monsterMap = new HashMap<>();
        monsterMap.put("monster01", new Monster(1, "小妖怪", "巡山的"));
        monsterMap.put("monster02", new Monster(2, "大妖怪", "抓人的"));
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Monster getObject() throws Exception {
        return monsterMap.get(key);
    }

    @Override
    public Class<?> getObjectType() {
        return Monster.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
