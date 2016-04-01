package fr.thestudismetheory.data.dao;

import fr.thestudismetheory.data.Division;

import java.util.List;

/**
 * Created by Nazim on 24/02/2016.
 */
public interface DivisionDAO extends DAO<Division> {
    /**
     * Récupère la liste des sections par école
     *
     * @param schoolId
     * @return
     */
    public List<Division> getDivisionsBySchool(int schoolId);
}
