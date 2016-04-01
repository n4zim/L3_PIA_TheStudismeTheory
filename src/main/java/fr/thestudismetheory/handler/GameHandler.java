/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.handler;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.global.GameData;
import fr.thestudismetheory.ui.interfaces.MainGameInterface;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author vincent
 */
public class GameHandler {
    final private TheStudismeTheory app;

    public GameHandler(TheStudismeTheory app) {
        this.app = app;
    }
    
    public void newGame(String name){
        name = name.trim();
        
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(app.getMainWindow(), "Veuillez spécifier un nom pour la partie", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(app.getGlobalDAOFactory().getGameDataDAO().gameExists(name)){
            JOptionPane.showMessageDialog(app.getMainWindow(), "Nom déjà pris", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        GameData gameData = app.getGlobalDAOFactory().getGameDataDAO().insert(new GameData(name, 1, new Date(0)));
        internalStartGame(gameData);
    }
    
    private void internalStartGame(GameData gameData){
        app.getMainWindow().getInterfacesHandler().switchInterface(MainGameInterface.class);
    }
    
    public void selectGame(String name){
        GameData gameData = app.getGlobalDAOFactory().getGameDataDAO().findByPrimaryKey(name);
        
        if(gameData == null){
            JOptionPane.showMessageDialog(app.getMainWindow(), "Partie " + name + " introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        internalStartGame(gameData);
    }
}
