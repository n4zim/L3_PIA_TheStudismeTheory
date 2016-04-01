/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.interfaces;

import fr.thestudismetheory.Resources;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.handler.GameHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author vincent
 */
public class SelectGameInterface extends AbstractGameInterface {
    final private GameHandler gameHandler;
    
    final private JButton startBtn = new JButton(UIConstants.BUTTON_START_GAME);

    public SelectGameInterface(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);
        
        add(startBtn);
        actions();
    }
    
    private void actions(){
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameHandler.newGame();
            }
        });
    }

    @Override
    public String getTitle() {
        return UIConstants.TITLE_GAME;
    }

    @Override
    public String getId() {
        return "SELECT_GAME";
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.

        Image img = Resources.UNIV_IMG.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        g.drawImage(img, 0, 0, null);
    }


}
