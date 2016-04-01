/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.URL;

/**
 * Pool de constantes associées à des ressources
 */
final public class Resources {
    final static public Image UNIV_IMG = getImage("img/univ.jpg");
    final static public Image SCHOOL_TILE = getImage("img/school.jpg");

    final static public String[] STUDENT_NAMES = getStudentNames("txt/prenoms.txt");

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

    static public String[] getStudentNames(String name) {
        StringBuilder sb = new StringBuilder();
        System.out.println(name);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(getResource(name).openStream()))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch(FileNotFoundException ex) {
            System.out.println("Impossible d'ouvrir le fichier des prénoms");
        } catch(IOException e) {
            System.out.println("Erreur de lecture du fichier des prénoms");
            e.printStackTrace();
        }
        return sb.toString().split(System.getProperty("line.separator"));
    }
}
