package fr.thestudismetheory.handler;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.Category;
import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.School;
import fr.thestudismetheory.data.enums.StudentFlaw;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.game.Game;

import javax.swing.*;
import java.util.HashSet;

/**
 * Created by Maëva on 08/04/2016.
 */
public class DivisionHandler {

    final private int seatsRate = 200;
    final private TheStudismeTheory app;

    public DivisionHandler(TheStudismeTheory app){
        this.app = app;

    }

    public void addPole(Game game, String name, Category cat, School school){
        int cost = UIConstants.NEW_POLE_COST;
        if(game.getGameData().getMoney() > cost) {
            HashSet<StudentFlaw> cond = new HashSet<>();
            cond.add(StudentFlaw.PROCRASTINATION);
            cond.add(StudentFlaw.TRAVAILLEUR);

            Division newDivision = new Division(school, cat, seatsRate, cost, cond);
            game.getDAO().getDivisionDAO().insert(newDivision);
            game.getGameData().setMoney(game.getGameData().getMoney() - cost);
        }
        else {
            JOptionPane.showMessageDialog(app.getMainWindow(), "Vous n'avez pas assez d'argent !", "Attention", JOptionPane.ERROR_MESSAGE);
        }
    }
}
