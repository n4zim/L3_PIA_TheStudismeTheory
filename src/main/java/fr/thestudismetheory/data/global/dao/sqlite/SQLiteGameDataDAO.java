/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.global.dao.sqlite;

import fr.thestudismetheory.data.dao.sqlite.SQLiteDAO;
import fr.thestudismetheory.data.global.GameData;
import fr.thestudismetheory.data.global.dao.GameDataDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vincent
 */
public class SQLiteGameDataDAO extends SQLiteDAO<GameData> implements GameDataDAO{
    /**
     * TEXT PRIMARY KEY
     */
    final static public String ATTR_ID = "GAME_ID";
    /**
     * INTEGER
     */
    final static public String ATTR_SPEED = "GAME_SPEED";
    /**
     * INTEGER
     * timestamp in milliseconds (long)
     */
    final static public String ATTR_DATE = "GAME_DATE";
    
    final static private String[] COLUMNS = new String[]{
        ATTR_ID, ATTR_SPEED, ATTR_DATE
    };
    
    final static private String[] PK_COL = new String[]{ATTR_ID};

    public SQLiteGameDataDAO(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    protected void makeTable() throws SQLException {
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS " + getTableName() + "(" +
                        ATTR_ID + " TEXT PRIMARY KEY," +
                        ATTR_SPEED + " INTEGER," +
                        ATTR_DATE + " INTEGER" +
                ")"
        );
    }

    @Override
    protected String[] getColumns() {
        return COLUMNS;
    }

    @Override
    protected String[] getPkColumns() {
        return PK_COL;
    }

    @Override
    protected String getTableName() {
        return "GAME_DATA";
    }

    @Override
    protected GameData createByRS(ResultSet RS) throws SQLException {
        return new GameData(
                RS.getString(ATTR_ID),
                RS.getInt(ATTR_SPEED),
                new Date(RS.getLong(ATTR_DATE))
        );
    }

    @Override
    protected void bindValues(GameData entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setInt(offset, entity.getSpeed());
        stmt.setLong(offset + 1, entity.getGameDate().getTime());
    }

    @Override
    protected void bindPk(GameData entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setString(offset, entity.getId());
    }

    @Override
    public GameData insert(GameData model) {
        internalInsert(model);
        return model;
    }
    
}