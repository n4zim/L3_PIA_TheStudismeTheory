/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.Category;
import fr.thestudismetheory.data.Student;
import fr.thestudismetheory.data.dao.CityDAO;
import fr.thestudismetheory.data.dao.StudentDAO;
import fr.thestudismetheory.data.dao.sqlite.sub.SQLiteGraduation;
import fr.thestudismetheory.data.dao.sqlite.sub.SQLiteInterest;
import fr.thestudismetheory.data.enums.StudentFlaw;
import fr.thestudismetheory.data.listener.StudentListener;
import fr.thestudismetheory.data.subentity.Graduate;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vincent
 */
public class SQLiteStudentDAO extends SQLiteDAO<Student> implements StudentDAO {
    /**
     * INTEGER PRIMARY KEY AUTOINCREMENT
     */
    final static public String ATTR_ID = "SUTDENT_ID";

    /**
     * TEXT
     */
    final static public String ATTR_NAME = "STUDENT_NAME";

    /**
     * INTEGER
     * Les dates sont codées en timestamp en millisecondes, donc des long !
     */
    final static public String ATTR_BIRTH = "STUDENT_BIRTH";

    /**
     * INTEGER
     *
     * @see StudentFlaw#flawsToBitset(java.util.Set)
     */
    final static public String ATTR_FLAWS = "STUDENT_FLAWS";

    /**
     * @see SQLiteCityDAO#ATTR_ID
     */
    final static public String ATTR_CITY = SQLiteCityDAO.ATTR_ID;

    /**
     * INTEGER 0 <= studism <= 100
     */
    final static public String ATTR_STUDISM = "STUDENT_STUDISM";

    /**
     * INTEGER 0 <= procrass <= 100
     */
    final static public String ATTR_PROCRASS = "STUDENT_PROCRASS";

    final static private String[] COLUMNS = new String[]{
            ATTR_ID, ATTR_NAME, ATTR_BIRTH, ATTR_FLAWS,
            ATTR_CITY, ATTR_STUDISM, ATTR_PROCRASS
    };

    final static private String[] PK_COL = new String[]{ATTR_ID};

    final private SQLiteGraduation graduation;
    final private SQLiteInterest interest;
    final private CityDAO cityDAO;
    
    final private Map<Long, Student> studentById = new HashMap<>();
    
    final private StudentListener saveListener = new StudentListener() {
        @Override
        public void onInterestChange(Student student, Category category, int rate) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onNewGraduation(Student student, Graduate graduate) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onUpdate(Student model) {
            update(model);
        }
    };

    public SQLiteStudentDAO(SQLiteGraduation graduation, SQLiteInterest interest, CityDAO cityDAO, Connection connection) throws SQLException {
        super(connection);
        this.graduation = graduation;
        this.interest = interest;
        this.cityDAO = cityDAO;
    }

    @Override
    protected void makeTable() throws SQLException {
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS " + getTableName() + "(" +
                        ATTR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ATTR_NAME + " TEXT," +
                        ATTR_BIRTH + " INTEGER," +
                        ATTR_FLAWS + " INTEGER," +
                        ATTR_CITY + " INTEGER," +
                        ATTR_STUDISM + " INTEGER," +
                        ATTR_PROCRASS + " INTEGER" +
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
        return "STUDENT";
    }

    @Override
    protected Student createByRS(ResultSet RS) throws SQLException {
        long id = RS.getLong(ATTR_ID);
        
        if(studentById.containsKey(id))
            return studentById.get(id);
        
        Student student = new Student(
                id,
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
        student.setGraduations(graduation.getStudentGraduation(student));
        
        student.addListener(saveListener);
        
        studentById.put(id, student);

        return student;
    }

    @Override
    protected void bindValues(Student entity, PreparedStatement stmt, int offset) throws SQLException {
        stmt.setString(offset++, entity.getName());
        stmt.setLong(offset++, entity.getBirth().getTime());
        stmt.setInt(offset++, StudentFlaw.flawsToBitset(entity.getFlaws()));
        stmt.setInt(offset++, entity.getCity().getId());
        stmt.setInt(offset++, entity.getStudism());
        stmt.setInt(offset, entity.getProcrass());
    }

    @Override
    protected void bindPk(Student entity, PreparedStatement stmt, int offset) throws SQLException {
        if (entity.getId() == -1)
            stmt.setNull(offset, Types.INTEGER);
        else
            stmt.setLong(offset, entity.getId());
    }

    @Override
    public Student insert(Student model) {
        long id = internalInsert(model);
        Student student = new Student(id, model);
        student.addListener(saveListener);
        studentById.put(id, student);
        return student;
    }

}
