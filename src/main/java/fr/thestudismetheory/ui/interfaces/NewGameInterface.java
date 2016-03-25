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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author vincent
 */
public class NewGameInterface extends AbstractGameInterface {
    final private InterfacesHandler interfacesHandler;
    
    final private JButton startBtn = new JButton(UIConstants.BUTTON_START_GAME);

    public NewGameInterface(InterfacesHandler interfacesHandler) {
        this.interfacesHandler = interfacesHandler;
        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);
        
        add(startBtn);
        
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStart();
            }
        });
    }

    @Override
    public String getTitle() {
        return UIConstants.TITLE_GAME;
    }

    @Override
    public String getId() {
        return "NEW_GAME";
    }
    
    public void onStart(){
        interfacesHandler.switchInterface(MainGameInterface.class);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.

        Image img = Resources.UNIV_IMG.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        g.drawImage(img, 0, 0, null);
    }


}
