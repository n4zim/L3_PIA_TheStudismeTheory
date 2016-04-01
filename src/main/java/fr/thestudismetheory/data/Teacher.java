/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

import java.util.Date;

/**
 * @author q13000412
 */
public class Teacher extends AbstractModel<Teacher, ModelListener<Teacher>> {
    final private long id;
    final private String name;
    final private Date birth;
    final private Date entering;
    private int charisma;
    private int skill;
    private int punct;
    private int teachSkill;
    private Category category;
    private Division division;

    public Teacher(long id, String name, Date birth, Date entering, int charisma, int skill, int punct, int teachSkill, Category category, Division division) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.entering = entering;
        this.charisma = charisma;
        this.skill = skill;
        this.punct = punct;
        this.teachSkill = teachSkill;
        this.category = category;
        this.division = division;
    }

    public Teacher(long id, Teacher other) {
        this.id = id;
        name = other.name;
        birth = other.birth;
        entering = other.entering;
        charisma = other.charisma;
        skill = other.skill;
        punct = other.punct;
        teachSkill = other.teachSkill;
        category = other.category;
        division = other.division;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirth() {
        return birth;
    }

    public Date getEntering() {
        return entering;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
        notifyUpdate();
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
        notifyUpdate();
    }

    public int getPunct() {
        return punct;
    }

    public void setPunct(int punct) {
        this.punct = punct;
        notifyUpdate();
    }

    public int getTeachSkill() {
        return teachSkill;
    }

    public void setTeachSkill(int teachSkill) {
        this.teachSkill = teachSkill;
        notifyUpdate();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        notifyUpdate();
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
        notifyUpdate();
    }
}
