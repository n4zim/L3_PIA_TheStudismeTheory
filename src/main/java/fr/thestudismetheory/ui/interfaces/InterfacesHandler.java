/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.interfaces;

import fr.thestudismetheory.ui.MainWindow;

/**
 *
 * @author vincent
 */
public class InterfacesHandler {
    final static public String NEW_GAME = "NEW_GAME";
    
    final private MainWindow mainWindow;

    public InterfacesHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    
}
