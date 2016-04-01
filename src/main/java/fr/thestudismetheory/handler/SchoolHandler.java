/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.handler;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.City;
import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.Institution;
import fr.thestudismetheory.data.School;
import fr.thestudismetheory.data.enums.InstitutionType;
import fr.thestudismetheory.game.Game;
import javax.swing.JOptionPane;
import java.util.List;

/**
 *
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
    
    public void paySchools(Game game){
        long price = 0;
        
        for(School school : game.getDAO().getSchoolDAO().getAll()){
            price += BASE_SCHOOL_COST_PER_MONTH;
            
            for(Division division : game.getDAO().getDivisionDAO().getAll()){
                price += division.getCost();
            }
        }
        
        long money = game.getGameData().getMoney() - price;
        
        if(money < 0){
            JOptionPane.showMessageDialog(app.getMainWindow(), "Vous n'avez plus d'argent !", "Attention", JOptionPane.ERROR_MESSAGE);
        }
        
        game.getGameData().setMoney(money);
    }

    public void createSchool(Game game, String name){
        School newSchool = new School(-1, city, institution, name, 0, 0, 200);
        game.getDAO().getSchoolDAO().insert(newSchool);
    }
}
