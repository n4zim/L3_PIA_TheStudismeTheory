/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory;

import fr.thestudismetheory.data.dao.DAOFactory;
import fr.thestudismetheory.data.dao.sqlite.SQLiteDAOFactory;
import fr.thestudismetheory.data.global.dao.GlobalDAOFactory;
import fr.thestudismetheory.data.global.dao.sqlite.SQLiteGlobalDAOFactory;

/**
 * Factory générale pour les différents modules
 * @author vincent
 */
public class ModulesFactory {
    public DAOFactory createGameDAOFactory(String gameId){
        try{
            return new SQLiteDAOFactory(gameId + ".gdata");
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public GlobalDAOFactory createGlobalDAOFactory(){
        try{
            return new SQLiteGlobalDAOFactory();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
