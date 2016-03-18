/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui;

import fr.thestudismetheory.generator.WorldMapGenerator2;

import javax.swing.*;
import java.awt.*;

/**
 * @author vincent
 */
public class NewGame extends JPanel {

    public NewGame() {
        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);
        WorldMapGenerator2 generator = new WorldMapGenerator2();
        add(generator.showMap());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.

//        Image img = Resources.UNIV_IMG.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
//        g.drawImage(img, 0, 0, null);
    }


}
