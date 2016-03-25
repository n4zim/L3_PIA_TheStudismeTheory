/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

/**
 * @author q13000412
 */
public class School extends AbstractModel<School, ModelListener<School>> {
    final private int id;
    final private City city;
    final private Institution institution;
    private String name;
    private int repute;
    private int cost;
    private int seats;

    public School(int id, City city, Institution institution, String name, int repute, int cost, int seats) {
        this.id = id;
        this.city = city;
        this.institution = institution;
        this.name = name;
        this.repute = repute;
        this.cost = cost;
        this.seats = seats;
    }

    public School(int id, School school) {
        this.id = id;
        city = school.city;
        institution = school.institution;
        name = school.name;
        repute = school.repute;
        cost = school.cost;
        seats = school.seats;
    }

    public int getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public Institution getInstitution() {
        return institution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyUpdate();
    }

    public int getRepute() {
        return repute;
    }

    public void setRepute(int repute) {
        this.repute = repute;
        notifyUpdate();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
        notifyUpdate();
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
        notifyUpdate();
    }


}
