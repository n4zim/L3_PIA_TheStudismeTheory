/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.game.local;

import fr.thestudismetheory.data.*;
import fr.thestudismetheory.data.dao.DAOFactory;
import fr.thestudismetheory.game.Game;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Versio locale du jeu
 * @author vincent
 */
public class LocalGame implements Game{
	
	private List<City> cities;
	private List<Institution> institutions;
	private List<Division> divisions;
	private List<Category> categories;
	private List<School> schools;
	private List<Student> students;
	private List<Teacher> teachers;
	private GameData data;
	
	public LocalGame(DAOFactory dao, GameData data) {
		cities = dao.getCityDAO().getAll();
		institutions = dao.getInstitutionDAO().getAll();
		divisions = dao.getDivisionDAO().getAll();
		categories = dao.getCategoryDAO().getAll();
		schools = dao.getSchoolDAO().getAll();
		students = dao.getStudentDAO().getAll();
		teachers = dao.getTeacherDAO().getAll();
		this.data = data;
	}
	
    @Override
    public void start() {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
            	
            	Calendar cal = Calendar.getInstance();
            	cal.setTime(data.getGameDate());
            	cal.add(Calendar.DATE, data.getSpeed());
            	data.setGameDate(cal.getTime());
            	
                onTick(data.getGameDate());
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    public void onTick(Date currentDate) {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
