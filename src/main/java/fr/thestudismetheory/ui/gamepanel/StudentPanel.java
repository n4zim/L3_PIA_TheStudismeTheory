package fr.thestudismetheory.ui.gamepanel;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.School;
import fr.thestudismetheory.data.strings.UIConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.List;

/**
 * Fenetre permettant la gestion des étudiants d'une école
 * Created by Maeva on 11/03/2016.
 */
public class StudentPanel extends GamePanel {
    private TheStudismeTheory app;
    final static public String PANEL_ID = "STUDENT";
    final private CentralGamePanel gamePanel;
    protected JPanel cards;
    protected List<School> schools_list = Collections.emptyList();
    protected List<Division> pole_list = Collections.emptyList();
    Object[][] student = {
            {"Johnathan Sykes", "10/02/1995", 8, 10, "informatique"},
            {"Nicolas Van de Kampf", "5/03/1994", 3, 15, "sculpture de mehnir"},
            {"Damien Cuthbert", "8/04/1996", 10, 7, "pornographie"},
            {"Corinne Valance", "3/05/1992", 16, 9, "droit"},
            {"Emilie Schrödinger", "20/06/1993", 22, 12, "la sieste"},
            {"Delphine Duke", "9/07/1995", 42, 5, "veterinaire"},
            {"Eric Trump", "12/07/1994", 1, 9, "emeux"},
    };

    protected JComboBox cb_schools;
    protected JComboBox cb_poles;

    public StudentPanel(CentralGamePanel centralGamePanel, final TheStudismeTheory app) {
        this.app = app;
        setLayout(new BorderLayout());

        this.gamePanel = centralGamePanel;

        //Ajout liste des écoles
        JPanel north = new JPanel();
        add(north, BorderLayout.NORTH);

        cb_schools = new JComboBox(schools_list.toArray());
        cb_poles = new JComboBox(pole_list.toArray());

        cb_schools.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    School selectedSchool = (School) cb_schools.getSelectedItem();

                    pole_list = app.getGameHandler().getCurrentGame().getDAO().getDivisionDAO().getDivisionsBySchool(selectedSchool.getId());
                    cb_poles.removeAllItems();
                    for(Division division : pole_list)
                        cb_poles.addItem(division);
                }
            }
        });

        cb_poles.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();

                Object item = event.getItem();

                if (event.getStateChange() == ItemEvent.SELECTED) {
                    //int indexDivision = cb_poles.getSelectedIndex();
                    //Division selectedDivision = pole_list.get(indexDivision);


                }
            }
        });

        north.add(cb_schools);
        north.add(cb_poles);

        //Menu enregistrer et anuler
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(south, BorderLayout.SOUTH);

        JButton b_save = new JButton(UIConstants.BUTTON_VALIDATE);
        JButton b_cancel = new JButton(UIConstants.BUTTON_CANCEL);

        south.add(b_cancel);
        south.add(b_save);

        //Création des onglets
        cards = new JPanel(new CardLayout());

        cards.add(studentList(), "studentList");

        add(cards, BorderLayout.CENTER);

        b_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.switchDefaultPanel();
            }
        });

        b_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.switchDefaultPanel();
            }
        });
    }

    @Override
    public String getId() {
        return PANEL_ID;
    }

    public JPanel studentList() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel("Liste des étudiants");
        title.setFont(new Font("Verdana", 1, 20));

        north.add(title);

        //Liste des professeurs (table)
        String[] entetes = {"Nom", "Date de naissance", "Studisme", "Procrastination", "Interêt"};

        //Création d'une table non éditable
        final JTable tableau = new JTable();
        tableau.setModel(new DefaultTableModel(student, entetes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableau.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        center.add(tableau.getTableHeader());
        center.add(tableau);

        JButton b_fired = new JButton(UIConstants.BUTTON_FIRED);
        north.add(b_fired);

        b_fired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selected = tableau.getSelectedRows();
            }
        });


        panel.add(north, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);

        return panel;
    }

    protected void updatePanel(){
        schools_list = app.getGameHandler().getCurrentGame().getDAO().getSchoolDAO().getAll();
        cb_schools.removeAllItems();
        for(School school : schools_list)
            cb_schools.addItem(school);

        School selectedSchool = (School) cb_schools.getSelectedItem();
        pole_list = app.getGameHandler().getCurrentGame().getDAO().getDivisionDAO().getDivisionsBySchool(selectedSchool.getId());
        cb_poles.removeAllItems();
        for(Division division : pole_list)
            cb_poles.addItem(division);
    }
}
