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

        app.getGameHandler().getCurrentGame().getDAO().getTeacherDAO().update(teacher);
    }
}
