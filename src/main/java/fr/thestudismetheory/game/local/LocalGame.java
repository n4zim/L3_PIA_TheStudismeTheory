/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.game.local;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.dao.DAOFactory;
import fr.thestudismetheory.data.global.GameData;
import fr.thestudismetheory.game.Game;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Versio locale du jeu
 *
 * @author vincent
 */
public class LocalGame implements Game {
    final private GameData data;
    final private DAOFactory dao;
    final private TheStudismeTheory app;
    final private Calendar calendar = Calendar.getInstance();
    final private ScheduledExecutorService ses;
    private int curYear;
    private int curMounth;

    public LocalGame(DAOFactory dao, GameData data, TheStudismeTheory app) {
        this.data = data;
        this.dao = dao;
        this.app = app;

        ses = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void start() {
        calendar.setTime(data.getGameDate());

        curYear = calendar.get(Calendar.YEAR);
        curMounth = calendar.get(Calendar.MONTH);

        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Calendar cal = Calendar.getInstance();
                cal.setTime(data.getGameDate());
                cal.add(Calendar.DATE, data.getSpeed());
                data.setGameDate(cal.getTime());

                updateScore();

                onTick(data.getGameDate());
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    public void onTick(Date currentDate) {
        System.out.println(">> Tick " + currentDate);

        calendar.setTime(currentDate);
        int nYear = calendar.get(Calendar.YEAR);
        int nMounth = calendar.get(Calendar.MONTH);

        if (nYear > curYear) {
            curYear = nYear;
            onNewYear();
        }

        if (curMounth != nMounth) {
            curMounth = nMounth;
            onNewMounth();
        }


    }

    private void onNewYear() {
        app.getStudentHandler().passExams(this);
        app.getStudentHandler().onEndYear(this);
    }

    private void onNewMounth() {
        app.getSchoolHandler().paySchools(this);
        app.getTeacherHandler().paySalary(this);
    }

    @Override
    public void pause() {
        data.setSpeed(0);
    }

    @Override
    public void resume() {
        data.setSpeed(1);
    }

    @Override
    public void quit() {
        ses.shutdown();
    }

    public void updateScore() {

    }

    @Override
    public GameData getGameData() {
        return data;
    }

    @Override
    public DAOFactory getDAO() {
        return dao;
    }


}
