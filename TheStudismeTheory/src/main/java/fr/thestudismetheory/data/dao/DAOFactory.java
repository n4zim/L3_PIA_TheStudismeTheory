/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao;

/**
 * S'occupe de l'instantion des DAOs
 * @author vincent
 */
public interface DAOFactory {
    public CategoryDAO getCategoryDAO();
    public CityDAO getCityDAO();
    public DivisionDAO getDivisionDAO();
    public InstitutionDAO getInstitutionDAO();
    public SchoolDAO getSchoolDAO();
    public StudentDAO getStudentDAO();
    public TeacherDAO getTeacherDAO();
}
