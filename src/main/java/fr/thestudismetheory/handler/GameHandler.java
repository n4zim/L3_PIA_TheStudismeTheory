/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.handler;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.global.dao.GlobalDAOFactory;
import fr.thestudismetheory.ui.interfaces.MainGameInterface;

/**
 *
 * @author vincent
 */
public class GameHandler {
    final private TheStudismeTheory app;

    public GameHandler(TheStudismeTheory app) {
        this.app = app;
    }
    
    public void newGame(){
        app.getMainWindow().getInterfacesHandler().switchInterface(MainGameInterface.class);
    }
}
