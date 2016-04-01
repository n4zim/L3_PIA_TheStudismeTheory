/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.School;
import fr.thestudismetheory.data.dao.CityDAO;
import fr.thestudismetheory.data.dao.InstitutionDAO;
import fr.thestudismetheory.data.dao.SchoolDAO;

import java.sql.*;

/**
 * @author vincent
 */
public class SQLiteSchoolDAO extends SQLiteDAO<School> implements SchoolDAO {
    /**
     * INTEGER PRIMARY KEY AUTOINCREMENT
     */
    final static public String ATTR_ID = "SCHOOL_ID";

    /**
     * @see SQLiteCityDAO#ATTR_ID
     */
    final static public String ATTR_CITY = SQLiteCityDAO.ATTR_ID;

    /**
     * @see SQLiteInstitutionDAO#ATTR_ID
     */
    final static public String ATTR_INSITUTION = SQLiteInstitutionDAO.ATTR_ID;

    /**
     * TEXT
     */
    final static public String ATTR_NAME = "SCHOOL_NAME";

    /**
     * INTEGER
     */
    final static public String ATTR_REPUTE = "SCHOOL_REPUTE";

    /**
     * INTEGER > 0
     */
    final static public String ATTR_COST = "SCHOOL_COST";

    /**
     * INTEGER > 0
     */
    final static public String ATTR_SEATS = "SCHOOL_SEATS";

    final static private String[] COLUMNS = new String[]{
            ATTR_ID, ATTR_CITY, ATTR_INSITUTION, ATTR_NAME, ATTR_REPUTE,
            ATTR_COST, ATTR_SEATS
    };

    final static private String[] PK_COL = new String[]{ATTR_ID};

    final private CityDAO cityDAO;
    final private InstitutionDAO institutionDAO;

    public SQLiteSchoolDAO(CityDAO cityDAO, InstitutionDAO institutionDAO, Connection connection) throws SQLException {
        super(connection);
        this.cityDAO = cityDAO;
        this.institutionDAO = institutionDAO;
    }

    @Override
    protected void makeTable() throws SQLException {
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS " + getTableName() + "(" +
                        ATTR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ATTR_CITY + " INTEGER," +
                        ATTR_INSITUTION + " INTEGER," +
                        ATTR_NAME + " TEXT," +
                        ATTR_REPUTE + " INTEGER," +
                        ATTR_COST + " INTEGER," +
                        ATTR_SEATS + " INTEGER" +
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
    protected void bindValues(School entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setInt(offset++, entity.getCity().getId());
        stmt.setInt(offset++, entity.getInstitution().getId());
        stmt.setString(offset++, entity.getName());
        stmt.setInt(offset++, entity.getRepute());
        stmt.setInt(offset++, entity.getCost());
        stmt.setInt(offset, entity.getSeats());
    }

    @Override
    protected void bindPk(School entity, PreparedStatement stmt, int offset) throws SQLException {
        if (entity.getId() == -1)
            stmt.setNull(offset, Types.INTEGER);
        else
            stmt.setInt(offset, entity.getId());
    }

    @Override
    protected String getTableName() {
        return "SCHOOL";
    }

    @Override
    protected School createByRS(ResultSet RS) throws SQLException {
        return new School(
                RS.getInt(ATTR_ID),
                cityDAO.findByPrimaryKey(RS.getInt(ATTR_CITY)),
                institutionDAO.findByPrimaryKey(RS.getInt(ATTR_INSITUTION)),
                RS.getString(ATTR_NAME),
                RS.getInt(ATTR_REPUTE),
                RS.getInt(ATTR_COST),
                RS.getInt(ATTR_SEATS)
        );
    }

    @Override
    public School insert(School model) {
        int id = (int) internalInsert(model);
        return new School(id, model);
    }

}
