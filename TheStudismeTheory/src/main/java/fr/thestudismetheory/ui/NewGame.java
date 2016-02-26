/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui;

import fr.thestudismetheory.Resources;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author vincent
 */
public class NewGame extends JPanel{

    public NewGame() {
        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);
        add(new JLabel("test"));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
        
        Image img = Resources.UNIV_IMG.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        g.drawImage(img, 0, 0, null);
    }
    
    
}
