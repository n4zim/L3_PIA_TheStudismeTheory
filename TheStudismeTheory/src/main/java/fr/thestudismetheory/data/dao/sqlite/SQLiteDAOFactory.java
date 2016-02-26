/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite;

import fr.thestudismetheory.data.dao.CategoryDAO;
import fr.thestudismetheory.data.dao.CityDAO;
import fr.thestudismetheory.data.dao.DAOFactory;
import fr.thestudismetheory.data.dao.DivisionDAO;
import fr.thestudismetheory.data.dao.InstitutionDAO;
import fr.thestudismetheory.data.dao.SchoolDAO;
import fr.thestudismetheory.data.dao.StudentDAO;
import fr.thestudismetheory.data.dao.TeacherDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vincent
 */
public class SQLiteDAOFactory implements DAOFactory{
    final private Connection connection;

    public SQLiteDAOFactory(String dbname) throws SQLException {
        connection = DriverManager.getConnection("jdbc://sqlite:" + dbname);
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CityDAO getCityDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DivisionDAO getDivisionDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InstitutionDAO getInstitutionDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchoolDAO getSchoolDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentDAO getStudentDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TeacherDAO getTeacherDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
