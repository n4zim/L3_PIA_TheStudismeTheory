/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.handler;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.*;
import fr.thestudismetheory.data.enums.InstitutionType;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.game.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vincent
 */
public class SchoolHandler {
    final static public int BASE_SCHOOL_COST_PER_MONTH = 1000;

    final private TheStudismeTheory app;
    final private City city;
    final private Institution institution;

    public SchoolHandler(TheStudismeTheory app) {
        this.app = app;
        this.city = new City(-1, "City Cool", 42);
        this.institution = new Institution(-1, "Institut Lol", InstitutionType.PUBLIC);
    }

    public void paySchools(Game game) {
        long price = 0;

        for (School school : game.getDAO().getSchoolDAO().getAll()) {
            price += BASE_SCHOOL_COST_PER_MONTH;

            for (Division division : game.getDAO().getDivisionDAO().getAll()) {
                price += division.getCost();
            }
        }

        long money = game.getGameData().getMoney() - price;

        if (money < 0) {
            JOptionPane.showMessageDialog(app.getMainWindow(), "Vous n'avez plus d'argent !", "Attention", JOptionPane.ERROR_MESSAGE);
        }

        game.getGameData().setMoney(money);
    }

    public void createSchool(Game game, String name) {
        int cost = UIConstants.NEW_SCHOOL_PRICE;
        if(game.getGameData().getMoney() > cost) {
            School newSchool = new School(Model.ID_NOT_DEFINED, city, institution, name, 0, cost, 200);
            game.getDAO().getSchoolDAO().insert(newSchool);
            game.getGameData().setMoney(game.getGameData().getMoney() - cost);
        }
        else {
            JOptionPane.showMessageDialog(app.getMainWindow(), "Vous n'avez pas assez d'argent !", "Attention", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteSchool(Game game, School school){
        int income = UIConstants.SELL_SCHOOL_PRICE;
        game.getDAO().getSchoolDAO().delete(school);
        game.getGameData().setMoney(game.getGameData().getMoney() + income);
    }
}
