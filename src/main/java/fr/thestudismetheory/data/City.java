/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

/**
 * @author q13000412
 */
public class City extends AbstractModel<City> {
    final private int id;
    private String name;
    private int studentsCount;

    public City(int id, String name, int studentsCount) {
        this.id = id;
        this.name = name;
        this.studentsCount = studentsCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyUpdate();
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
        notifyUpdate();
    }
}
