package fr.thestudismetheory.generator;

import fr.thestudismetheory.Resources;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WorldCitySquare extends JButton {
    private final int BORDER_SIZE = 2;
    private int type;
    private int areaId = 0;
    private List<WorldCitySquare> neighbors = new ArrayList<>();
    private WorldCitySquare ancestor;
    private boolean[] borders = {false, false, false, false};

    public WorldCitySquare() {
        this.setOpaque(true);
        this.setBorderPainted(false);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (type == 0) {
            Image img = Resources.SCHOOL_TILE.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(img));
        }
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int a) {
        areaId = a;
    }

    public int getType() {
        return type;
    }

    public void setType(int t) {
        type = t;
    }

    public List<WorldCitySquare> getNeighbors() {
        return neighbors;
    }

    public void addNeighbors(WorldCitySquare s) {
        neighbors.add(s);
    }

    public WorldCitySquare getAncestor() {
        return ancestor;
    }

    public void setAncestor(WorldCitySquare a) {
        ancestor = a;
    }

    public int getTopBorder() {
        return borders[0] ? BORDER_SIZE : 0;
    }

    public void setTopBorder(boolean b) {
        borders[0] = b;
    }

    public int getLeftBorder() {
        return borders[1] ? BORDER_SIZE : 0;
    }

    public void setLeftBorder(boolean b) {
        borders[1] = b;
    }

    public int getBottomBorder() {
        return borders[2] ? BORDER_SIZE : 0;
    }

    public void setBottomBorder(boolean b) {
        borders[2] = b;
    }

    public int getRightpBorder() {
        return borders[3] ? BORDER_SIZE : 0;
    }

    public void setRightBorder(boolean b) {
        borders[3] = b;
    }
}
