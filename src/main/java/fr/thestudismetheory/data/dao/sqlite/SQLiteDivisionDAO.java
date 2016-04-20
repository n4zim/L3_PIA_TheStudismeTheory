/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.ModelListener;
import fr.thestudismetheory.data.dao.CategoryDAO;
import fr.thestudismetheory.data.dao.DivisionDAO;
import fr.thestudismetheory.data.dao.SchoolDAO;
import fr.thestudismetheory.data.enums.StudentFlaw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vincent
 */
public class SQLiteDivisionDAO extends SQLiteDAO<Division> implements DivisionDAO {
    /**
     * @see SQLiteSchoolDAO#ATTR_ID
     */
    final static public String ATTR_SCHOOL = SQLiteSchoolDAO.ATTR_ID;

    /**
     * @see SQLiteCategoryDAO#ATTR_ID
     */
    final static public String ATTR_CATEGORY = SQLiteCategoryDAO.ATTR_ID;

    /**
     * INTEGER between 0 and 100
     */
    final static public String ATTR_SEATS_RATE = "DIVISION_SEATS_RATE";

    /**
     * INTEGER > 0
     */
    final static public String ATTR_COST = "DIVISION_COST";

    /**
     * INTEGER
     *
     * @see StudentFlaw#parseFlaws(int)
     */
    final static public String ATTR_COND = "DIVISION_COND";

    final static private String[] COLUMNS = new String[]{
            ATTR_SCHOOL, ATTR_CATEGORY, ATTR_SEATS_RATE,
            ATTR_COST, ATTR_COND
    };

    final static private String[] PK_COLS = new String[]{ATTR_SCHOOL, ATTR_CATEGORY};

    final private SchoolDAO schoolDAO;
    final private CategoryDAO categoryDAO;
    
    final private ModelListener<Division> saveListener = new ModelListener<Division>() {
        @Override
        public void onUpdate(Division model) {
            update(model);
        }
    };

    public SQLiteDivisionDAO(SchoolDAO schoolDAO, CategoryDAO categoryDAO, Connection connection) throws SQLException {
        super(connection);
        this.schoolDAO = schoolDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    protected void makeTable() throws SQLException {
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS " + getTableName() + "(" +
                        ATTR_SCHOOL + " INTEGER," +
                        ATTR_CATEGORY + " INTEGER," +
                        ATTR_SEATS_RATE + " INTEGER," +
                        ATTR_COST + " INTEGER," +
                        ATTR_COND + " INTEGER" +
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
    protected void bindValues(Division entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setInt(offset++, entity.getSeatsRate());
        stmt.setInt(offset++, entity.getCost());
        stmt.setInt(offset, StudentFlaw.flawsToBitset(entity.getCond()));
    }

    @Override
    protected void bindPk(Division entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setInt(offset, entity.getSchool().getId());
        stmt.setInt(offset + 1, entity.getCategory().getId());
    }

    @Override
    protected String getTableName() {
        return "DIVISION";
    }

    @Override
    protected Division createByRS(ResultSet RS) throws SQLException {
        Division d = new Division(
                schoolDAO.findByPrimaryKey(RS.getInt(ATTR_SCHOOL)),
                categoryDAO.findByPrimaryKey(RS.getInt(ATTR_CATEGORY)),
                RS.getInt(ATTR_SEATS_RATE),
                RS.getInt(ATTR_COST),
                StudentFlaw.parseFlaws(RS.getInt(ATTR_COND))
        );
        
        d.addListener(saveListener);
        
        return d;
    }

    @Override
    public Division insert(Division model) {
        internalInsert(model);
        model.addListener(saveListener);
        return model;
    }

    @Override
    public List<Division> getDivisionsBySchool(int schoolId) {
        List<Division> divisions = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE " + ATTR_SCHOOL + " = ?")) {
            stmt.setInt(1, schoolId);

            ResultSet RS = stmt.executeQuery();

            while (RS.next())
                divisions.add(createByRS(RS));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisions;
    }
}
