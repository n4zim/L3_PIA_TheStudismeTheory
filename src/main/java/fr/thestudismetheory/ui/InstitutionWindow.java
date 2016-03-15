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

       JButton b_save = new JButton(UIConstants.BUTTON_SAVE);
       JButton b_cancel = new JButton(UIConstants.BUTTON_CANCEL);

       south.add(b_cancel);
       south.add(b_save);

       //Création des onglets
       cards = new JPanel(new CardLayout());

       cards.add(newSchoolPanel(), UIConstants.BUTTON_NEW_SCHOOL);
       cards.add(sellSchoolPanel(), UIConstants.BUTTON_SELL_SCHOOL);
       cards.add(grantPanel(), UIConstants.BUTTON_GRANT);

       cards.setBackground(Color.blue);
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

       this.setContentPane(content);
   }

    JPanel newSchoolPanel(){
        JPanel panel = new JPanel();
        panel.add(new JTextField("Carte création d'une école", 20));

        return panel;
    }

    JPanel sellSchoolPanel(){
        JPanel panel = new JPanel();
        panel.add(new JTextField("Carte vendre une école", 20));

        return panel;
    }

    JPanel grantPanel(){
        JPanel panel = new JPanel();
        panel.add(new JTextField("Carte demande de subvention", 20));

        return panel;
    }
}
