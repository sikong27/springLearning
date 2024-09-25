package org.bean;

public class Monster {
    private Integer monsterID;
    private String name;
    private String skill;

    private void init() {
        System.out.println("Monster init...");
    }

    private void destroy() {
        System.out.println("Monster destroy...");
    }


    /**
     * 必须给无参构造，context底层使用无参构造函数，通过反射创建对象
     */
    public Monster() {
    }

    public Monster(Integer monsterID, String name, String skill) {
        this.monsterID = monsterID;
        this.name = name;
        this.skill = skill;
    }

    public Integer getMonsterID() {
        return monsterID;
    }


    /**
     * xml方式配置bean必须给set方法，底层通过set完成赋值
     * @param monsterID
     */
    public void setMonsterID(Integer monsterID) {
        this.monsterID = monsterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Mosnter{" +
                "monsterID=" + monsterID +
                ", name='" + name + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
