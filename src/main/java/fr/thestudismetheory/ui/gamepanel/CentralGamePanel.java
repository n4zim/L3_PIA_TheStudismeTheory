/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.gamepanel;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.generation.CityGenerate;
import fr.thestudismetheory.generator.WorldCityGenerator;

import javax.swing.*;
import java.awt.*;

/**
 * @author vincent
 */
public class CentralGamePanel extends JPanel {
    final private InstitutionPanel institutionView;
    final private WorldCityGenerator cityView;
    final private SchoolPanel schoolPanel;
    final private StudentPanel studentPanel;
    final private TeacherPanel teacherPanel;
    final private FinancePanel financePanel;

    public CentralGamePanel(TheStudismeTheory app) {
        setLayout(new BorderLayout());
        institutionView = new InstitutionPanel(this, app);

        // Tests en cours
        cityView = new WorldCityGenerator(new CityGenerate(20, 5));

        schoolPanel = new SchoolPanel(this);
        studentPanel = new StudentPanel(this);
        teacherPanel = new TeacherPanel(this);
        financePanel = new FinancePanel();

        switchDefaultPanel();
    }

    public void switchInstitution() {
        switchPanel(institutionView);
    }

    public void switchSchool() {
        switchPanel(schoolPanel);
    }

    public void switchStudent() {
        switchPanel(studentPanel);
    }

    public void switchTeacher() {
        switchPanel(teacherPanel);
    }

    public void switchFinance() {
        switchPanel(financePanel);
    }

    public void switchDefaultPanel() {
        switchPanel(cityView);
    }

    private void switchPanel(JPanel panel) {
        removeAll();
        add(panel);
        revalidate();
        repaint();
    }
}
