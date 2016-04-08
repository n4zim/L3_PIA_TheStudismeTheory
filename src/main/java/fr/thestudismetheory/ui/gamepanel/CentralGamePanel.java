/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.gamepanel;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.generation.CityGenerate;
import fr.thestudismetheory.data.generation.CountryGenerate;
import fr.thestudismetheory.generator.WorldCityGenerator;
import fr.thestudismetheory.generator.WorldCountryGenerator;

import javax.swing.*;
import java.awt.*;

/**
 * @author vincent
 */
public class CentralGamePanel extends JPanel {
    final private InstitutionPanel institutionView;
    final private WorldCountryGenerator countryView;
    final private SchoolPanel schoolPanel;
    final private StudentPanel studentPanel;
    final private TeacherPanel teacherPanel;
    final private FinancePanel financePanel;

    public CentralGamePanel(TheStudismeTheory app) {
        setLayout(new BorderLayout());
        institutionView = new InstitutionPanel(this, app);

        // Tests en cours
        countryView = new WorldCountryGenerator(this,new CountryGenerate(620, 450, 4));
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
        switchPanel(countryView);
    }

    public void switchCityPanel(int id) {
        switchPanel(countryView.getCity(id));
    }

    private void switchPanel(JPanel panel) {
        removeAll();
        add(panel);
        revalidate();
        repaint();
    }
}
