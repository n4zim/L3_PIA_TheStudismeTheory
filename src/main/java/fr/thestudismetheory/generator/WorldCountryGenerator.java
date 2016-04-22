package fr.thestudismetheory.generator;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.thestudismetheory.Constants;
import fr.thestudismetheory.data.generation.CityGenerate;
import fr.thestudismetheory.data.generation.CountryGenerate;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.ui.gamepanel.CentralGamePanel;
import java.awt.Image;

public class WorldCountryGenerator extends JPanel {
	private Graphics2D g2d;
	final private CentralGamePanel centralPanel;
	private CountryGenerate countryGenerate;
	private JButton[] cityButtons;

	public WorldCountryGenerator(CentralGamePanel centralPanel, CountryGenerate countryGenerate) {
		setLayout(null);
		this.countryGenerate = countryGenerate;
		this.centralPanel = centralPanel;
		if(!countryGenerate.isGenerated()) generateCountryMap();
		
		createButtons();
		
		
	}

	private void generateCountryMap() {
		int width = countryGenerate.getWidth();
		int height = countryGenerate.getHeight();
		
		int fullSize = (int)(width/2);
		double angle = 0;
		
		Point point = new Point();
		int currSize;
		Vector<Point> points = new Vector<Point>();
		int nbPoints = 1;
		Point center;
		
		g2d = countryGenerate.getCountryImage().createGraphics();
		g2d.setColor(new Color(0,247,255));
		g2d.fillRect(0, 0, width, height);
		
		point.x = (int)(width/2);
		point.y = (int)(height/2);
		points.add(point);
		
		currSize = fullSize;
		
		g2d.setColor(new Color(255,255,255));
		g2d.fillOval((int)(point.x-(currSize/2)),(int)(point.y-(currSize/2)),currSize,currSize);
		
		for(int percentSize = 99; percentSize > 0; percentSize -= 1) {
			
			angle = Math.random()*360;
			angle = angle * (Math.PI/180);
			
			center = points.get((int)(Math.random()*nbPoints));
			
			point.x = (int)(center.x+((currSize/2)*Math.cos(angle)));
			point.y = (int)(center.x+((currSize/2)*Math.sin(angle)));
			
			points.add(point);
			nbPoints++;
			
			currSize = (int)(fullSize*percentSize/100);
			
			g2d.fillOval((int)(point.x-currSize/2),(int)(point.y-currSize/2)-200,currSize+(int)(Math.random()*currSize/5),currSize+(int)(Math.random()*currSize/5));	
			
		}
		
		generateCities();
		countryGenerate.setAsGenerated();
	}

	public WorldCityGenerator getCity(int id) {
		return countryGenerate.getCityGenerator(id);
	}


	private void generateCities() {
		
		for(int i = 0; i< countryGenerate.getNbCities();i++) {
			countryGenerate.setCityGenerator(i, new WorldCityGenerator(new CityGenerate(Constants.DEFAULT_CITY_GRID_SIZE)));
		}
		
		Color white = new Color(255,255,255);
		
		Point city = new Point();
		
		boolean ok;
		
		for(int i = 0; i< countryGenerate.getNbCities(); i++) {
			ok = false;
			do {
				city.x = (int)(Math.random()*(countryGenerate.getWidth()-60)+30);
				city.y = (int)(Math.random()*(countryGenerate.getHeight()-60)+30);
				
				if(countryGenerate.getCountryImage().getRGB(city.x, city.y)==white.getRGB()) ok = true;
				
				for(int j=0 ; j<countryGenerate.getCities().size();j++) {
					if(Math.sqrt(Math.pow((countryGenerate.getCityPoint(j).x-city.x),2)+Math.pow((countryGenerate.getCityPoint(j).y-city.y),2))<(countryGenerate.getHeight()/10)) {
						ok = false;
						break;
					}
				}

			} while(ok==false);
			
			countryGenerate.addCityPoint(new Point(city.x,city.y));
			/*g2d.setColor(Color.RED);
			g2d.fillRect(city.x-10,city.y-10,20,20);*/
		}
		
	}
	
	private void createButtons() {
		cityButtons = new JButton[countryGenerate.getNbCities()];
		
		for(int i=0;i<countryGenerate.getNbCities();i++){
			cityButtons[i] = new JButton();
			cityButtons[i].setBounds(countryGenerate.getCityPoint(i).x-10, countryGenerate.getCityPoint(i).y-10, 20, 20);
			add(cityButtons[i]);
			final int id = i;
			cityButtons[i].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                centralPanel.switchCityPanel(id);
	            }
	        });
		}	
	}
	
    @Override
	public void paintComponent(Graphics g) {
        Image img = countryGenerate.getCountryImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        //Image img = countryGenerate.getCountryImage();
        g.drawImage(img,0,0,null);
     }
	
}
