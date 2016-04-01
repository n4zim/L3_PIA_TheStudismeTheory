/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

import fr.thestudismetheory.data.enums.StudentFlaw;

import java.util.Set;

/**
 * @author q13000412
 */
public class Division extends AbstractModel<Division, ModelListener<Division>> {
    final private School school;
    final private Category category;
    final private Set<StudentFlaw> cond;
    private int seatsRate;
    private int cost;

    public Division(School school, Category category, int seatsRate, int cost, Set<StudentFlaw> cond) {
        this.school = school;
        this.category = category;
        this.seatsRate = seatsRate;
        this.cost = cost;
        this.cond = cond;
    }

    public School getSchool() {
        return school;
    }

    public Category getCategory() {
        return category;
    }

    public int getSeatsRate() {
        return seatsRate;
    }

    public void setSeatsRate(int seatsRate) {
        this.seatsRate = seatsRate;
        notifyUpdate();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
        notifyUpdate();
    }

    public Set<StudentFlaw> getCond() {
        return cond;
    }
}
