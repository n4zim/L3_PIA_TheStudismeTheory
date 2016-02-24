/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

/**
 *
 * @author q13000412
 */
public class StudentGraduate extends AbstractModel<StudentGraduate>{
    final static public int NOT_GRADED_YET = -1;
    
    final private Student student;
    final private Division pole;
    final private int year;
    private int grade;

    public StudentGraduate(Student student, Division pole, int year, int grade) {
        this.student = student;
        this.pole = pole;
        this.year = year;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public Division getPole() {
        return pole;
    }

    public int getYear() {
        return year;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
        notifyUpdate();
    }
    
    public boolean isYearTerminated(){
        return grade != NOT_GRADED_YET;
    }
}
