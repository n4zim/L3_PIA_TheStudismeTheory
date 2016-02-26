/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Modèle abstrait
 * Gère les listeners
 * @author q13000412
 * @param <M> Doit correspondre OBLIGATOIREMENT à la classe courante (class A extends AbstractModel<A>{})
 */
abstract public class AbstractModel<M extends AbstractModel<M>> implements Model<M>{
    final protected List<ModelListener<M>> listeners = new ArrayList<>();

    /**
     * Notifie à tout les listeners comme quoi le modèle
     * courant a été modifié (permettant une MaJ de la GUI
     * Ou de la BD)
     */
    public void notifyUpdate(){
        for (ModelListener<M> listener : listeners) {
            listener.onUpdate((M)this);
        }
    }
    
    @Override
    public void addListener(ModelListener<M> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ModelListener<M> listener) {
        listeners.remove(listener);
    }
}
