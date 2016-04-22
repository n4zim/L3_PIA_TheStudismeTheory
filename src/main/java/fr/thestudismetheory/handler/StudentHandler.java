/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.handler;

import fr.thestudismetheory.Constants;
import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.game.Game;

/**
 * @author vincent
 */
public class StudentHandler {
    final private TheStudismeTheory app;

    public StudentHandler(TheStudismeTheory app) {
        this.app = app;
    }

    public void passExams(Game game) {
        //TODO
    }

    public void onEndYear(Game game) {
        //TODO
        
        int nbStu = game.getDAO().getStudentDAO().getAll().size();
        game.getGameData().setMoney(game.getGameData().getMoney() + nbStu * Constants.MONEY_PER_STUDENT);
    }
}
