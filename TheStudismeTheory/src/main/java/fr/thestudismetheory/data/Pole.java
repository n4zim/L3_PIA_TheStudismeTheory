/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

/**
 *
 * @author q13000412
 */
public class Pole extends AbstractModel<Pole>{
    final private School school;
    final private Category category;
    private int seatsRate;
    private int cost;
    private int cond;

    public Pole(School school, Category category, int seatsRate, int cost, int cond) {
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

    public int getCost() {
        return cost;
    }

    public int getCond() {
        return cond;
    }

    public void setSeatsRate(int seatsRate) {
        this.seatsRate = seatsRate;
        notifyUpdate();
    }

    public void setCost(int cost) {
        this.cost = cost;
        notifyUpdate();
    }

    public void setCond(int cond) {
        this.cond = cond;
        notifyUpdate();
    }
    
    
}
