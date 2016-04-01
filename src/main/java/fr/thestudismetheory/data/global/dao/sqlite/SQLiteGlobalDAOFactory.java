/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.global.dao.sqlite;

import fr.thestudismetheory.Constants;
import fr.thestudismetheory.data.global.dao.GameDataDAO;
import fr.thestudismetheory.data.global.dao.GlobalDAOFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vincent
 */
public class SQLiteGlobalDAOFactory implements GlobalDAOFactory{
    final private Connection connection;
    
    final private GameDataDAO gameDataDAO;

    public SQLiteGlobalDAOFactory() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + Constants.GLOBAL_DB_FILE);
        
        gameDataDAO = new SQLiteGameDataDAO(connection);
    }

    @Override
    public GameDataDAO getGameDataDAO() {
        return gameDataDAO;
    }
}
