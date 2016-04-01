/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.global.dao;

/**
 * Factory DAO pour les données globales (communes à toutes les parties)
 *
 * @author vincent
 */
public interface GlobalDAOFactory {
    public GameDataDAO getGameDataDAO();
}
