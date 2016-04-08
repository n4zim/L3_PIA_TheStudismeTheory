package fr.thestudismetheory.generator;

import fr.thestudismetheory.data.generation.CityGenerate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WorldCityGenerator extends JPanel {

    private final Color BORDER_COLOR = Color.BLACK;
    private final int MAX_SECTION_SQUARES = 6;
    private final Color[] TYPES_COLORS = {Color.RED, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.CYAN, Color.GREEN};

    private CityGenerate cityGenerate;

    public WorldCityGenerator(CityGenerate cityGenerate) {
        super(new GridLayout(cityGenerate.getSize(), cityGenerate.getSize()));

        //this.setPreferredSize(new Dimension(GRID_SIZE * SQUARE_SIZE, GRID_SIZE * SQUARE_SIZE));

        this.cityGenerate = cityGenerate;

        // G?n?re les cases de la grille
        if(!cityGenerate.isGenerated()) generateCitySquares();

        // Ins?re des zones dans les cases
        generateCityAreas();

        // Affiche toutes les cases
        for (int i = 0; i < cityGenerate.getSize() * cityGenerate.getSize(); i++) {
            int w = i % cityGenerate.getSize();
            int h = i / cityGenerate.getSize();

            final WorldCitySquare currentWorldCitySquare = cityGenerate.getSquareFromCityMap(h, w);
            currentWorldCitySquare.setBorderPainted(true);

            if (h > 0 && cityGenerate.getSquareFromCityMap(h, w).getAreaId() != cityGenerate.getSquareFromCityMap(h-1, w).getAreaId())
                currentWorldCitySquare.setTopBorder(true);
            if (w > 0 && cityGenerate.getSquareFromCityMap(h, w).getAreaId() != cityGenerate.getSquareFromCityMap(h, w-1).getAreaId())
                currentWorldCitySquare.setLeftBorder(true);

            if (h < (cityGenerate.getSize() - 1) && cityGenerate.getSquareFromCityMap(h, w).getAreaId() != cityGenerate.getSquareFromCityMap(h+1, w).getAreaId())
                currentWorldCitySquare.setBottomBorder(true);
            if (w < (cityGenerate.getSize() - 1) && cityGenerate.getSquareFromCityMap(h, w).getAreaId() != cityGenerate.getSquareFromCityMap(h, w+1).getAreaId())
                currentWorldCitySquare.setRightBorder(true);

            // Si c'est une école
            if (currentWorldCitySquare.getType() == 0) {
                currentWorldCitySquare.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Edition l'école " + currentWorldCitySquare.getAreaId());
                    }
                });
            }

            // Si ne n'est pas une école
            else {
                currentWorldCitySquare.setBackground(TYPES_COLORS[currentWorldCitySquare.getType()]);
                currentWorldCitySquare.setFocusPainted(false);
            }

            /*currentWorldCitySquare.setBorder(BorderFactory.createMatteBorder(
                    currentWorldCitySquare.getTopBorder(),
                    currentWorldCitySquare.getLeftBorder(),
                    currentWorldCitySquare.getBottomBorder(),
                    currentWorldCitySquare.getRightpBorder(),
                    BORDER_COLOR));*/

            // Ajout de l'élément courrant
            this.add(currentWorldCitySquare);
        }
    }

    private void generateCitySquares() {
        // Instantiation de toutes les cases
        for (int i = 0; i < cityGenerate.getSize() * cityGenerate.getSize(); i++) {
            WorldCitySquare worldCitySquare = new WorldCitySquare();
            if ((i / cityGenerate.getSize() + i % cityGenerate.getSize()) % 2 == 1) {
                worldCitySquare.setBackground(Color.gray);
            }

            cityGenerate.setSquareFromCityMap(i/cityGenerate.getSize(), i%cityGenerate.getSize(), worldCitySquare);
        }

        // Ajout des voisins
        for (int i = 0; i < cityGenerate.getSize() * cityGenerate.getSize(); i++) {
            int w = i % cityGenerate.getSize();
            int h = i / cityGenerate.getSize();

            WorldCitySquare currentWorldCitySquare = cityGenerate.getSquareFromCityMap(h, w);
            if (w > 0) currentWorldCitySquare.addNeighbors(cityGenerate.getSquareFromCityMap(h, w-1)); // Voisin de gauche
            if (w < (cityGenerate.getSize() - 1)) currentWorldCitySquare.addNeighbors(cityGenerate.getSquareFromCityMap(h, w+1)); // Voisin de droite
            if (h > 0) currentWorldCitySquare.addNeighbors(cityGenerate.getSquareFromCityMap(h-1, w)); // Voisin du haut
            if (h < (cityGenerate.getSize() - 1)) currentWorldCitySquare.addNeighbors(cityGenerate.getSquareFromCityMap(h+1, w)); // Voisin du bas
        }

        cityGenerate.setAsGenerated();
    }

    private void generateCityAreas() {
        Random random = new Random();
        // Zones allouables par le joueur
        int availableAreas = (cityGenerate.getSize() / 2) + 2;

        // Nombre de cases encore vides
        int remainingSquares = cityGenerate.getSize() * cityGenerate.getSize();

        // Identifiant, taille et type de la zone actuelle
        int currentAreaId = 0;
        int currentAreaSize = 0;
        int currentAreaType = 0;

        // Case actuelle
        WorldCitySquare currentWorldCitySquare = cityGenerate.getSquareFromCityMap(0, 0); // D?part sur la case initiale

        // Tant que toutes les cases ne sont pas parcourues
        while (remainingSquares > 0) {

            // Si le nombre de cases de m?me type n'est pas atteint
            if (currentAreaSize > 0) {
                if (currentWorldCitySquare.getAreaId() == 0)
                    currentAreaSize--;
            } else {
                // Prend un type al?atoire entre 0 et KIND_OF_TYPES
                currentAreaType = random.nextInt(cityGenerate.getKindOfSections()) + ((availableAreas > 0) ? 0 : 1);
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
}
