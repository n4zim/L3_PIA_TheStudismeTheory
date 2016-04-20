/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.City;
import fr.thestudismetheory.data.ModelListener;
import fr.thestudismetheory.data.dao.CityDAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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

    final private Map<Integer, City> cacheById = new HashMap<>();
    
    final private ModelListener<City> saveListener = new ModelListener<City>() {
        @Override
        public void onUpdate(City model) {
            update(model);
        }
    };

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
        if (entity.getId() == -1)
            stmt.setNull(offset, Types.INTEGER);
        else
            stmt.setInt(offset, entity.getId());
    }

    @Override
    protected City getCached(Object... pk) {
        return cacheById.get(pk[0]);
    }

    @Override
    protected City createByRS(ResultSet RS) throws SQLException {
        int id = RS.getInt(ATTR_ID);

        if (cacheById.containsKey(id))
            return cacheById.get(id);

        City city = new City(
                id,
                RS.getString(ATTR_NAME),
                RS.getInt(ATTR_STU_COUNT)
        );
        
        city.addListener(saveListener);

        cacheById.put(id, city);
        return city;
    }

    @Override
    public City insert(City model) {
        int id = (int) internalInsert(model);
        City city = new City(id, model.getName(), model.getStudentsCount());
        city.addListener(saveListener);
        cacheById.put(id, city);
        return city;
    }

}
