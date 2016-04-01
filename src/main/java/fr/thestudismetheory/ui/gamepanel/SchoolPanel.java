package fr.thestudismetheory.ui.gamepanel;

import fr.thestudismetheory.data.strings.UIConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenetre permettant la gestion d'une école
 * Created by Maeva on 11/03/2016.
 */
public class SchoolPanel extends GamePanel {
    static final public String PANEL_ID = "SCHOOL";

    protected JPanel cards;
    protected String[] schools_list = {"** Ecole 1 **", "** Ecole 2 **", "** Ecole 3 **", "** Ecole 4 **", "** Ecole 5 **"};
    protected String[] cat_list = {"** Informatique **", "** Sport **", "** Médecine **", "** Science **", "** Ninja **"};
    protected String[] pole_list = {"** Pole 1 **", "** Pole 2 **", "** Pole 3 **", "** Pole 4 **", "** Pole 5 **"};
    
    final private CentralGamePanel centralPanel;

    public SchoolPanel(CentralGamePanel centralGamePanel) {
        setLayout(new BorderLayout());
        
        this.centralPanel = centralGamePanel;

        //Ajout liste des écoles
        JPanel north = new JPanel();
        add(north, BorderLayout.NORTH);

        JComboBox cb_schools = new JComboBox(schools_list);

        north.add(cb_schools);

        //Ajout des bouttons
        JPanel west = new JPanel();
        add(west, BorderLayout.WEST);

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
        add(south, BorderLayout.SOUTH);

        JButton b_save = new JButton(UIConstants.BUTTON_VALIDATE);
        JButton b_cancel = new JButton(UIConstants.BUTTON_CANCEL);

        south.add(b_cancel);
        south.add(b_save);

        //Création des onglets
        cards = new JPanel(new CardLayout());

        cards.add(addPole(), UIConstants.BUTTON_ADD_POLE);
        cards.add(upgradePole(), UIConstants.BUTTON_UPGRADE_POLE);

        add(cards, BorderLayout.CENTER);

        b_add_pole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, UIConstants.BUTTON_ADD_POLE);
            }
        });

        b_upgrade_pole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, UIConstants.BUTTON_UPGRADE_POLE);
            }
        });

        b_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        return PANEL_ID;
    }

    public JPanel addPole() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel(UIConstants.BUTTON_ADD_POLE);
        title.setFont(new Font("Verdana", 1, 20));

        north.add(title);

        JLabel cost = new JLabel(UIConstants.ADD_POLE_COST);

        JLabel poleNameLabel = new JLabel("Nom de l'école : ");
        JTextField poleName = new JTextField(30);
        JLabel catLabel = new JLabel("Catégorie : ");
        JComboBox catName = new JComboBox(cat_list);

        JPanel formPanel = new JPanel();

        panel.add(north, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15); // Taille et espacement des boutons
        formPanel.setLayout(new GridBagLayout());

        formPanel.add(poleNameLabel, gbc);
        formPanel.add(poleName, gbc);
        formPanel.add(catLabel, gbc);
        formPanel.add(catName, gbc);
        formPanel.add(cost, gbc);

        return panel;
    }

    public JPanel upgradePole() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel north = new JPanel();

        JLabel title = new JLabel(UIConstants.BUTTON_UPGRADE_POLE);
        title.setFont(new Font("Verdana", 1, 20));

        north.add(title);

        JButton cost = new JButton(UIConstants.UPGRADE_POLE_COST);

        JLabel poleListLabel = new JLabel("Pôle : ");
        JComboBox cb_pole = new JComboBox(pole_list);
        JLabel description = new JLabel(UIConstants.UPGRADE_POLE_DESC);

        JPanel formPanel = new JPanel();

        panel.add(north, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15); // Taille et espacement des boutons
        formPanel.setLayout(new GridBagLayout());

        formPanel.add(poleListLabel, gbc);
        formPanel.add(cb_pole, gbc);
        formPanel.add(description, gbc);
        formPanel.add(cost, gbc);

        return panel;
    }
}
