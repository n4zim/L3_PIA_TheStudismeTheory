/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.listener;

import fr.thestudismetheory.data.ModelListener;
import fr.thestudismetheory.data.global.GameData;
import fr.thestudismetheory.ui.interfaces.MainGameInterface;

/**
 * @author vincent
 */
public class GameDataListener implements ModelListener<GameData> {
    final private MainGameInterface gameInterface;

    public GameDataListener(MainGameInterface gameInterface) {
        this.gameInterface = gameInterface;
    }

    @Override
    public void onUpdate(GameData model) {
        gameInterface.setTime(model.getGameDate());
        gameInterface.setResources(model.getMoney());
    }
}
