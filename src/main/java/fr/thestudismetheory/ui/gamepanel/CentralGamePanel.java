/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.gamepanel;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.School;
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
        cityView = new WorldCityGenerator();
        schoolPanel = new SchoolPanel(this, app);
        studentPanel = new StudentPanel(this , app);
        teacherPanel = new TeacherPanel(this, app);
        financePanel = new FinancePanel();

        switchDefaultPanel();
    }

    public void switchInstitution() {
        switchPanel(institutionView);
        institutionView.updatePanel();
    }

    public void switchSchool() {
        switchPanel(schoolPanel);
        schoolPanel.updatePanel();
    }

    public void switchStudent() {
        switchPanel(studentPanel);
        studentPanel.updatePanel();
    }

    public void switchTeacher() {
        switchPanel(teacherPanel);
        teacherPanel.updatePanel();
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
