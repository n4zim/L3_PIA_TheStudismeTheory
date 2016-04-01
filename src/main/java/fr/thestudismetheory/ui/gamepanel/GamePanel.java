/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.gamepanel;

import javax.swing.*;

/**
 * Panel pour l'affichage central du jeu (ex: de la map)
 *
 * @author vincent
 */
abstract public class GamePanel extends JPanel {

    public GamePanel() {
        setBorder(BorderFactory.createEtchedBorder());
    }

    abstract public String getId();
}
