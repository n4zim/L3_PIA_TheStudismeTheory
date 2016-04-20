/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.handler;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.School;
import fr.thestudismetheory.data.Teacher;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.game.Game;

/**
 * @author vincent
 */
public class TeacherHandler {
    final private TheStudismeTheory app;

    public TeacherHandler(TheStudismeTheory app) {
        this.app = app;
    }

    public void paySalary(Game game) {

    }

    public void hireTeacher(Division division, Teacher teacher) {
        teacher.setDivision(division);
        
        teacher = app.getGameHandler().getCurrentGame().getDAO().getTeacherDAO().insert(teacher);
        
        System.out.println("DEBUG: hireTeacher " + teacher);

        //Mise à jour de l'argent
        long money = app.getGameHandler().getCurrentGame().getGameData().getMoney() - UIConstants.HIRE_TEACHER_COST;

        if (money < 0) {
            //  JOptionPane.showMessageDialog(app.getMainWindow(), "Vous n'avez plus d'argent !", "Attention", JOptionPane.ERROR_MESSAGE);
        }

        app.getGameHandler().getCurrentGame().getGameData().setMoney(money);
    }

    public void fireTeacher(Teacher teacher) {
        app.getGameHandler().getCurrentGame().getDAO().getTeacherDAO().delete(teacher);

        //Mise à jour de l'argent
        long money = app.getGameHandler().getCurrentGame().getGameData().getMoney() - UIConstants.FIRE_TEACHER_COST;

        if (money < 0) {
            //  JOptionPane.showMessageDialog(app.getMainWindow(), "Vous n'avez plus d'argent !", "Attention", JOptionPane.ERROR_MESSAGE);
        }

        app.getGameHandler().getCurrentGame().getGameData().setMoney(money);
    }
}
