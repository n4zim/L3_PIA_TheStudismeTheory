/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

/**
 * Interface de base pour les modèles
 *
 * @author q13000412
 */
public interface Model<M extends Model<M, L>, L extends ModelListener<M>> {

    public static final int ID_NOT_DEFINED = -1;

    /**
     * Ajoute un listener dans la liste des listeners
     *
     * @param listener
     */
    public void addListener(L listener);

    /**
     * Supprime le listener
     *
     * @param listener
     */
    public void removeListener(L listener);
}
