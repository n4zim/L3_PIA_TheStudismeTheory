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
 * Fenetre permettant la gestion des professeurs d'une école
 * Created by Maeva on 11/03/2016.
 */
public class TeacherPanel extends GamePanel {
    private TheStudismeTheory app;
    final static public String PANEL_ID = "TEACHER";
    final private CentralGamePanel gamePanel;
    protected JPanel cards;
    protected List<School> schools_list = Collections.emptyList(); // initialisation liste vide
    protected List<Division> pole_list = Collections.emptyList();
    private Object[][] teachers = {
            {"Johnathan", "Sykes", 10, 8, 10, 8},
            {"Nicolas", "Van de Kampf", 5, 3, 15, 5},
            {"Damien", "Cuthbert", 8, 10, 7, 20},
            {"Corinne", "Valance", 3, 16, 9, 30},
            {"Emilie", "Schrödinger", 20, 22, 12, 14},
            {"Delphine", "Duke", 9, 42, 5, 19},
            {"Eric", "Trump", 12, 1, 9, 1},
    };
    private JTable tableau = new JTable();
    protected JComboBox cb_schools;
    protected JComboBox cb_poles;


    public TeacherPanel(CentralGamePanel centralGamePanel, final TheStudismeTheory app) {
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

        cards.add(teacherList(), "teacherList");

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

    public JPanel teacherList() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel("Liste des professeurs");
        title.setFont(new Font("Verdana", 1, 20));

        north.add(title);

        //Liste des professeurs (table)
        String[] entetes = {"Prénom", "Nom", "Charisme", "Skill", "Ponctualité", "Compétence d'apprentissage"};

        //Création d'une table non éditable
        tableau.setModel(new DefaultTableModel(teachers, entetes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton b_engager = new JButton("Engager le professeur sélectionné");

        b_engager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = tableau.getSelectedRow();

                //Sélection correcte
                if (select >= 0) {
                    System.out.println(select);

                }
            }
        });

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        center.add(tableau.getTableHeader());
        center.add(tableau);
        center.add(b_engager);

        panel.add(north, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);

        return panel;
    }

    protected void updatePanel(){
        //ComboBox Ecoles
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
