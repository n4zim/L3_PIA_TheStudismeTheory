/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.global;

import fr.thestudismetheory.data.AbstractModel;
import fr.thestudismetheory.data.ModelListener;
import java.util.Date;

/**
 * Données du jeu
 * @author vincent
 */
public class GameData extends AbstractModel<GameData, ModelListener<GameData>>{
    final private String id;
    private int speed;
    private Date gameDate;
    private long money;

    public GameData(String id, int speed, Date gameDate, long money) {
        this.id = id;
        this.speed = speed;
        this.gameDate = gameDate;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        notifyUpdate();
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
        notifyUpdate();
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
        notifyUpdate();
    }
}
