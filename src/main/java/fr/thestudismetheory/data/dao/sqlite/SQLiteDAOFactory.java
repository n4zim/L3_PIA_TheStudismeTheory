/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.dao.DAOFactory;
import fr.thestudismetheory.data.dao.sqlite.sub.SQLiteGraduation;
import fr.thestudismetheory.data.dao.sqlite.sub.SQLiteInterest;

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
    
    final private SQLiteGraduation graduation;
    final private SQLiteInterest interest;

    public SQLiteDAOFactory(String dbname) throws SQLException {
        connection = DriverManager.getConnection("jdbc://sqlite:" + dbname);

        categoryDAO = new SQLiteCategoryDAO(connection);
        cityDAO = new SQLiteCityDAO(connection);
        institutionDAO = new SQLiteInstitutionDAO(connection);
        schoolDAO = new SQLiteSchoolDAO(cityDAO, institutionDAO, connection);
        divisionDAO = new SQLiteDivisionDAO(schoolDAO, categoryDAO, connection);
        
        interest = new SQLiteInterest(connection, categoryDAO);
        graduation = new SQLiteGraduation(connection, divisionDAO);
        studentDAO = new SQLiteStudentDAO(graduation, interest, cityDAO, connection);
        
        teacherDAO = new SQLiteTeacherDAO(categoryDAO, connection);
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
