package org.bean;

public class Monster {
    private Integer monsterID;
    private String name;
    private String skill;

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
