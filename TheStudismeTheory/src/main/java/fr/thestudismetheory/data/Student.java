/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author q13000412
 */
public class Student extends AbstractModel<Student>{
    final private long id;
    final private String name;
    final private Date birth;
    private City city;
    private int studism;
    private int procrass;
    private int flaws;
    
    final private Map<Category, Integer> interests = new HashMap<>();

    public Student(long id, String name, Date birth, City city, int studism, int procrass, int flaws) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.city = city;
        this.studism = studism;
        this.procrass = procrass;
        this.flaws = flaws;
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

    public City getCity() {
        return city;
    }

    public int getStudism() {
        return studism;
    }

    public int getProcrass() {
        return procrass;
    }

    public int getFlaws() {
        return flaws;
    }

    public void setCity(City city) {
        this.city = city;
        notifyUpdate();
    }

    public void setStudism(int studism) {
        this.studism = studism;
        notifyUpdate();
    }

    public void setProcrass(int procrass) {
        this.procrass = procrass;
        notifyUpdate();
    }

    public void setFlaws(int flaws) {
        this.flaws = flaws;
        notifyUpdate();
    }
    
    public void setInterestRate(Category category, int interestRate){
        interests.put(category, interestRate);
        notifyUpdate();
    }
    
    public int getInterestRate(Category category){
        return interests.getOrDefault(category, 0);
    }
}
