/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.Category;
import fr.thestudismetheory.data.dao.CategoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author vincent
 */
public class SQLiteCategoryDAO extends SQLiteDAO<Category> implements CategoryDAO {
    /**
     * INTEGER PRIMARY KEY AUTOINCREMNT
     */
    final static public String ATTR_ID = "CATEGORY_ID";
    
    /**
     * TEXT
     */
    final static public String ATTR_NAME = "CATEGORY_NAME";
    
    /**
     * INTEGER 0 <= attract <= 100
     */
    final static public String ATTR_ATTRACT = "CATEGORY_ATTRACT";
    
    final static private String[] COLUMNS = new String[]{ATTR_ID, ATTR_NAME, ATTR_ATTRACT};
    final static private String[] PK_COLS = new String[]{ATTR_ID};
    

    public SQLiteCategoryDAO(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    protected void makeTable() throws SQLException {
        connection.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS " + getTableName() + "(" +
                    ATTR_ID + " INTEGER PRIMARY KEY AUTOINCREMNT," +
                    ATTR_NAME + " TEXT," +
                    ATTR_ATTRACT + " INTEGER" +
            ")"
       );
    }

    @Override
    protected String[] getColumns() {
        return COLUMNS;
    }

    @Override
    protected String[] getPkColumns() {
        return PK_COLS;
    }

    @Override
    protected String getTableName() {
        return "CATEGORY";
    }

    @Override
    protected Category createByRS(ResultSet RS) throws SQLException {
        return new Category(
            RS.getInt(ATTR_ID),
            RS.getString(ATTR_NAME),
            RS.getInt(ATTR_ATTRACT)
        );
    }

    @Override
    public Category insert(Category model) {
        int id = (int)internalInsert(model);
        return new Category(id, model.getName(), model.getAttract());
    }

    @Override
    protected void bindValues(Category entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setString(offset++, entity.getName());
        stmt.setInt(offset, entity.getAttract());
    }

    @Override
    protected void bindPk(Category entity, PreparedStatement stmt, int offset) throws SQLException {
        if(entity.getId() == -1)
            stmt.setNull(offset, Types.INTEGER);
        else
            stmt.setInt(offset, entity.getId());
    }

}
