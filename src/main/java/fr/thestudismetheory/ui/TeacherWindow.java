package fr.thestudismetheory.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenetre permettant la gestion des professeurs d'une école
 * Created by Maeva on 11/03/2016.
 */
public class TeacherWindow extends WindowConstants {

    protected JPanel cards;
    protected String[] schools_list = {"** Ecole 1 **", "** Ecole 2 **", "** Ecole 3 **", "** Ecole 4 **", "** Ecole 5 **"};
    protected String[] pole_list = {"** Pole 1 **", "** Pole 2 **", "** Pole 3 **", "** Pole 4 **", "** Pole 5 **"};

    public TeacherWindow() {
        super(UIConstants.TITLE_TEACHER);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        //Ajout liste des écoles
        JPanel north = new JPanel();
        content.add(north, BorderLayout.NORTH);

        JComboBox cb_schools = new JComboBox(schools_list);
        JComboBox cb_poles = new JComboBox(pole_list);

        north.add(cb_schools);
        north.add(cb_poles);

        //Menu enregistrer et anuler
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        content.add(south, BorderLayout.SOUTH);

        JButton b_save = new JButton(UIConstants.BUTTON_VALIDATE);
        JButton b_cancel = new JButton(UIConstants.BUTTON_CANCEL);

        south.add(b_cancel);
        south.add(b_save);

        //Création des onglets
        cards = new JPanel(new CardLayout());

        cards.add(teacherList(), "teacherList");

        content.add(cards, BorderLayout.CENTER);

        b_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        b_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        this.setContentPane(content);
    }

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

    public JPanel teacherList(){
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
                if(select >= 0)
                {
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
}
