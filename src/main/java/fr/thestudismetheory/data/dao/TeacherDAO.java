package fr.thestudismetheory.data.dao;

import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.Teacher;
import java.util.List;

/**
 * Created by Nazim on 24/02/2016.
 */
public interface TeacherDAO extends DAO<Teacher> {
    public List<Teacher> getTeachersByDivision(Division division);
}
