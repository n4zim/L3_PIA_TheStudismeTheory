package fr.thestudismetheory.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenetre permettant la gestion de l'institution
 * Created by Maeva on 11/03/2016.
 */
public class InstitutionWindow extends WindowConstants {

    protected JPanel cards;

   public InstitutionWindow(){
       super(UIConstants.TITLE_INSTITUTION);

       JPanel content = new JPanel();
       content.setLayout(new BorderLayout());

       //Ajout des bouttons
       JPanel west = new JPanel();
       content.add(west, BorderLayout.WEST);

       GridBagConstraints gbc = new GridBagConstraints();

       gbc.gridx = 0;
       gbc.gridy = GridBagConstraints.RELATIVE;
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.insets = new Insets(15, 15, 15, 15);
       west.setLayout(new GridBagLayout());

       JButton b_new_school = new JButton(UIConstants.BUTTON_NEW_SCHOOL);
       JButton b_sell_school = new JButton(UIConstants.BUTTON_SELL_SCHOOL);
       JButton b_grant = new JButton(UIConstants.BUTTON_GRANT);

       west.add(b_new_school, gbc);
       west.add(b_sell_school, gbc);
       west.add(b_grant, gbc);

       //Menu enregistrer et anuler
       JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
       content.add(south, BorderLayout.SOUTH);

       JButton b_save = new JButton(UIConstants.BUTTON_VALIDATE);
       JButton b_cancel = new JButton(UIConstants.BUTTON_CANCEL);

       south.add(b_cancel);
       south.add(b_save);

       //Création des onglets
       cards = new JPanel(new CardLayout());

       cards.add(newSchoolPanel(), UIConstants.BUTTON_NEW_SCHOOL);
       cards.add(sellSchoolPanel(), UIConstants.BUTTON_SELL_SCHOOL);
       cards.add(grantPanel(), UIConstants.BUTTON_GRANT);

       content.add(cards, BorderLayout.CENTER);

       b_new_school.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               CardLayout cl = (CardLayout)(cards.getLayout());
               cl.show(cards, UIConstants.BUTTON_NEW_SCHOOL);
           }
       });

       b_sell_school.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               CardLayout cl = (CardLayout)(cards.getLayout());
               cl.show(cards, UIConstants.BUTTON_SELL_SCHOOL);
           }
       });

       b_grant.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               CardLayout cl = (CardLayout)(cards.getLayout());
               cl.show(cards, UIConstants.BUTTON_GRANT);
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

    public JPanel newSchoolPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel(UIConstants.BUTTON_NEW_SCHOOL);
        title.setFont(new Font("Verdana",1,20));

        north.add(title);

        JLabel cost = new JLabel(UIConstants.NEW_SCHOOL_COST);

        JLabel schoolNameLabel = new JLabel("Nom de l'école : ");
        JTextField schoolName = new JTextField(30);

        JPanel formPanel = new JPanel();
        formPanel.add(schoolNameLabel);
        formPanel.add(schoolName);

        panel.add(north, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15); // Taille et espacement des boutons
        formPanel.setLayout(new GridBagLayout());

        formPanel.add(cost, gbc);

        return panel;
    }

    protected String[] schools_list = { "** Ecole 1 **", "** Ecole 2 **", "** Ecole 3 **", "** Ecole 4 **", "** Ecole 5 **" };

    public JPanel sellSchoolPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel(UIConstants.BUTTON_SELL_SCHOOL);
        title.setFont(new Font("Verdana",1,20));

        north.add(title);

        JLabel cost = new JLabel(UIConstants.SELL_SCHOOL_COST);

        JLabel schoolNameLabel = new JLabel("Nom de l'école : ");
        JComboBox cb_schools = new JComboBox(schools_list);

        JPanel formPanel = new JPanel();
        formPanel.add(schoolNameLabel);
        formPanel.add(cb_schools);

        panel.add(north, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15); // Taille et espacement des boutons
        formPanel.setLayout(new GridBagLayout());

        formPanel.add(cost, gbc);

        return panel;
    }

    public JPanel grantPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel(UIConstants.BUTTON_GRANT);
        title.setFont(new Font("Verdana",1,20));

        north.add(title);

        JButton cost = new JButton(UIConstants.GRANT_COST);

        JPanel formPanel = new JPanel();
        formPanel.add(cost);

        panel.add(north, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15); // Taille et espacement des boutons
        formPanel.setLayout(new GridBagLayout());

        return panel;
    }
}
