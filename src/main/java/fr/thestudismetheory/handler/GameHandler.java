/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.handler;

import fr.thestudismetheory.Constants;
import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.Category;
import fr.thestudismetheory.data.Division;
import fr.thestudismetheory.data.dao.CategoryDAO;
import fr.thestudismetheory.data.dao.DivisionDAO;
import fr.thestudismetheory.data.global.GameData;
import fr.thestudismetheory.game.Game;
import fr.thestudismetheory.game.local.LocalGame;
import fr.thestudismetheory.ui.interfaces.MainGameInterface;

import javax.swing.*;
import java.util.Date;
import java.util.List;

/**
 * @author vincent
 */
public class GameHandler {
    final static public Date START_DATE = new Date(0);
    final static public int DEFAULT_SPEED = 1;
    final static public long START_MONEY = 10000;

    final private TheStudismeTheory app;

    private Game currentGame;

    public GameHandler(TheStudismeTheory app) {
        this.app = app;
    }

    public void newGame(String name) {
        name = name.trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(app.getMainWindow(), "Veuillez spécifier un nom pour la partie", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (app.getGlobalDAOFactory().getGameDataDAO().gameExists(name)) {
            JOptionPane.showMessageDialog(app.getMainWindow(), "Nom déjà pris", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GameData gameData = app.getGlobalDAOFactory().getGameDataDAO().insert(new GameData(
                name,
                DEFAULT_SPEED,
                START_DATE,
                START_MONEY
        ));

        internalStartGame(gameData);

        //Génération des catégories
        CategoryDAO categoryDAO = app.getGameHandler().getCurrentGame().getDAO().getCategoryDAO();

        categoryDAO.insert(new Category(-1, "Mathématique", Constants.DEFAULT_CATEGORY_ATTRACT));
        categoryDAO.insert(new Category(-1, "Sciences", Constants.DEFAULT_CATEGORY_ATTRACT));
        categoryDAO.insert(new Category(-1, "Langues", Constants.DEFAULT_CATEGORY_ATTRACT));
        categoryDAO.insert(new Category(-1, "Ninja", Constants.DEFAULT_CATEGORY_ATTRACT));
        categoryDAO.insert(new Category(-1, "Magie", Constants.DEFAULT_CATEGORY_ATTRACT));
        categoryDAO.insert(new Category(-1, "Français", Constants.DEFAULT_CATEGORY_ATTRACT));
        categoryDAO.insert(new Category(-1, "Sport", Constants.DEFAULT_CATEGORY_ATTRACT));
        categoryDAO.insert(new Category(-1, "Informatique", Constants.DEFAULT_CATEGORY_ATTRACT));
    }

    private void internalStartGame(GameData gameData) {
        currentGame = new LocalGame(
                app.getModulesFactory().createGameDAOFactory(gameData.getId()),
                gameData,
                app
        );

        MainGameInterface mgi = app.getMainWindow().getInterfacesHandler().getInterface(MainGameInterface.class);

        //Initialiser le GUI ici
        gameData.addListener(mgi.getGameDataListener());
        mgi.getGameDataListener().onUpdate(gameData);

        app.getMainWindow().getInterfacesHandler().switchInterface(mgi);

        currentGame.start();
    }

    public void selectGame(String name) {
        GameData gameData = app.getGlobalDAOFactory().getGameDataDAO().findByPrimaryKey(name);

        if (gameData == null) {
            JOptionPane.showMessageDialog(app.getMainWindow(), "Partie " + name + " introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        internalStartGame(gameData);
    }

    public Game getCurrentGame() {
        return this.currentGame;
    }
}
