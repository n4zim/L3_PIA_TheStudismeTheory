/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.listener;

import fr.thestudismetheory.data.Category;
import fr.thestudismetheory.data.ModelListener;
import fr.thestudismetheory.data.Student;
import fr.thestudismetheory.data.subentity.Graduate;

/**
 * Listener pour un étudiant
 *
 * @author vincent
 */
public interface StudentListener extends ModelListener<Student> {
    /**
     * Évènement envoyé quand un étudiant change d'intérêt
     *
     * @param student
     * @param category
     * @param rate
     */
    public void onInterestChange(Student student, Category category, int rate);

    /**
     * Évènement envoyé quand un étudiant commence de nouvelles études
     *
     * @param student
     * @param graduate
     */
    public void onNewGraduation(Student student, Graduate graduate);
}
