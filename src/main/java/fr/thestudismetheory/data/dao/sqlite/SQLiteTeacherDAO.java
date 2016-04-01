/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.Teacher;
import fr.thestudismetheory.data.dao.CategoryDAO;
import fr.thestudismetheory.data.dao.DivisionDAO;
import fr.thestudismetheory.data.dao.TeacherDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

/**
 * @author vincent
 */
public class SQLiteTeacherDAO extends SQLiteDAO<Teacher> implements TeacherDAO {
    /**
     * INTEGER PRIMARY KEY AUTOINCREMENT
     */
    final static public String ATTR_ID = "TEACHER_ID";
    
    /**
     * TEXT
     */
    final static public String ATTR_NAME = "TEACHER_NAME";
    
    /**
     * INTEGER
     * timestamp en long !
     */
    final static public String ATTR_BIRTH = "TEACHER_BIRTH";
    
    /**
     * INTEGER
     * timestamp en long
     */
    final static public String ATTR_ENTERING = "TEACHER_ENTERING";
    
    /**
     * INTEGER [0-100]
     */
    final static public String ATTR_CHARISMA = "TEACHER_CHARISMA";
    
    /**
     * INTEGER [0-100]
     */
    final static public String ATTR_SKILL = "TEACHER_SKILL";
    
    /**
     * INTEGER [0-100]
     */
    final static public String ATTR_PUNCT = "TEACHER_PUNCT";
    
    /**
     * INTEGER [0-100]
     */
    final static public String ATTR_TEACH_SKILL = "TEACHER_TEACH_SKILL";
    
    /**
     * @see SQLiteCategoryDAO#ATTR_ID
     */
    final static public String ATTR_CATEGORY = SQLiteCategoryDAO.ATTR_ID;
    
    /**
     * @see SQLiteSchoolDAO#ATTR_ID
     */
    final static public String ATTR_SCHOOL = SQLiteSchoolDAO.ATTR_ID;
    
    final static private String[] COLUMNS = new String[]{
        ATTR_ID, ATTR_NAME, ATTR_BIRTH, ATTR_ENTERING,
        ATTR_CHARISMA, ATTR_SKILL, ATTR_PUNCT, 
        ATTR_TEACH_SKILL, ATTR_CATEGORY, ATTR_SCHOOL
    };
    
    final static private String[] PK_COL = new String[]{ATTR_ID};
    
    final private CategoryDAO categoryDAO;
    final private DivisionDAO divisionDAO;

    public SQLiteTeacherDAO(CategoryDAO categoryDAO, Connection connection, DivisionDAO divisionDAO) throws SQLException {
        super(connection);
        this.categoryDAO = categoryDAO;
        this.divisionDAO = divisionDAO;
    }

    @Override
    protected void makeTable() throws SQLException {
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS " + getTableName() + "(" +
                        ATTR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ATTR_NAME + " TEXT," + 
                        ATTR_BIRTH + " INTEGER," +
                        ATTR_ENTERING + " INTEGER," +
                        ATTR_CHARISMA + " INTEGER," +
                        ATTR_SKILL + " INTEGER," +
                        ATTR_PUNCT + " INTEGER," +
                        ATTR_TEACH_SKILL + " INTEGER," +
                        ATTR_CATEGORY + " INTEGER" +
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
        return "TEACHER";
    }

    @Override
    protected Teacher createByRS(ResultSet RS) throws SQLException {
        int catId = RS.getInt(ATTR_CATEGORY);
        int schoolId = RS.getInt(ATTR_SCHOOL);
        
        return new Teacher(
                RS.getLong(ATTR_ID), 
                RS.getString(ATTR_NAME), 
                new Date(RS.getLong(ATTR_BIRTH)),
                new Date(RS.getLong(ATTR_ENTERING)),
                RS.getInt(RS.getInt(ATTR_CHARISMA)),
                RS.getInt(ATTR_SKILL),
                RS.getInt(ATTR_PUNCT),
                RS.getInt(ATTR_TEACH_SKILL),
                categoryDAO.findByPrimaryKey(catId),
                divisionDAO.findByPrimaryKey(schoolId, catId)
        );
    }

    @Override
    protected void bindValues(Teacher entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setString(offset++, entity.getName());
        stmt.setLong(offset++, entity.getBirth().getTime());
        stmt.setLong(offset++, entity.getEntering().getTime());
        stmt.setInt(offset++, entity.getCharisma());
        stmt.setInt(offset++, entity.getSkill());
        stmt.setInt(offset++, entity.getPunct());
        stmt.setInt(offset++, entity.getTeachSkill());
        stmt.setInt(offset++, entity.getCategory().getId());
        
        if(entity.getDivision() == null)
            stmt.setNull(offset, Types.INTEGER);
        else
            stmt.setInt(offset, entity.getDivision().getSchool().getId());
    }

    @Override
    protected void bindPk(Teacher entity, PreparedStatement stmt, int offset) throws SQLException {
        if(entity.getId() == -1)
            stmt.setNull(offset, Types.INTEGER);
        else
            stmt.setLong(offset, entity.getId());
    }

    @Override
    public Teacher insert(Teacher model) {
        long id = internalInsert(model);
        return new Teacher(id, model);
    }

}
