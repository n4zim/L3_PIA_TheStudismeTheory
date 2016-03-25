/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.interfaces;

import fr.thestudismetheory.ui.MainWindow;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vincent
 */
public class InterfacesHandler {
    final private MainWindow mainWindow;
    
    final private Map<Class, AbstractGameInterface> interfaces = new HashMap<>();
    final static public Class<? extends AbstractGameInterface> START_INTERFACE = NewGameInterface.class;

    public InterfacesHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    public void init(){
        constructAll();
        
        for(AbstractGameInterface agi : interfaces.values()){
            mainWindow.addInterface(agi, agi.getId());
        }
        
        switchInterface(START_INTERFACE);
    }
    
    private void constructAll(){
        registerInterface(new NewGameInterface(this));
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
        mainWindow.switchInterface(i.getId());
    }
}
