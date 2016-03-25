/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.Student;
import fr.thestudismetheory.data.dao.CityDAO;
import fr.thestudismetheory.data.dao.StudentDAO;
import fr.thestudismetheory.data.dao.sqlite.sub.SQLiteGraduation;
import fr.thestudismetheory.data.dao.sqlite.sub.SQLiteInterest;
import fr.thestudismetheory.data.enums.StudentFlaw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

/**
 * @author vincent
 */
public class SQLiteStudentDAO extends SQLiteDAO<Student> implements StudentDAO {
    final static public String ATTR_ID = "SUTDENT_ID";
    final static public String ATTR_NAME = "STUDENT_NAME";
    
    /**
     * Les dates sont codé en timestamp en millisecondes, donc des long !
     */
    final static public String ATTR_BIRTH = "STUDENT_BIRTH";
    final static public String ATTR_FLAWS = "STUDENT_FLAWS";
    final static public String ATTR_CITY = SQLiteCityDAO.ATTR_ID;
    final static public String ATTR_STUDISM = "STUDENT_STUDISM";
    final static public String ATTR_PROCRASS = "STUDENT_PROCRASS";
    
    final static private String[] COLUMNS = new String[]{
        ATTR_ID, ATTR_NAME, ATTR_BIRTH, ATTR_FLAWS,
        ATTR_CITY, ATTR_STUDISM, ATTR_PROCRASS
    };
    
    final static private String[] PK_COL = new String[]{ATTR_ID};
    
    final private SQLiteGraduation graduation;
    final private SQLiteInterest interest;
    final private CityDAO cityDAO;

    public SQLiteStudentDAO(SQLiteGraduation graduation, SQLiteInterest interest, CityDAO cityDAO, Connection connection) throws SQLException {
        super(connection);
        this.graduation = graduation;
        this.interest = interest;
        this.cityDAO = cityDAO;
    }

    @Override
    protected void makeTable() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return "STUDENT";
    }

    @Override
    protected Student createByRS(ResultSet RS) throws SQLException {
        Student student = new Student(
                RS.getLong(ATTR_ID), 
                RS.getString(ATTR_NAME),
                new Date(RS.getLong(ATTR_BIRTH)),
                cityDAO.findByPrimaryKey(RS.getInt(ATTR_CITY)),
                RS.getInt(ATTR_STUDISM), 
                RS.getInt(ATTR_PROCRASS),
                StudentFlaw.parseFlaws(RS.getInt(ATTR_FLAWS)),
                null,
                null
        );
        
        student.setInterests(interest.getInsterestsByStudent(student));
        student.setGraduations(graduation.getStudentGradution(student));
        
        return student;
    }

    @Override
    protected void bindValues(Student entity, PreparedStatement stmt, int offset) throws SQLException {
        
    }

    @Override
    protected void bindPk(Student entity, PreparedStatement stmt, int offset) throws SQLException {
        if(entity.getId() == -1)
            stmt.setNull(offset, Types.INTEGER);
        else
            stmt.setLong(offset, entity.getId());
    }

    @Override
    public Student insert(Student model) {
        long id = internalInsert(model);
        return new Student(id, model);
    }

}
