/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.subentity;

import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.Student;

/**
 * Réprésentation du graduate
 * N'est pas un modèle, car il ne réprésente qu'un élément de Student
 *
 * @author vincent
 */
public class Graduate {
    final static public int GRADE_MAX = 20;
    final static public int GRADUATE_IN_PROGRESS = -1;
    final static public int GRADUATE_FIRED = -2;

    final private Student student;
    final private int year;
    final private Division division;
    final private int level;
    private int grade;

    public Graduate(Student student, int year, Division division, int level, int grade) {
        this.student = student;
        this.year = year;
        this.division = division;
        this.level = level;
        this.grade = grade;
    }

    public int getYear() {
        return year;
    }

    public Division getDivision() {
        return division;
    }

    public int getLevel() {
        return level;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        assert grade >= GRADUATE_FIRED && grade <= GRADE_MAX;

        this.grade = grade;
        student.notifyUpdate();
    }

    public boolean isFired() {
        return grade == GRADUATE_FIRED;
    }

    public boolean isInProgress() {
        return grade == GRADUATE_IN_PROGRESS;
    }
}
