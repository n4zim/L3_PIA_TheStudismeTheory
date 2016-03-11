package TheStudismeTheory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapGenerator extends JPanel {
    private static final int GRID_SIZE = 8;
    private static final int SQUARE_SIZE = 80;
    private static final int BORDER_SIZE = 2;
    private static final int KIND_OF_TYPES = 3; 
    private static final int MAX_SECTION_SQUARES = 6;
    private static final Color BORDER_COLOR = Color.BLACK;
    
    private static final Color[] TYPES_COLORS = { Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW };
    
    private static CitySection[][] cityMap = new CitySection[GRID_SIZE][GRID_SIZE];

    public MapGenerator() {
        super(new GridLayout(GRID_SIZE, GRID_SIZE));
        this.setPreferredSize(new Dimension(GRID_SIZE * SQUARE_SIZE, GRID_SIZE * SQUARE_SIZE));
        for(int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
        	CitySquare button = new CitySquare(i);
        	button.setBorderPainted(true);
        	
        	int w = i%GRID_SIZE;
        	int h = i/GRID_SIZE;
        	
        	CitySection currentCitySection = cityMap[h][w];
        	button.setBackground(currentCitySection.getColor());
        	        	
        	if(h > 0 &&  cityMap[h][w].getType() != cityMap[h-1][w].getType()) currentCitySection.setTopBorder(true);
        	if(w > 0 &&  cityMap[h][w].getType() != cityMap[h][w-1].getType()) currentCitySection.setLeftBorder(true);
        	
        	if(h < (GRID_SIZE-1) &&  cityMap[h][w].getType() != cityMap[h+1][w].getType()) currentCitySection.setBottomBorder(true);
        	if(w < (GRID_SIZE-1) &&  cityMap[h][w].getType() != cityMap[h][w+1].getType()) currentCitySection.setRightBorder(true);
        	
        	button.setBorder(BorderFactory.createMatteBorder(
        			currentCitySection.getTopBorder(),
        			currentCitySection.getLeftBorder(),
        			currentCitySection.getBottomBorder(),
        			currentCitySection.getRightpBorder(),
        			BORDER_COLOR));
        	
        	button.setFocusPainted(false);
            this.add(button);
        }
    }

    private static class CitySquare extends JButton {
        public CitySquare(int i) {
            super(((i/GRID_SIZE)+1) + "," + (i%GRID_SIZE+1) + " ["+cityMap[i/GRID_SIZE][i%GRID_SIZE].getType()+"]");
            this.setOpaque(true);
            this.setBorderPainted(false);
            if ((i / GRID_SIZE + i % GRID_SIZE) % 2 == 1) {
                this.setBackground(Color.gray);
            }
        }
    }
    
    private static class CitySection {
    	private int type;
    	private Color color;
    	//private List<CitySection> neighbors = new ArrayList<>();
    	private boolean[] borders = { false, false, false, false };
		public CitySection(int type, Color color) { this.type = type; this.color = color; }
		public int getType() { return type; }
		public Color getColor() { return color; }
		public int getTopBorder() { return borders[0] ? BORDER_SIZE : 0; }
		public void setTopBorder(boolean b) { borders[0] = b; }
		public int getLeftBorder() { return borders[1] ? BORDER_SIZE : 0; }
		public void setLeftBorder(boolean b) { borders[1] = b; }
		public int getBottomBorder() { return borders[2] ? BORDER_SIZE : 0; }
		public void setBottomBorder(boolean b) { borders[2] = b; }
		public int getRightpBorder() { return borders[3] ? BORDER_SIZE : 0; }
		public void setRightBorder(boolean b) { borders[3] = b; }
		//public void addNeighbors(CitySection s) { neighbors.add(s); }
		//public List<CitySection> getNeighbors() { return neighbors; }
    }
    
    private static void generateCitySections() {
    	Random random = new Random();
    	
    	/*Set<CitySection> freeSections = new HashSet<>(GRID_SIZE*GRID_SIZE);
    	for(int i = 0; i < (GRID_SIZE*GRID_SIZE); i++) freeSections.add(cityMap[i/GRID_SIZE][i%GRID_SIZE]);*/
    	
    	int availableAreas = (GRID_SIZE/2) + random.nextInt(2);
    	    	
    	int size = 0;
    	int type = 0;
    	    	
		for(int i = 0; i < (GRID_SIZE*GRID_SIZE); i++) {
			//CitySection currentSection = cityMap[i/GRID_SIZE][i%GRID_SIZE];
			//if(freeSections.contains(currentSection)) {
			if(cityMap[i/GRID_SIZE][i%GRID_SIZE] == null) {    			
				
				if(size == 0) {
					size = random.nextInt(MAX_SECTION_SQUARES) + 1;
					type = random.nextInt(KIND_OF_TYPES);
	    			if(availableAreas == 0) type = random.nextInt(KIND_OF_TYPES-1) + 1; else availableAreas--;
				} else size--;
				
    			cityMap[i/GRID_SIZE][i%GRID_SIZE] = new CitySection(type, TYPES_COLORS[type]);
				
				//freeSections.remove(currentSection);
			}
		}
    }

    private void display() {
        JFrame f = new JFrame("The Studisme Theory City Generation");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
    	generateCitySections();
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MapGenerator().display();
            }
        });
    }
}