package fr.thestudismetheory.ui;

/**
 * Created by Maeva on 14/03/2016.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


/*
 * Une application juste pour montrer l'interface de la boîte de saisie
 */
public class CadreSaisieMembre extends JFrame {

    public static void main(String[] args) {
        JFrame cadre = new CadreSaisieMembre("Test du cadre de saisie d'un membre");
        cadre.setLocationRelativeTo(null);
        cadre.setVisible(true);
    }

    public CadreSaisieMembre(String titre) {
        super(titre);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panneauDeContenu());
        pack();
    }

    /*
     * Construction de la zone de contenu (c.-à-d. tout sauf le bord) de la boîte de dialogue
     */
    JPanel panneauDeContenu() {
        JPanel panneau = new JPanel();

        /*
         * on dispose les panneaux et sous-panneaux
         */
        panneau.setLayout(new BorderLayout());
        JPanel grandCentre = new JPanel();
        JPanel grandEst = new JPanel();
        JPanel grandSud = new JPanel();
        panneau.add(grandCentre, BorderLayout.CENTER);
        panneau.add(grandEst, BorderLayout.EAST);
        panneau.add(grandSud, BorderLayout.SOUTH);

        grandCentre.setLayout(new BorderLayout());
        JPanel petitNord = new JPanel();
        JPanel petitCentre = new JPanel();
        JPanel petitSud = new JPanel();
        grandCentre.add(petitNord, BorderLayout.NORTH);
        grandCentre.add(petitCentre, BorderLayout.CENTER);
        grandCentre.add(petitSud, BorderLayout.SOUTH);

        /*
         * on y met les composants dedans
         */
        petitNord.setLayout(new GridLayout(0, 1));
        petitNord.add(new JLabel("Nom"));
        JTextField champDeTexte = new JTextField();
        petitNord.add(champDeTexte);
        petitNord.add(new JLabel("Prénom"));
        champDeTexte = new JTextField();
        petitNord.add(champDeTexte);
        petitNord.add(new JLabel("Adresse"));

        petitCentre.setLayout(new BorderLayout());
        JTextArea zoneDeTexte = new JTextArea();
        petitCentre.add(new JScrollPane(zoneDeTexte));

        petitSud.setLayout(new FlowLayout());
        petitSud.add(new JLabel("Sexe"));
        ButtonGroup groupe = new ButtonGroup();
        JRadioButton boutonRadio = new JRadioButton("Homme");
        groupe.add(boutonRadio);
        petitSud.add(boutonRadio);
        boutonRadio.setSelected(true);
        boutonRadio = new JRadioButton("Femme");
        groupe.add(boutonRadio);
        petitSud.add(boutonRadio);

        grandSud.setLayout(new FlowLayout());
        JButton bouton1 = new JButton("OK");
        grandSud.add(bouton1);
        JButton bouton2 = new JButton("Annuler");
        grandSud.add(bouton2);
        bouton1.setPreferredSize(bouton2.getPreferredSize());

        grandEst.setLayout(new GridLayout(0, 1));
        JCheckBox[] boiteACocher = new JCheckBox[Sports.NOMBRE];
        for (int i = 0; i < Sports.NOMBRE; i++) {
            boiteACocher[i] = new JCheckBox(Sports.values()[i].toString());
            grandEst.add(boiteACocher[i]);
        }

        panneau.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        /*
         * Pour la question 1.2 (à propos des bordures) mettre true ci-dessous:
         */
        if (true) {
            grandCentre.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            grandEst.setBorder(BorderFactory.createCompoundBorder(BorderFactory
                    .createEmptyBorder(0, 5, 0, 0), BorderFactory
                    .createTitledBorder(
                            BorderFactory.createLineBorder(Color.BLUE, 1),
                            "Sports")));
        }

        return panneau;
    }

    public enum Sports {
        Tennis, Squash, Natation, Athletisme, Randonnee, Foot, Basket, Volley, Petanque;

        public static final int NOMBRE = values().length;
    }
}
