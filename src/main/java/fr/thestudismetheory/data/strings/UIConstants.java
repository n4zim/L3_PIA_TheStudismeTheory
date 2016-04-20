/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.strings;

import java.awt.*;

/**
 * Pool des constantes pour l'UI
 *
 * @author vincent
 */
final public class UIConstants {
    final static public String TITLE_GAME = "The Studisme Theory";
    final static public String TITLE_NEW_GAME = "Nouvelle partie";
    final static public String TITLE_SELECT_GAME = "Sélectionner un partie";

    //COMMON BUTTONS
    final static public String BUTTON_VALIDATE = "Enregistrer";
    final static public String BUTTON_CANCEL = "Annuler";

    //SelectGameInterface
    final static public String BUTTON_NEW_GAME = TITLE_NEW_GAME;

    // GAME WINDOW
    final static public String BUTTON_INST = "Institutions";
    final static public String BUTTON_SCHOOL = "Ecoles";
    final static public String BUTTON_TEACHER = "Professeurs";
    final static public String BUTTON_STUDENT = "Etudiants";
    final static public String BUTTON_FINANCE = "Finances";
    final static public String BUTTON_EXIT = "Quitter le jeu";
    final static public String LABEL_RESSOURCE = "Ressources";
    final static public String LABEL_TIME = "Temps";
    final static public String LABEL_STAT = "Statistiques";


    // INSTITUTION WINDOW
    final static public String BUTTON_NEW_SCHOOL = "Créer une école";
    final static public String BUTTON_SELL_SCHOOL = "Vendre une école";
    final static public String BUTTON_GRANT = "Demander une subvention";
    //Onglet créer une école
    final static public int NEW_SCHOOL_PRICE = 2000;
    final static public String NEW_SCHOOL_COST = "Prix de la création : " + NEW_SCHOOL_PRICE + "€";
    //Onglet vendre une école
    final static public int SELL_SCHOOL_PRICE = 2000;
    final static public String SELL_SCHOOL_COST = "Prix de la vente : " + SELL_SCHOOL_PRICE + "€";
    //Onglet demander une subvention
    final static public int GRANT_PRICE = 10000;
    final static public String GRANT_COST = "Demander une subvention (" + GRANT_PRICE + "€)";

    // SCHOOL WINDOW
    final static public String BUTTON_ADD_POLE = "Ajouter un pôle";
    final static public String BUTTON_UPGRADE_POLE = "Améliorer un pôle";
    //Onglet ajouter un pole
    final static public int NEW_POLE_COST = 3000;
    final static public String ADD_POLE_COST = "Prix de l'ajout d'un pôle : " + NEW_POLE_COST + "€";

    //Onglet améliorer un pole
    final static public String UPGRADE_POLE_COST = "Améliorer le pôle (1500€)";
    final static public int UPGRADE_DIVISION_COST = 1500;
    final static public String UPGRADE_POLE_DESC = "Description : Améliorer un pôle permet d'augmenter la capacité d'étudiants de celui-ci.";
    final static public int UPGRADE_DIVISION_SEATS = 200;


    final static public Dimension DEFAULT_WIN_DIM = new Dimension(850, 650);

    //STUDENT WINDOW

    final static public String BUTTON_FIRED = "Renvoyer";
}
