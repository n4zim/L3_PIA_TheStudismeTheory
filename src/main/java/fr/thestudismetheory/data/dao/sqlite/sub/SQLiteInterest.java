/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao.sqlite.sub;

import fr.thestudismetheory.data.Category;
import fr.thestudismetheory.data.Student;
import fr.thestudismetheory.data.dao.CategoryDAO;
import fr.thestudismetheory.data.dao.sqlite.SQLiteCategoryDAO;
import fr.thestudismetheory.data.dao.sqlite.SQLiteStudentDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vincent
 */
public class SQLiteInterest {
    /**
     * @see SQLiteStudentDAO#ATTR_ID
     */
    final static public String ATTR_STUDENT = SQLiteStudentDAO.ATTR_ID;
    
    /**
     * @see SQLiteCategoryDAO#ATTR_ID
     */
    final static public String ATTR_CATEGORY = SQLiteCategoryDAO.ATTR_ID;
    
    /**
     * INTEGER 0 <= rate <= 100
     */
    final static public String ATTR_RATE = "INTEREST_RATE";
    
    final static public String TABLE_NAME = "INTEREST";
    
    final private Connection connection;
    final private CategoryDAO categoryDAO;

    public SQLiteInterest(Connection connection, CategoryDAO categoryDAO) {
        this.connection = connection;
        this.categoryDAO = categoryDAO;
    }
    
    /**
     * Récpère les intérêts d'un étudiant
     * @param student
     * @return 
     */
    public Map<Category, Integer> getInsterestsByStudent(Student student){
        Map<Category, Integer> interests = new HashMap<>();
        
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + ATTR_STUDENT + " = ?")){
            stmt.setLong(1, student.getId());
            ResultSet RS = stmt.executeQuery();
            
            while(RS.next()){
                Category category = categoryDAO.findByPrimaryKey(RS.getInt(ATTR_CATEGORY));
                int rate = RS.getInt(ATTR_RATE);
                interests.put(category, rate);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return interests;
    }
}
