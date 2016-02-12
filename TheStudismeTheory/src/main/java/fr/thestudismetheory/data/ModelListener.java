/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

/**
 * Listener pour les modèles
 * @author q13000412
 */
public interface ModelListener<M extends Model>{
    /**
     * Evènement envoyé en cas de changement dans le modèle
     * @param model Modèle changé
     */
    public void onUpdate(M model);
}
