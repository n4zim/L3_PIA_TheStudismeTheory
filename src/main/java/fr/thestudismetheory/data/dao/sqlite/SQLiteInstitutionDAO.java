/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.Institution;
import fr.thestudismetheory.data.ModelListener;
import fr.thestudismetheory.data.dao.InstitutionDAO;
import fr.thestudismetheory.data.enums.InstitutionType;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vincent
 */
public class SQLiteInstitutionDAO extends SQLiteDAO<Institution> implements InstitutionDAO {
    /**
     * INTEGER PRIMARY KEY AUTOINCREMENT
     */
    final static public String ATTR_ID = "INSTITUTION_ID";

    /**
     * TEXT
     */
    final static public String ATTR_NAME = "INSTITUTION_NAME";

    /**
     * TEXT
     *
     * @see InstitutionType
     */
    final static public String ATTR_TYPE = "INSTITUTION_TYPE";

    final static private String[] COLUMNS = new String[]{ATTR_ID, ATTR_NAME, ATTR_TYPE};
    final static private String[] PK_COL = new String[]{ATTR_ID};
    
    final private Map<Integer, Institution> cacheById = new HashMap<>();
    
    final private ModelListener<Institution> saveListener = new ModelListener<Institution>() {
        @Override
        public void onUpdate(Institution model) {
            update(model);
        }
    };

    public SQLiteInstitutionDAO(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    protected void makeTable() throws SQLException {
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS " + getTableName() + "(" +
                        ATTR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ATTR_NAME + " TEXT," +
                        ATTR_TYPE + " TEXT" +
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
    protected void bindValues(Institution entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setString(offset, entity.getName());
        stmt.setString(offset + 1, entity.getType().name());
    }

    @Override
    protected void bindPk(Institution entity, PreparedStatement stmt, int offset) throws SQLException {
        if (entity.getId() == -1)
            stmt.setNull(offset, Types.INTEGER);
        else
            stmt.setInt(offset, entity.getId());
    }

    @Override
    protected String getTableName() {
        return "INSTITUTION";
    }

    @Override
    protected Institution createByRS(ResultSet RS) throws SQLException {
        int id = RS.getInt(ATTR_ID);
        
        if(cacheById.containsKey(id))
            return cacheById.get(id);
        
        Institution i = new Institution(
                id,
                RS.getString(ATTR_NAME),
                InstitutionType.valueOf(RS.getString(ATTR_TYPE))
        );
        
        i.addListener(saveListener);
        cacheById.put(id, i);
        
        return i;
    }

    @Override
    public Institution insert(Institution model) {
        int id = (int) internalInsert(model);
        Institution i = new Institution(id, model.getName(), model.getType());
        i.addListener(saveListener);
        cacheById.put(id, i);
        return i;
    }

    @Override
    protected Institution getCached(Object... pk) {
        return cacheById.get(pk[0]);
    }   
}