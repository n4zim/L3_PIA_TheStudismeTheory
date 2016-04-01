/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite.sub;

import fr.thestudismetheory.data.Student;
import fr.thestudismetheory.data.dao.DivisionDAO;
import fr.thestudismetheory.data.dao.sqlite.SQLiteDivisionDAO;
import fr.thestudismetheory.data.dao.sqlite.SQLiteSchoolDAO;
import fr.thestudismetheory.data.dao.sqlite.SQLiteStudentDAO;
import fr.thestudismetheory.data.subentity.Graduate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vincent
 */
public class SQLiteGraduation {
    /**
     * @see SQLiteSchoolDAO#ATTR_ID
     */
    final static public String ATTR_STUDENT = SQLiteStudentDAO.ATTR_ID;

    /**
     * INTEGER > 0, 4 digits
     */
    final static public String ATTR_YEAR = "GRADUATION_YEAR";

    /**
     * @see SQLiteDivisionDAO#ATTR_SCHOOL
     */
    final static public String ATTR_SCHOOL = SQLiteDivisionDAO.ATTR_SCHOOL;

    /**
     * @see SQLiteDivisionDAO#ATTR_CATEGORY
     */
    final static public String ATTR_CATEGORY = SQLiteDivisionDAO.ATTR_CATEGORY;

    /**
     * INTEGER 0 < level <= 8
     */
    final static public String ATTR_LEVEL = "GRADUATION_LEVEL";

    /**
     * INTEGER 0 <= grade <= 20
     */
    final static public String ATTR_GRADE = "GRADUATION_GRADE";

    final static public String TABLE_NAME = "GRADUATION";

    final private Connection connection;
    final private DivisionDAO divisionDAO;

    public SQLiteGraduation(Connection connection, DivisionDAO divisionDAO) {
        this.connection = connection;
        this.divisionDAO = divisionDAO;
    }

    /**
     * Récupère les notes + études d'un étudiant
     *
     * @param student
     * @return Map des études, avec pour clé l'année
     */
    public Map<Integer, Graduate> getStudentGraduation(Student student) {
        Map<Integer, Graduate> graduations = new HashMap<>();

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + ATTR_STUDENT + " = ?")) {
            stmt.setLong(1, student.getId());

            ResultSet RS = stmt.executeQuery();

            while (RS.next()) {
                Graduate graduate = new Graduate(
                        student,
                        RS.getInt(ATTR_YEAR),
                        divisionDAO.findByPrimaryKey(RS.getInt(ATTR_SCHOOL), RS.getInt(ATTR_CATEGORY)),
                        RS.getInt(ATTR_LEVEL),
                        RS.getInt(ATTR_GRADE)
                );

                graduations.put(graduate.getYear(), graduate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return graduations;
    }
}
