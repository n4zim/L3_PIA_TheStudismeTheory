/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

/**
 * @author q13000412
 */
public class Category extends AbstractModel<Category, ModelListener<Category>> {
    final private int id;
    final private String name;
    private int attract;

    public Category(int id, String name, int attract) {
        this.id = id;
        this.name = name;
        this.attract = attract;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAttract() {
        return attract;
    }

    public void setAttract(int attract) {
        this.attract = attract;
        notifyUpdate();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
