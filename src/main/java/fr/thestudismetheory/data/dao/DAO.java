/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.dao;

import fr.thestudismetheory.data.Model;

import java.util.List;

/**
 * Interface de base pour la gestion
 * des données
 *
 * @author q13000412
 */
public interface DAO<M extends Model> {
    /**
     * Récupère tout les modèles
     *
     * @return
     */
    public List<M> getAll();

    /**
     * Récupère le modèle selon sa clé primaire
     *
     * @param pk la clé primaire (peut être une liste d'attributs)
     * @return le modèle, ou null si inéxistant
     */
    public M findByPrimaryKey(Object... pk);

    /**
     * Ajoute le modèle dans la base de données
     *
     * @param model Le modèle à ajouter
     * @return L'instance correspondante à la base de données
     * <code>
     * MyModel model = new MyModel(); //définie les valeurs du modèle
     * MyDAO dao = daoDacotry.getMyDAO(); //récupère le DAO
     * model = dao.insert(model); //Ajoute le modèle dans la BD, et récupère l'instance valide du modèle
     * </code>
     */
    public M insert(M model);

    /**
     * Met à jour un modèle dans la BD
     *
     * @param model Modèle à mettre à jour
     */
    public void update(M model);

    /**
     * Supprime un modèle de la base
     *
     * @param model
     */
    public void delete(M model);
}
