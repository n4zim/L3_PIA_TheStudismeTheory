/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.Category;
import fr.thestudismetheory.data.dao.CategoryDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author vincent
 */
public class SQLiteCategoryDAO extends SQLiteDAO<Category> implements CategoryDAO{

    public SQLiteCategoryDAO(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    protected void makeTable() throws SQLException {
        
    }

    @Override
    protected String[] getColumns() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String[] getPkColumns() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object[] getPkValues(Category entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableName() {
        return "CATEGORY";
    }

    @Override
    protected Category createByRS(ResultSet RS) throws SQLException {
        return new Category(
            RS.getInt("CATEGORY_ID"), 
            RS.getString("CATEGORY_NAME"), 
            RS.getInt("CATEGORY_ATTRACT")
        );
    }

    @Override
    public Category insert(Category model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Category model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
