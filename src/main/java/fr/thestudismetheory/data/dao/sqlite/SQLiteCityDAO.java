/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.City;
import fr.thestudismetheory.data.dao.CityDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author vincent
 */
public class SQLiteCityDAO extends SQLiteDAO<City> implements CityDAO {
    /**
     * INTEGER PRIMARY KEY AUTOINCREMENT
     */
    final static public String ATTR_ID = "CITY_ID";
    
    /**
     * TEXT
     */
    final static public String ATTR_NAME = "CITY_NAME";
    
    /**
     * INTEGER > 0
     */
    final static public String ATTR_STU_COUNT = "CITY_STUDENTS_COUNT";
    
    final static private String[] COLUMNS = new String[]{ATTR_ID, ATTR_NAME, ATTR_STU_COUNT};
    final static private String[] PK_COL = new String[]{ATTR_ID};

    public SQLiteCityDAO(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    protected void makeTable() throws SQLException {
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS " + getTableName() + "(" +
                        ATTR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
                        ATTR_NAME + " TEXT," +
                        ATTR_STU_COUNT + " INTEGER" +
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
        return "CITY";
    }

    @Override
    protected void bindValues(City entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setString(offset, entity.getName());
        stmt.setInt(offset + 1, entity.getStudentsCount());
    }

    @Override
    protected void bindPk(City entity, PreparedStatement stmt, int offset) throws SQLException {
        if(entity.getId() == -1)
            stmt.setNull(offset, Types.INTEGER);
        else
            stmt.setInt(offset, entity.getId());
    }

    @Override
    protected City createByRS(ResultSet RS) throws SQLException {
        return new City(
                RS.getInt(ATTR_ID), 
                RS.getString(ATTR_NAME), 
                RS.getInt(ATTR_STU_COUNT)
        );
    }

    @Override
    public City insert(City model) {
        int id = (int)internalInsert(model);
        return new City(id, model.getName(), model.getStudentsCount());
    }

}
