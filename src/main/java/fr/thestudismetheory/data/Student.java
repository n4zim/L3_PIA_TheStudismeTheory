/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

import fr.thestudismetheory.data.subentity.Graduate;
import fr.thestudismetheory.data.enums.StudentFlaw;
import fr.thestudismetheory.data.listener.StudentListener;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author q13000412
 */
public class Student extends AbstractModel<Student, StudentListener> {
    final private long id;
    final private String name;
    final private Date birth;
    final private Set<StudentFlaw> flaws;
    final private Map<Category, Integer> interests;
    final private Map<Integer, Graduate> graduations;
    private City city;
    private int studism;
    private int procrass;

    public Student(long id, String name, Date birth, City city, int studism, int procrass, Set<StudentFlaw> flaws, Map<Category, Integer> interests, Map<Integer, Graduate> graduations) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.city = city;
        this.studism = studism;
        this.procrass = procrass;
        this.flaws = flaws;
        this.interests = interests;
        this.graduations = graduations;
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

    public void setCity(City city) {
        this.city = city;
        notifyUpdate();
    }

    public int getStudism() {
        return studism;
    }

    public void setStudism(int studism) {
        this.studism = studism;
        notifyUpdate();
    }

    public int getProcrass() {
        return procrass;
    }

    public void setProcrass(int procrass) {
        this.procrass = procrass;
        notifyUpdate();
    }

    public Set<StudentFlaw> getFlaws() {
        return flaws;
    }

    public void addFlaw(StudentFlaw flaw) {
        flaws.add(flaw);
        notifyUpdate();
    }

    public void removeFlaw(StudentFlaw flaw) {
        flaws.remove(flaw);
        notifyUpdate();
    }

    public boolean hasFlaw(StudentFlaw flaw) {
        return flaws.contains(flaw);
    }

    public void setInterestRate(Category category, int interestRate) {
        interests.put(category, interestRate);
        notifyUpdate();
    }

    public int getInterestRate(Category category) {
        return interests.getOrDefault(category, 0);
    }

    public Graduate getGraduate(int year) {
        return graduations.get(year);
    }

    public void setGraduate(int year, Graduate graduate) {
        graduations.put(year, graduate);
    }
}
