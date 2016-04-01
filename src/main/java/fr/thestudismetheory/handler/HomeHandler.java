/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.handler;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.ui.interfaces.SelectGameInterface;

/**
 *
 * @author vincent
 */
public class HomeHandler {
    final private TheStudismeTheory app;

    public HomeHandler(TheStudismeTheory app) {
        this.app = app;
    }
    
    public void start(){
        app.getMainWindow().getInterfacesHandler().init();
        home();
        app.getMainWindow().setVisible(true);
    }
    
    public void home(){
        app.getMainWindow().getInterfacesHandler().switchInterface(SelectGameInterface.class);
    }
}
