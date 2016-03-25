package fr.thestudismetheory.generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WorldCityGenerator extends JPanel {
    public static final int GRID_SIZE = 4;

    private final Color BORDER_COLOR = Color.BLACK;

    private final int KIND_OF_TYPES = 5;
    private final int MAX_SECTION_SQUARES = 6;

    private final Color[] TYPES_COLORS = {Color.RED, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.CYAN, Color.GREEN};

    private WorldCitySquare[][] cityMap = new WorldCitySquare[GRID_SIZE][GRID_SIZE];

    public WorldCityGenerator() {
        super(new GridLayout(7, 7));
        //this.setPreferredSize(new Dimension(GRID_SIZE * SQUARE_SIZE, GRID_SIZE * SQUARE_SIZE));

        // G?n?re les cases de la grille
        generateCitySquares();

        // Ins?re des zones dans les cases
        generateCityAreas();

        // Affiche toutes les cases
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            int w = i % GRID_SIZE;
            int h = i / GRID_SIZE;

            final WorldCitySquare currentWorldCitySquare = cityMap[h][w];
            currentWorldCitySquare.setBorderPainted(true);

            if (h > 0 && cityMap[h][w].getAreaId() != cityMap[h - 1][w].getAreaId())
                currentWorldCitySquare.setTopBorder(true);
            if (w > 0 && cityMap[h][w].getAreaId() != cityMap[h][w - 1].getAreaId())
                currentWorldCitySquare.setLeftBorder(true);

            if (h < (GRID_SIZE - 1) && cityMap[h][w].getAreaId() != cityMap[h + 1][w].getAreaId())
                currentWorldCitySquare.setBottomBorder(true);
            if (w < (GRID_SIZE - 1) && cityMap[h][w].getAreaId() != cityMap[h][w + 1].getAreaId())
                currentWorldCitySquare.setRightBorder(true);

            /*currentWorldCitySquare.setBorder(BorderFactory.createMatteBorder(
                    currentWorldCitySquare.getTopBorder(),
                    currentWorldCitySquare.getLeftBorder(),
                    currentWorldCitySquare.getBottomBorder(),
                    currentWorldCitySquare.getRightpBorder(),
                    BORDER_COLOR));*/

            currentWorldCitySquare.setBackground(TYPES_COLORS[currentWorldCitySquare.getType()]);

            currentWorldCitySquare.setFocusPainted(false);

            if(currentWorldCitySquare.getType() == 0) {
                currentWorldCitySquare.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Edition l'école "+currentWorldCitySquare.getAreaId());
                    }
                });
            } else {
                //currentWorldCitySquare.setFocusPainted(false);
                //Resources.UNIV_IMG.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            }

            // Ajout des séparateurs horizontaux
            if(w == 0 && h > 0) {
                for(int j = 0; j < 7; j++) {
                    JButton horizontalButton = new JButton();
                    horizontalButton.setText("H"+h + ":" + w);
                    this.add(horizontalButton);
                }

            } else if(w != 0) {
                JButton verticalButton = new JButton();
                verticalButton.setText("V"+h + ":" + w);
                this.add(verticalButton);
            }

            // Ajout de l'élément courrant
            this.add(currentWorldCitySquare);
        }
    }

    public static void main(String[] args) {
        WorldCityGenerator worldCityGenerator = new WorldCityGenerator();
        worldCityGenerator.display();
    }

    private void generateCitySquares() {
        // Instantiation de toutes les cases
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            WorldCitySquare worldCitySquare = new WorldCitySquare();
            if ((i / WorldCityGenerator.GRID_SIZE + i % WorldCityGenerator.GRID_SIZE) % 2 == 1) {
                worldCitySquare.setBackground(Color.gray);
            }
            cityMap[i / GRID_SIZE][i % GRID_SIZE] = worldCitySquare;
        }

        // Ajout des voisins
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            int w = i % GRID_SIZE;
            int h = i / GRID_SIZE;

            WorldCitySquare currentWorldCitySquare = cityMap[h][w];
            if (w > 0) currentWorldCitySquare.addNeighbors(cityMap[h][w - 1]); // Voisin de gauche
            if (w < (GRID_SIZE - 1)) currentWorldCitySquare.addNeighbors(cityMap[h][w + 1]); // Voisin de droite
            if (h > 0) currentWorldCitySquare.addNeighbors(cityMap[h - 1][w]); // Voisin du haut
            if (h < (GRID_SIZE - 1)) currentWorldCitySquare.addNeighbors(cityMap[h + 1][w]); // Voisin du bas
        }
    }

    private void generateCityAreas() {
        Random random = new Random();
        // Zones allouables par le joueur
        int availableAreas = (GRID_SIZE / 2) + 2;

        // Nombre de cases encore vides
        int remainingSquares = GRID_SIZE * GRID_SIZE;

        // Identifiant, taille et type de la zone actuelle
        int currentAreaId = 0;
        int currentAreaSize = 0;
        int currentAreaType = 0;

        // Case actuelle
        WorldCitySquare currentWorldCitySquare = cityMap[0][0]; // D?part sur la case initiale

        // Tant que toutes les cases ne sont pas parcourues
        while (remainingSquares > 0) {

            // Si le nombre de cases de m?me type n'est pas atteint
            if (currentAreaSize > 0) {
                if (currentWorldCitySquare.getAreaId() == 0)
                    currentAreaSize--;
            } else {
                // Prend un type al?atoire entre 0 et KIND_OF_TYPES
                currentAreaType = random.nextInt(KIND_OF_TYPES) + ((availableAreas > 0) ? 0 : 1);
                if (availableAreas > 0 && currentAreaType == 0) availableAreas--;

                // Si inst. : entre 2 et 4 cases OU si autre : entre 1 et MAX_SECTION_SQUARES+2 cases
                currentAreaSize = random.nextInt(MAX_SECTION_SQUARES - (currentAreaType == 0 ? 2 : 0)) + (1 + (currentAreaType == 0 ? 1 : 0));

                currentAreaId++;
            }

            // Vérifie l'?tat de la case
            if (currentWorldCitySquare.getAreaId() == 0) {
                currentWorldCitySquare.setAreaId(currentAreaId);
                currentWorldCitySquare.setType(currentAreaType);
                currentWorldCitySquare.setText("T" + currentAreaType + " A" + currentAreaId);
                remainingSquares--;
            } else { // Récupère la prochaine case
                List<WorldCitySquare> neighbors = currentWorldCitySquare.getNeighbors();
                Collections.shuffle(neighbors);

                boolean wasUpdated = false;
                for (WorldCitySquare neighbor : neighbors) {
                    if (neighbor.getAreaId() == 0) {
                        neighbor.setAncestor(currentWorldCitySquare);
                        currentWorldCitySquare = neighbor;
                        wasUpdated = true;
                        break;
                    }
                }
                if (!wasUpdated) {
                    currentWorldCitySquare = currentWorldCitySquare.getAncestor();
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
}
