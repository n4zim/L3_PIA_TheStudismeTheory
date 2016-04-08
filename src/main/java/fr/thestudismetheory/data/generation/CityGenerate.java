package fr.thestudismetheory.data.generation;

import fr.thestudismetheory.generator.WorldCitySquare;

public class CityGenerate {
    private int kindOfSections;
    private WorldCitySquare[][] cityMap;
    private boolean generated = false;

    public CityGenerate(int gridSize, int kindOfSections) {
        this.cityMap = new WorldCitySquare[gridSize][gridSize];
        this.kindOfSections = kindOfSections;
    }

    public CityGenerate(WorldCitySquare[][] cityMap, int kindOfSections) {
        this.cityMap = cityMap;
        this.kindOfSections = kindOfSections;
        this.generated = true;
    }

    public int getKindOfSections() {
        return kindOfSections;
    }

    public int getSize() {
        return cityMap.length;
    }

    public WorldCitySquare getSquareFromCityMap(int h, int w) {
        return cityMap[h][w];
    }

    public void setSquareFromCityMap(int h, int w, WorldCitySquare square) {
        this.cityMap[h][w] = square;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setAsGenerated() {
        generated = true;
    }
}
