/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.generator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author vincent
 */
public class WorldMapGenerator {
    final static public int MAP_HEIGHT = 50;
    final static public int MAP_WIDTH = 65;
    final static public int PIX_PER_AREA = 10;
    final private boolean[][] map = new boolean[MAP_HEIGHT][MAP_WIDTH];
    final private Random random = new Random();

    final private List<int[]> areas = new ArrayList<>();

    public WorldMapGenerator() {
        addRandomStartPoints();
        enlargeAreas();
    }

    private void addRandomStartPoints() {
        for (int i = 0; i < 15; ++i) {
            int line = random.nextInt(MAP_HEIGHT);
            int col = random.nextInt(MAP_WIDTH);

            map[line][col] = true;

            areas.add(new int[]{line, col});
        }
    }

    private void enlargeAreas() {
        for (int i = 0; i < 3000; ++i) {
            int[] selection = areas.get(random.nextInt(areas.size()));

            int[][] available = new int[][]{
                    new int[]{selection[0] - 1, selection[1]}, //top
                    new int[]{selection[0] + 1, selection[1]}, //bottom
                    new int[]{selection[0], selection[1] - 1}, //left
                    new int[]{selection[0], selection[1] + 1}//right
            };

            int[] newArea = available[random.nextInt(available.length)];

            if (newArea[0] < 0 || newArea[0] >= MAP_HEIGHT
                    || newArea[1] < 0 || newArea[1] >= MAP_WIDTH)
                continue;

            if (map[newArea[0]][newArea[1]])
                continue;

            areas.add(newArea);
            map[newArea[0]][newArea[1]] = true;
        }
    }

    public JPanel showMap() {
        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.green);

                for (int line = 0; line < MAP_HEIGHT; ++line) {
                    for (int col = 0; col < MAP_WIDTH; ++col) {
                        if (map[line][col]) {
                            g.fillRect(col * PIX_PER_AREA, line * PIX_PER_AREA, PIX_PER_AREA, PIX_PER_AREA);
                        }
                    }
                }
            }

        };

        panel.setPreferredSize(new Dimension(MAP_WIDTH * PIX_PER_AREA, MAP_HEIGHT * PIX_PER_AREA));
        panel.setBackground(Color.CYAN);
        return panel;
    }
}
