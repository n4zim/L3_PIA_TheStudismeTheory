/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.dao.DAOFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author vincent
 */
public class SQLiteDAOFactory implements DAOFactory {
    final private Connection connection;

    final private SQLiteCategoryDAO categoryDAO;
    final private SQLiteCityDAO cityDAO;
    final private SQLiteDivisionDAO divisionDAO;
    final private SQLiteInstitutionDAO institutionDAO;
    final private SQLiteSchoolDAO schoolDAO;
    final private SQLiteStudentDAO studentDAO;
    final private SQLiteTeacherDAO teacherDAO;

    public SQLiteDAOFactory(String dbname) throws SQLException {
        connection = DriverManager.getConnection("jdbc://sqlite:" + dbname);

        categoryDAO = new SQLiteCategoryDAO(connection);
        cityDAO = new SQLiteCityDAO(connection);
        divisionDAO = new SQLiteDivisionDAO(connection);
        institutionDAO = new SQLiteInstitutionDAO(connection);
        schoolDAO = new SQLiteSchoolDAO(cityDAO, institutionDAO, connection);
        studentDAO = new SQLiteStudentDAO(null, null, null, connection);
        teacherDAO = new SQLiteTeacherDAO(connection);
    }

    @Override
    public SQLiteCategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    @Override
    public SQLiteCityDAO getCityDAO() {
        return cityDAO;
    }

    @Override
    public SQLiteDivisionDAO getDivisionDAO() {
        return divisionDAO;
    }

    @Override
    public SQLiteInstitutionDAO getInstitutionDAO() {
        return institutionDAO;
    }

    @Override
    public SQLiteSchoolDAO getSchoolDAO() {
        return schoolDAO;
    }

    @Override
    public SQLiteStudentDAO getStudentDAO() {
        return studentDAO;
    }

    @Override
    public SQLiteTeacherDAO getTeacherDAO() {
        return teacherDAO;
    }

}
