/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.interfaces;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.ui.MainWindow;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vincent
 */
public class InterfacesHandler {
    final private MainWindow mainWindow;
    final private TheStudismeTheory app;
    
    final private Map<Class, AbstractGameInterface> interfaces = new HashMap<>();

    public InterfacesHandler(MainWindow mainWindow, TheStudismeTheory app) {
        this.mainWindow = mainWindow;
        this.app = app;
    }
    
    public void init(){
        constructAll();
        
        for(AbstractGameInterface agi : interfaces.values()){
            mainWindow.addInterface(agi, agi.getId());
        }
    }
    
    private void constructAll(){
        registerInterface(new SelectGameInterface(app.getGameHandler()));
        registerInterface(new MainGameInterface());
    }
    
    public<T extends AbstractGameInterface> T getInterface(Class<T> type){
        return (T)interfaces.get(type);
    }
    
    private void registerInterface(AbstractGameInterface agi){
        interfaces.put(agi.getClass(), agi);
    }
    
    public<T extends AbstractGameInterface> void switchInterface(Class<T> type){
        T i = getInterface(type);
        switchInterface(i);
    }
    
    public<T extends AbstractGameInterface> void switchInterface(T i){
        mainWindow.switchInterface(i.getId());
    }
}
