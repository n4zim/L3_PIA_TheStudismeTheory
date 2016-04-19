package fr.thestudismetheory.data.generation;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

import fr.thestudismetheory.generator.WorldCityGenerator;

public class CountryGenerate {
	private BufferedImage countryImage;
	private Vector<Point> cities;
	private int nbCities;
	private WorldCityGenerator[] cityGenerators;
	
	private boolean generated = false;
	
	
	public CountryGenerate(int width, int height, int nbCities) {
		countryImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		cities = new Vector<Point>();
		cityGenerators = new WorldCityGenerator[nbCities];
		this.nbCities = nbCities;
	}
	
	public CountryGenerate(BufferedImage countryImage, Vector<Point> cities, WorldCityGenerator[] cityGenerators) {
		this.countryImage = countryImage;
		this.cities = cities;
		this.cityGenerators = cityGenerators;
		nbCities = cities.size();
		generated = true;
	}
	
	public WorldCityGenerator getCityGenerator(int id) {
		return cityGenerators[id];
	}
	
	public void setCityGenerator(int id, WorldCityGenerator cityGen) {
		cityGenerators[id] = cityGen;
	}
	
	public int getNbCities() {
		return nbCities;
	}
	
	public int getWidth() {
		return countryImage.getWidth();
	}
	
	public int getHeight() {
		return countryImage.getHeight();
	}
	
	public BufferedImage getCountryImage() {
		return countryImage;
	}
	
	public Point getCityPoint(int indice) {
		return cities.get(indice);
	}
	
	public void addCityPoint(Point city) {
		cities.add(city);
	}
	
	public boolean isGenerated() {
		return generated;
	}
	
	public void setAsGenerated() {
		generated = true;
	}
	
	public Vector<Point> getCities() {
		return cities;
	}
}
