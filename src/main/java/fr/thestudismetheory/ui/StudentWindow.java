package fr.thestudismetheory.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenetre permettant la gestion des étudiants d'une école
 * Created by Maeva on 11/03/2016.
 */
public class StudentWindow extends WindowConstants {

    protected JPanel cards;
    protected String[] schools_list = {"** Ecole 1 **", "** Ecole 2 **", "** Ecole 3 **", "** Ecole 4 **", "** Ecole 5 **"};
    protected String[] pole_list = {"** Pole 1 **", "** Pole 2 **", "** Pole 3 **", "** Pole 4 **", "** Pole 5 **"};

    public StudentWindow() {

        super(UIConstants.TITLE_STUDENT);
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

        cards.add(studentList(), "studentList");

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

    Object[][] student = {
            {"Johnathan Sykes", "10/02/1995", 8, 10, "informatique"},
            {"Nicolas Van de Kampf", "5/03/1994", 3, 15, "sculpture de mehnir"},
            {"Damien Cuthbert", "8/04/1996", 10, 7, "pornographie"},
            {"Corinne Valance", "3/05/1992", 16, 9, "droit"},
            {"Emilie Schrödinger", "20/06/1993", 22, 12, "la sieste"},
            {"Delphine Duke", "9/07/1995", 42, 5, "veterinaire"},
            {"Eric Trump", "12/07/1994", 1, 9, "emeux"},
    };

    public JPanel studentList(){
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
                int[] selected =  tableau.getSelectedRows();
            }
        });


        panel.add(north, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);

        return panel;
    }
}
