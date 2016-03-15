package fr.thestudismetheory.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenetre permettant la gestion d'une école
 * Created by Maeva on 11/03/2016.
 */
public class SchoolWindow extends WindowConstants {

    protected JPanel cards;
    protected String[] schools_list = { "** Ecole 1 **", "** Ecole 2 **", "** Ecole 3 **", "** Ecole 4 **", "** Ecole 5 **" };

    public SchoolWindow() {
        super(UIConstants.TITLE_SCHOOL);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        //Ajout liste des écoles
        JPanel north = new JPanel();
        content.add(north, BorderLayout.NORTH);

        JComboBox cb_schools = new JComboBox(schools_list);

        north.add(cb_schools);

        //Ajout des bouttons
        JPanel west = new JPanel();
        content.add(west, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);
        west.setLayout(new GridBagLayout());

        JButton b_add_pole = new JButton(UIConstants.BUTTON_ADD_POLE);
        JButton b_upgrade_pole = new JButton(UIConstants.BUTTON_UPGRADE_POLE);

        west.add(b_add_pole, gbc);
        west.add(b_upgrade_pole, gbc);

        //Menu enregistrer et anuler
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        content.add(south, BorderLayout.SOUTH);

        JButton b_save = new JButton(UIConstants.BUTTON_SAVE);
        JButton b_cancel = new JButton(UIConstants.BUTTON_CANCEL);

        south.add(b_cancel);
        south.add(b_save);

        //Création des onglets
        cards = new JPanel(new CardLayout());

        cards.add(addPole(), UIConstants.BUTTON_ADD_POLE);
        cards.add(upgradePole(), UIConstants.BUTTON_UPGRADE_POLE);

        content.add(cards, BorderLayout.CENTER);

        b_add_pole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, UIConstants.BUTTON_ADD_POLE);
            }
        });

        b_upgrade_pole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, UIConstants.BUTTON_UPGRADE_POLE);
            }
        });

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

    public JPanel addPole(){
        JPanel panel = new JPanel();
        panel.add(new JTextField("Carte ajouter un pôle", 20));

        return panel;
    }

    public JPanel upgradePole(){
        JPanel panel = new JPanel();
        panel.add(new JTextField("Carte améliorer un pôle", 20));

        return panel;
    }
}
