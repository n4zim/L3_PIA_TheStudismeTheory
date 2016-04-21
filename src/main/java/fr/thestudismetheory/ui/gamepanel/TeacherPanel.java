package fr.thestudismetheory.ui.gamepanel;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.School;
import fr.thestudismetheory.data.Teacher;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.generator.PeopleGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    protected JComboBox cb_schools;
    protected JComboBox cb_poles;

    /* Tableaux */
    protected Object[] columnNames = {"Nom", "Charisme", "Skill", "Ponctualité", "Compétence d'apprentissage"};

    //Professeurs disponibles
    private JTable tabHire = new JTable();
    protected DefaultTableModel modelTableHire;
    protected List<Teacher> teachersHire = Collections.emptyList();

    //Professeurs engagés
    private JTable tabFire = new JTable();
    protected DefaultTableModel modelTableFire;
    protected List<Teacher> teachersFire = Collections.emptyList();

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

                    Division selectedDivision = (Division) cb_poles.getSelectedItem();

                    updateTeachersListByDivision(selectedDivision);
                }
            }
        });

        cb_poles.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();

                Object item = event.getItem();

                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Division selectedDivision = (Division) cb_poles.getSelectedItem();

                    updateTeachersListByDivision(selectedDivision);
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

        JLabel title = new JLabel("Liste des professeurs disponibles");
        title.setFont(new Font("Verdana", 1, 20));

        north.add(title);

        //Liste des professeurs à engager (table)
        modelTableHire = new DefaultTableModel(new Object[0][0], columnNames);
        tabHire.setModel(modelTableHire);
        tabHire.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Liste des professeurs à renvoyer (table)
        modelTableFire = new DefaultTableModel(new Object[0][0], columnNames);
        tabFire.setModel(modelTableFire);
        tabFire.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton b_engager = new JButton(UIConstants.BUTTON_HIRE_TEACHER);

        b_engager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teachersFire.size() <= 0) {
                    int select = tabHire.getSelectedRow();

                    //Sélection correcte
                    if (select >= 0) {
                        School selectedSchool = (School) cb_schools.getSelectedItem();
                        Division selectedDivision = (Division) cb_poles.getSelectedItem();

                        if (selectedSchool != null && selectedDivision != null) {
                            Teacher selectedTeacher = teachersHire.get(select);

                            app.getTeacherHandler().hireTeacher(selectedDivision, selectedTeacher);
                            teachersHire.remove(select);
                            modelTableHire.removeRow(select);
                            modelTableHire.fireTableStructureChanged();

                            updateTeachersListByDivision(selectedDivision);
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(app.getMainWindow(), "Vous avez déjà engagé un professeur dans ce pôle !", "Attention", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JLabel titleFire = new JLabel("Liste des professeurs du pôle sélectionné");
        titleFire.setFont(new Font("Verdana", 1, 20));

        JButton b_renvoyer = new JButton(UIConstants.BUTTON_FIRE_TEACHER);

        b_renvoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = tabFire.getSelectedRow();

                //Sélection correcte
                if (select >= 0) {
                    School selectedSchool = (School) cb_schools.getSelectedItem();
                    Division selectedDivision = (Division) cb_poles.getSelectedItem();

                    if(selectedSchool != null && selectedDivision != null)
                    {
                        Teacher selectedTeacher = teachersFire.get(select);

                        app.getTeacherHandler().fireTeacher(selectedTeacher);
                        teachersFire.remove(select);
                        modelTableFire.removeRow(select);
                        modelTableFire.fireTableStructureChanged();

                        updateTeachersListByDivision(selectedDivision);
                    }
                }
            }
        });

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        center.add(tabHire.getTableHeader());
        center.add(tabHire);
        center.add(b_engager);
        center.add(titleFire);
        center.add(tabFire.getTableHeader());
        center.add(tabFire);
        center.add(b_renvoyer);

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

        Division selectedDivision = (Division) cb_poles.getSelectedItem();

        if(selectedDivision != null)
        {
            //Mise à jour de la liste des professerus à engager
            teachersHire = Collections.emptyList();
            teachersHire = PeopleGenerator.newTeacherList(10, new Date(0));

            modelTableHire = new DefaultTableModel(new Object[0][0], columnNames);
            for (Teacher value : teachersHire) {
                Object[] o = new Object[5];
                o[0] = value.getName();
                o[1] = value.getCharisma();
                o[2] = value.getSkill();
                o[3] = value.getPunct();
                o[4] = value.getTeachSkill();
                modelTableHire.addRow(o);
            }
            tabHire.setModel(modelTableHire);
            modelTableHire.fireTableStructureChanged();

            updateTeachersListByDivision(selectedDivision);
        }
    }

    public void updateTeachersListByDivision(Division division){
        if(division != null) {
            //Mise à jour de la liste des professeurs du pôle selectionné
            teachersFire = Collections.emptyList();
            teachersFire = app.getGameHandler().getCurrentGame().getDAO().getTeacherDAO().getTeachersByDivision(division);

            modelTableFire = new DefaultTableModel(new Object[0][0], columnNames);
            for (Teacher value : teachersFire) {
                Object[] o = new Object[5];
                o[0] = value.getName();
                o[1] = value.getCharisma();
                o[2] = value.getSkill();
                o[3] = value.getPunct();
                o[4] = value.getTeachSkill();
                modelTableFire.addRow(o);
            }
            tabFire.setModel(modelTableFire);
            modelTableFire.fireTableStructureChanged();
        }
    }
}
