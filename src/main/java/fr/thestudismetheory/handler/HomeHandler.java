/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.handler;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.ui.interfaces.NewGameInterface;

/**
 *
 * @author vincent
 */
public class HomeHandler {
    final private TheStudismeTheory app;

    public HomeHandler(TheStudismeTheory app) {
        this.app = app;
    }
    
    public void home(){
        app.getMainWindow().getInterfacesHandler().switchInterface(NewGameInterface.class);
    }
}
