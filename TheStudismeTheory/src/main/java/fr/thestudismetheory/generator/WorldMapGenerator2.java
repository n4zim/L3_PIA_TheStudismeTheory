/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.generator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author vincent
 */
public class WorldMapGenerator2 {
    final static public int MAP_HEIGHT = 50;
    final static public int MAP_WIDTH  = 65;
    final static public int PIX_PER_AREA = 10;
    final static public int MIN_AREA_SIZE = 1;
    final static public int MAX_AREA_SIZE = 4;
    
    final private Random random = new Random();
    
    final private List<Area> areas = new ArrayList<>();
    final private Map<Point, Area> points = new HashMap<>();
    
    static class Area{
        private int size;
        private Point point;
        private int country = -1;
        
        public boolean containsPoints(Point other){
            return other.x >= point.x
                    && other.x < point.x + size
                    && other.y >= point.y 
                    && other.y < point.y + size;
        }
        
        public List<Point> getBorderTop(){
            List<Point> points = new ArrayList<>();
            
            for(int i = 0; i < size; ++i){
                points.add(new Point(point.x + i, point.y));
            }
            
            return points;
        }
        
        public List<Point> getBorderLeft(){
            List<Point> points = new ArrayList<>();
            
            for(int i = 0; i < size; ++i){
                points.add(new Point(point.x, point.y + i));
            }
            
            return points;
        }
        
        public List<Point> getBorderRight(){
            List<Point> points = new ArrayList<>();
            
            for(int i = 0; i < size; ++i){
                points.add(new Point(point.x + size - 1, point.y + i));
            }
            
            return points;
        }
        
        public List<Point> getBorderBottom(){
            List<Point> points = new ArrayList<>();
            
            for(int i = 0; i < size; ++i){
                points.add(new Point(point.x + i, point.y + size - 1));
            }
            
            return points;
        }
        
        public List<Point> getOuterPoints(){
            List<Point> outer = new ArrayList<>();
            
            for(Point p : getBorderTop()){
                outer.add(new Point(p.x, p.y - 1));
            }
            
            for(Point p : getBorderBottom()){
                outer.add(new Point(p.x, p.y + 1));
            }
            
            for(Point p : getBorderRight()){
                outer.add(new Point(p.x + 1, p.y));
            }
            
            for(Point p : getBorderLeft()){
                outer.add(new Point(p.x - 1, p.y));
            }
            
            return outer;
        }
    }

    public WorldMapGenerator2() {
        addRandomStartPoints();
        enlargeAreas();
        setCountries();
    }
    
    private void addRandomStartPoints(){
        for(int i = 0; i < 15; ++i){
            int line = random.nextInt(MAP_HEIGHT);
            int col  = random.nextInt(MAP_WIDTH);
            int size = MIN_AREA_SIZE + random.nextInt(MAX_AREA_SIZE - MIN_AREA_SIZE);
            
            Area area = new Area();
            area.point = new Point(col, line);
            area.size = size;
            
            addArea(area);
        }
    }
    
    private void addArea(Area area){
        for(int i = 0; i < area.size; ++i){
            points.put(new Point(area.point.x + i, area.point.y + i), area);
        }
        
        areas.add(area);
    }
    
    private void enlargeAreas(){
        for(int i = 0; i < 1500; ++i){
            Area selection = areas.get(random.nextInt(areas.size()));
            
            int size = MIN_AREA_SIZE + random.nextInt(MAX_AREA_SIZE - MIN_AREA_SIZE);
            
            Point[] available = new Point[]{
                new Point(selection.point.x, selection.point.y - size), //top
                new Point(selection.point.x, selection.point.y + selection.size), //bottom
                new Point(selection.point.x - size, selection.point.y), //left
                new Point(selection.point.x + selection.size, selection.point.y)//right
            };
            
            Point newPoint = available[random.nextInt(available.length)];
            
            if(points.containsKey(newPoint))
                continue;
            
            Area area = new Area();
            area.point = newPoint;
            area.size = size;
            
            addArea(area);
        }
    }
    
    private int makeCountry(Area area, int id, int curSize){
        if(area.country != -1)
            return curSize;
        
        if(curSize > 20)
            return curSize;
        
        curSize += area.size;
        area.country = id;
        
        List<Point> outer = area.getOuterPoints();
        Collections.shuffle(outer);
            
        for(Point point : outer){
            if(curSize > 20)
                break;

            Area neightbor = points.get(point);
            
            if(neightbor == null)
                continue;

            curSize = makeCountry(neightbor, id, curSize);
        }
        
        return curSize;
    }
    
    private void setCountries(){
        int curCountry = 0;
        
        for(Area area : areas){
            if(area.country != -1)
                continue;
            
            makeCountry(area, curCountry, 0);
            ++curCountry;
        }
    }
    
    public JPanel showMap(){
        JPanel panel = new JPanel(){

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Color[] colors = new Color[]{
                    Color.BLUE, Color.DARK_GRAY, Color.GRAY,
                    Color.MAGENTA, Color.PINK, Color.ORANGE, Color.YELLOW,
                    Color.WHITE, Color.GREEN
                };
                
                for(Area area : areas){
                    g.setColor(colors[area.country % colors.length]);
                    g.fillRect(area.point.x * PIX_PER_AREA, area.point.y * PIX_PER_AREA, area.size * PIX_PER_AREA, area.size * PIX_PER_AREA);
                    g.setColor(Color.BLACK);
                    g.drawRect(area.point.x * PIX_PER_AREA, area.point.y * PIX_PER_AREA, area.size * PIX_PER_AREA, area.size * PIX_PER_AREA);
                }
            }
            
        };
        
        panel.setPreferredSize(new Dimension(MAP_WIDTH * PIX_PER_AREA, MAP_HEIGHT * PIX_PER_AREA));
        panel.setBackground(Color.CYAN);
        return panel;
    }
}
