package fr.thestudismetheory.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CityGenerator extends JPanel {
	public static final int GRID_SIZE = 8;
	
    private final int SQUARE_SIZE = 80;
    private final Color BORDER_COLOR = Color.BLACK;
    
    private final int KIND_OF_TYPES = 5;
    private final int MAX_SECTION_SQUARES = 6;
    
    private final Color[] TYPES_COLORS = { Color.RED, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.CYAN, Color.GREEN };
    
    private CitySquare[][] cityMap = new CitySquare[GRID_SIZE][GRID_SIZE];

    public CityGenerator() {
        super(new GridLayout(GRID_SIZE, GRID_SIZE));
        this.setPreferredSize(new Dimension(GRID_SIZE * SQUARE_SIZE, GRID_SIZE * SQUARE_SIZE));

        // G?n?re les cases de la grille
        generateCitySquares();
        
        // Ins?re des zones dans les cases
        generateCityAreas();
        
        // Affiche toutes les cases
        for(int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
        	int w = i%GRID_SIZE;
        	int h = i/GRID_SIZE;

            CitySquare currentCitySquare = cityMap[h][w];
            currentCitySquare.setBorderPainted(true);

        	if(h > 0 && cityMap[h][w].getAreaId() != cityMap[h-1][w].getAreaId()) currentCitySquare.setTopBorder(true);
        	if(w > 0 && cityMap[h][w].getAreaId() != cityMap[h][w-1].getAreaId()) currentCitySquare.setLeftBorder(true);

        	if(h < (GRID_SIZE-1) &&  cityMap[h][w].getAreaId() != cityMap[h+1][w].getAreaId()) currentCitySquare.setBottomBorder(true);
        	if(w < (GRID_SIZE-1) &&  cityMap[h][w].getAreaId() != cityMap[h][w+1].getAreaId()) currentCitySquare.setRightBorder(true);

            currentCitySquare.setBorder(BorderFactory.createMatteBorder(
                    currentCitySquare.getTopBorder(),
                    currentCitySquare.getLeftBorder(),
                    currentCitySquare.getBottomBorder(),
                    currentCitySquare.getRightpBorder(),
        			BORDER_COLOR));

            currentCitySquare.setBackground(TYPES_COLORS[currentCitySquare.getType()]);

            currentCitySquare.setFocusPainted(false);

            currentCitySquare.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Actions à faire lors du clic
                }
            });

            this.add(currentCitySquare);
        }
    }

    private void generateCitySquares() {
        // Instantiation de toutes les cases
        for(int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            CitySquare citySquare = new CitySquare();
            if ((i / CityGenerator.GRID_SIZE + i % CityGenerator.GRID_SIZE) % 2 == 1) {
                citySquare.setBackground(Color.gray);
            }
            cityMap[i/GRID_SIZE][i%GRID_SIZE] = citySquare;
        }

        // Ajout des voisins
        for(int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            int w = i%GRID_SIZE;
            int h = i/GRID_SIZE;

            CitySquare currentCitySquare  = cityMap[h][w];
            if(w > 0) currentCitySquare.addNeighbors(cityMap[h][w-1]); // Voisin de gauche
            if(w < (GRID_SIZE-1)) currentCitySquare.addNeighbors(cityMap[h][w+1]); // Voisin de droite
            if(h > 0) currentCitySquare.addNeighbors(cityMap[h-1][w]); // Voisin du haut
            if(h < (GRID_SIZE-1)) currentCitySquare.addNeighbors(cityMap[h+1][w]); // Voisin du bas
        }
    }

    private void generateCityAreas() {
        Random random = new Random();
        // Zones allouables par le joueur
        int availableAreas = (GRID_SIZE/2) + 2;

        // Nombre de cases encore vides
        int remainingSquares = GRID_SIZE*GRID_SIZE;

        // Identifiant, taille et type de la zone actuelle
        int currentAreaId = 0;
        int currentAreaSize = 0;
        int currentAreaType = 0;

        // Case actuelle
        CitySquare currentCitySquare = cityMap[0][0]; // D?part sur la case initiale

        // Tant que toutes les cases ne sont pas parcourues
        while(remainingSquares > 0) {

            // Si le nombre de cases de m?me type n'est pas atteint
            if(currentAreaSize > 0) {
                if(currentCitySquare.getAreaId() == 0)
                    currentAreaSize--;
            } else {
                // Prend un type al?atoire entre 0 et KIND_OF_TYPES
                currentAreaType = random.nextInt(KIND_OF_TYPES) + ((availableAreas > 0) ? 0 : 1);
                if(availableAreas > 0 && currentAreaType == 0) availableAreas--;

                // Si inst. : entre 2 et 4 cases OU si autre : entre 1 et MAX_SECTION_SQUARES+2 cases
                currentAreaSize = random.nextInt(MAX_SECTION_SQUARES-(currentAreaType==0?2:0))+(1+(currentAreaType==0?1:0));

                currentAreaId++;
            }

            // Vérifie l'?tat de la case
            if(currentCitySquare.getAreaId() == 0) {
                currentCitySquare.setAreaId(currentAreaId);
                currentCitySquare.setType(currentAreaType);
                currentCitySquare.setText("T"+currentAreaType+" A"+currentAreaId);
                remainingSquares--;
            } else { // Récupère la prochaine case
                List<CitySquare> neighbors = currentCitySquare.getNeighbors();
                Collections.shuffle(neighbors);

                boolean wasUpdated = false;
                for(CitySquare neighbor : neighbors) {
                    if(neighbor.getAreaId() == 0) {
                        neighbor.setAncestor(currentCitySquare);
                        currentCitySquare = neighbor;
                        wasUpdated = true;
                        break;
                    }
                }
                if(!wasUpdated) {
                    currentCitySquare = currentCitySquare.getAncestor();
                }
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
        CityGenerator cityGenerator = new CityGenerator();
        cityGenerator.display();
    }
}
