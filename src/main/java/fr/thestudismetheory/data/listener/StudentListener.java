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
 *
 * @author vincent
 */
public interface StudentListener extends ModelListener<Student>{
    public void onInterestChange(Student student, Category category, int rate);
    public void onNewGraduation(Student student, Graduate graduate);
}
