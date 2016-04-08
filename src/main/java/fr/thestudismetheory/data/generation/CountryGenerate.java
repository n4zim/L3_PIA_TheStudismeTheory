package fr.thestudismetheory.data.generation;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class CountryGenerate {
	private BufferedImage countryImage;
	private Vector<Point> cities;
	private int nbCities;
	
	private boolean generated = false;
	
	public CountryGenerate(int width, int height, int nbCities) {
		countryImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		cities = new Vector<Point>();
		this.nbCities = nbCities;
	}
	
	public CountryGenerate(BufferedImage countryImage, Vector<Point> cities) {
		this.countryImage = countryImage;
		this.cities = cities;
		nbCities = cities.size();
		generated = true;
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
