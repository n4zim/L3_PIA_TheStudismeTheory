/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

import fr.thestudismetheory.data.enums.InstitutionType;

/**
 * @author q13000412
 */
public class Institution extends AbstractModel<Institution> {
    final private int id;
    private String name;
    private InstitutionType type;

    public Institution(int id, String name, InstitutionType type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public InstitutionType getType() {
        return type;
    }

    public void setType(InstitutionType type) {
        this.type = type;
        notifyUpdate();
    }


}
