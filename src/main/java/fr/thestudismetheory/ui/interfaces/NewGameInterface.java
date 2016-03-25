/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.interfaces;

import fr.thestudismetheory.Resources;
import fr.thestudismetheory.generator.WorldMapGenerator2;
import fr.thestudismetheory.ui.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author vincent
 */
public class NewGameInterface extends JPanel {
    final private JButton startBtn = new JButton(UIConstants.BUTTON_START_GAME);

    public NewGameInterface() {
        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);
        
        add(startBtn);
    }
    
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.

        Image img = Resources.UNIV_IMG.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        g.drawImage(img, 0, 0, null);
    }


}
