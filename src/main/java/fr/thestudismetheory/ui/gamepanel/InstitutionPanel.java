package fr.thestudismetheory.ui.gamepanel;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.School;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Fenetre permettant la gestion de l'institution
 * Created by Maeva on 11/03/2016.
 */
public class InstitutionPanel extends GamePanel {
    private  TheStudismeTheory app;
    final static public String PANEL_ID = "INSTITUTION";
    final private CentralGamePanel centralPanel;
    protected JPanel cards;
    protected String CURRENT_CARD = UIConstants.BUTTON_NEW_SCHOOL;
    protected List<School> schools_list = Collections.emptyList();
    private JTextField schoolName;

    public InstitutionPanel(final CentralGamePanel parent, final TheStudismeTheory app) {
        this.app = app;

        setLayout(new BorderLayout());

        this.centralPanel = parent;

        //Ajout des bouttons
        JPanel west = new JPanel();
        add(west, BorderLayout.WEST);

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
        add(south, BorderLayout.SOUTH);

        JButton b_save = new JButton(UIConstants.BUTTON_VALIDATE);
        JButton b_cancel = new JButton(UIConstants.BUTTON_CANCEL);

        south.add(b_cancel);
        south.add(b_save);

        //Création des onglets
        cards = new JPanel(new CardLayout());

        cards.add(newSchoolPanel(), UIConstants.BUTTON_NEW_SCHOOL);
        cards.add(sellSchoolPanel(), UIConstants.BUTTON_SELL_SCHOOL);
        cards.add(grantPanel(), UIConstants.BUTTON_GRANT);

        add(cards, BorderLayout.CENTER);

        b_new_school.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, UIConstants.BUTTON_NEW_SCHOOL);
                CURRENT_CARD = UIConstants.BUTTON_NEW_SCHOOL;
            }
        });

        b_sell_school.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, UIConstants.BUTTON_SELL_SCHOOL);
                CURRENT_CARD = UIConstants.BUTTON_SELL_SCHOOL;
            }
        });

        b_grant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, UIConstants.BUTTON_GRANT);
                CURRENT_CARD = UIConstants.BUTTON_GRANT;
            }
        });

        b_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CURRENT_CARD == UIConstants.BUTTON_NEW_SCHOOL) {
                    String name = schoolName.getText();
                    if (name.length() > 0) {
                        app.getSchoolHandler().createSchool(app.getGameHandler().getCurrentGame(), name);
                        schoolName.setText("");
                    }
                }
                else if(CURRENT_CARD == UIConstants.BUTTON_SELL_SCHOOL){
                    app.getSchoolHandler().deleteSchool(app.getGameHandler().getCurrentGame(), (School) cb_schools.getSelectedItem());
                }
                centralPanel.switchDefaultPanel();
            }
        });

        b_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralPanel.switchDefaultPanel();
            }
        });
    }

    @Override
    public String getId() {
        return "INSITUTION";
    }

    public JPanel newSchoolPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel(UIConstants.BUTTON_NEW_SCHOOL);
        title.setFont(new Font("Verdana", 1, 20));

        north.add(title);

        JLabel cost = new JLabel(UIConstants.NEW_SCHOOL_COST);

        JLabel schoolNameLabel = new JLabel("Nom de l'école : ");
        schoolName = new JTextField(100);

        JPanel formPanel = new JPanel();

        panel.add(north, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15); // Taille et espacement des boutons
        formPanel.setLayout(new GridBagLayout());

        formPanel.add(schoolNameLabel, gbc);
        formPanel.add(schoolName, gbc);
        formPanel.add(cost, gbc);

        return panel;
    }

    protected JComboBox cb_schools;

    public JPanel sellSchoolPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel(UIConstants.BUTTON_SELL_SCHOOL);
        title.setFont(new Font("Verdana", 1, 20));

        north.add(title);

        JLabel cost = new JLabel(UIConstants.SELL_SCHOOL_COST);

        JLabel schoolNameLabel = new JLabel("Nom de l'école : ");
        cb_schools = new JComboBox(schools_list.toArray());

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

    public JPanel grantPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel(UIConstants.BUTTON_GRANT);
        title.setFont(new Font("Verdana", 1, 20));

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

    public void updatePanel(){
        schools_list = app.getGameHandler().getCurrentGame().getDAO().getSchoolDAO().getAll();

        cb_schools.removeAllItems();
        for(School school : schools_list)
            cb_schools.addItem(school);
    }
}
