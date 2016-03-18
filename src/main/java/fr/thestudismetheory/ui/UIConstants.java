/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui;

import java.awt.Dimension;

/**
 * Pool des constantes pour l'UI
 * @author vincent
 */
final public class UIConstants {
    final static public String TITLE_GAME = "The Studisme Theory";
    final static public String TITLE_INSTITUTION = "Gestion de l'institution";
    final static public String TITLE_SCHOOL= "Gestion de l'école";
    final static public String TITLE_STUDENT= "Gestion des étudiants";
    final static public String TITLE_TEACHER = "Gestion des professeurs";
    final static public String TITLE_FINANCE = "Finances";

    //COMMON BUTTONS
    final static public String BUTTON_VALIDATE = "Enregistrer";
    final static public String BUTTON_CANCEL = "Annuler";

    // GAME WINDOW
    final static public String BUTTON_INST = "Institutions";
    final static public String BUTTON_SCHOOL = "Ecoles";
    final static public String BUTTON_TEACHER = "Professeurs";
    final static public String BUTTON_STUDENT = "Etudiants";
    final static public String BUTTON_FINANCE = "Finances";
    final static public String BUTTON_SAVE = "Sauvegarder la partie";
    final static public String BUTTON_LOAD = "Charger une partie";
    final static public String BUTTON_EXIT = "Quitter le jeu";
    final static public String LABEL_RESSOURCE = "Ressources";
    final static public String LABEL_TIME = "Temps";
    final static public String LABEL_STAT= "Statistiques";
    final static public String LABEL_OPT_GAME = "Options du jeu";


    // INSTITUTION WINDOW
    final static public String BUTTON_NEW_SCHOOL = "Créer une école";
    final static public String BUTTON_SELL_SCHOOL = "Vendre une école";
    final static public String BUTTON_GRANT = "Demander une subvention";
        //Onglet créer une école
        final static public String NEW_SCHOOL_COST = "Prix de la création : ????€";
        final static public String SELL_SCHOOL_COST = "Prix de la vente : ????€";
        final static public String GRANT_COST = "Demander une subvention (????€)";

    // SCHOOL WINDOW
    final static public String BUTTON_ADD_POLE = "Ajouter un pôle";
    final static public String BUTTON_UPGRADE_POLE = "Améliorer un pôle";



    
    final static public Dimension DEFAULT_WIN_DIM = new Dimension(800, 600);
    final static public Dimension DEFAULT_OPT_DIM = new Dimension(400, 300);
}
