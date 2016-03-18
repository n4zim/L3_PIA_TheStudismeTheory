package fr.thestudismetheory.generator;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

public class CitySquare extends JButton {
    private int type;
    private int areaId = 0;

    private List<CitySquare> neighbors = new ArrayList<>();
    private CitySquare ancestor;

    private boolean[] borders = { false, false, false, false };

	public CitySquare() {
        this.setOpaque(true);
        this.setBorderPainted(false);
    }

    public int getAreaId() { return areaId; }
    public void setAreaId(int a) { areaId = a; }

    public int getType() { return type; }
    public void setType(int t) { type = t; }

    public List<CitySquare> getNeighbors() { return neighbors; }
    public void addNeighbors(CitySquare s) { neighbors.add(s); }

    public CitySquare getAncestor() { return ancestor; }
    public void setAncestor(CitySquare a) { ancestor = a; }

    private final int BORDER_SIZE = 2;

    public int getTopBorder() { return borders[0] ? BORDER_SIZE : 0; }
    public void setTopBorder(boolean b) { borders[0] = b; }
    public int getLeftBorder() { return borders[1] ? BORDER_SIZE : 0; }
    public void setLeftBorder(boolean b) { borders[1] = b; }
    public int getBottomBorder() { return borders[2] ? BORDER_SIZE : 0; }
    public void setBottomBorder(boolean b) { borders[2] = b; }
    public int getRightpBorder() { return borders[3] ? BORDER_SIZE : 0; }
    public void setRightBorder(boolean b) { borders[3] = b; }
}
