/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.game;

import java.util.Date;

/**
 * Représentation d'une partie
 *
 * @author q13000412
 * @warming Ne gère pas (directement) l'interface graphique !
 */
public interface Game {
    /**
     * Démarre le jeu (initialisation de la boucle, etc...)
     */
    public void start();

    /**
     * Méthode appelé à chaque "avancement" du temps
     *
     * @param currentDate la date courante
     */
    public void onTick(Date currentDate);

    /**
     * Mise en pause du jeu (Optionel, si par exemple mode online)
     */
    public void pause();

    /**
     * Reprise du jeu (Optionel, uniquement que si pause supporté)
     */
    public void resume();

    /**
     * Quitter le jeu (sauvegarde, fin boucle temporel, etc...)
     */
    public void quit();
}
