package fr.thestudismetheory.ui.gamepanel;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.Category;
import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.School;
import fr.thestudismetheory.data.strings.UIConstants;
import sun.util.resources.cldr.ebu.CalendarData_ebu_KE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

/**
 * Fenetre permettant la gestion d'une école
 * Created by Maeva on 11/03/2016.
 */
public class SchoolPanel extends GamePanel {
    private TheStudismeTheory app;
    static final public String PANEL_ID = "SCHOOL";
    final private CentralGamePanel centralPanel;
    protected JPanel cards;
    protected List<School> schools_list = Collections.emptyList();
    protected List<Category> cat_list = Collections.emptyList();
    protected List<Division> pole_list = Collections.emptyList();
    protected JComboBox cb_schools;
    protected JComboBox cb_categories;
    protected JComboBox cb_pole;
    protected String CURRENT_CARD = UIConstants.BUTTON_ADD_POLE;
    protected JTextField divisionName;

    public SchoolPanel(CentralGamePanel centralGamePanel, final TheStudismeTheory app) {
        this.app = app;
        setLayout(new BorderLayout());

        this.centralPanel = centralGamePanel;

        //Ajout liste des écoles
        JPanel north = new JPanel();
        add(north, BorderLayout.NORTH);

        cb_schools = new JComboBox(schools_list.toArray());

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
                CURRENT_CARD = UIConstants.BUTTON_ADD_POLE;
            }
        });

        b_upgrade_pole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, UIConstants.BUTTON_UPGRADE_POLE);
                CURRENT_CARD = UIConstants.BUTTON_UPGRADE_POLE;
            }
        });

        b_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CURRENT_CARD == UIConstants.BUTTON_ADD_POLE) {
                    String name = divisionName.getText();
                    if (name.length() > 0) {
                        app.getDivisionHandler().addPole(app.getGameHandler().getCurrentGame(), name,(Category) cb_categories.getSelectedItem(),(School) cb_schools.getSelectedItem());
                        divisionName.setText("");
                    }
                }
                else if(CURRENT_CARD == UIConstants.BUTTON_UPGRADE_POLE){
                   // app.getSchoolHandler().deleteSchool(app.getGameHandler().getCurrentGame(), (School) cb_schools.getSelectedItem());
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

        JLabel poleNameLabel = new JLabel("Nom du pôle : ");
        divisionName = new JTextField(100);
        JLabel catLabel = new JLabel("Catégorie : ");
        cb_categories = new JComboBox(cat_list.toArray());

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
        formPanel.add(divisionName, gbc);
        formPanel.add(catLabel, gbc);
        formPanel.add(cb_categories, gbc);
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
        cb_pole = new JComboBox(pole_list.toArray());
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

    public void updatePanel(){
        schools_list = app.getGameHandler().getCurrentGame().getDAO().getSchoolDAO().getAll();
        cb_schools.removeAllItems();
        for(School school : schools_list)
            cb_schools.addItem(school);

        cat_list = app.getGameHandler().getCurrentGame().getDAO().getCategoryDAO().getAll();
        cb_categories.removeAllItems();
        for(Category category : cat_list)
            cb_categories.addItem(category);

        pole_list = app.getGameHandler().getCurrentGame().getDAO().getDivisionDAO().getAll();
        cb_pole.removeAllItems();
        for(Division division : pole_list)
            cb_pole.addItem(division);
    }
}
