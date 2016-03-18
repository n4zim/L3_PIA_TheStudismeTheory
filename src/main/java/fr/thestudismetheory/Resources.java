/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

/**
 * Pool de constantes associées à des ressources
 */
final public class Resources {
    final static public Image UNIV_IMG = getImage("img/univ.jpg");

    static public URL getResource(String name) {
        return Resources.class.getClassLoader().getResource(name);
    }

    static public Image getImage(String name) {
        try {
            System.out.println(name);
            return ImageIO.read(getResource(name).openStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
