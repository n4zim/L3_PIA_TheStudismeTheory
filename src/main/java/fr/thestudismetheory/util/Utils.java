/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.util;

import java.util.Objects;
import java.util.Random;

/**
 * @author vincent
 */
final public class Utils {
    final static private Random RANDOM = new Random();

    private Utils() {
    }

    /**
     * Vérifie si un tableau contient un certain élément
     *
     * @param <T>
     * @param array
     * @param elem
     * @return
     */
    static public <T> boolean arrayContains(T[] array, T elem) {
        for (T e : array) {
            if (Objects.equals(e, elem))
                return true;
        }

        return false;
    }
    
    static public int randInt(int max){
        return RANDOM.nextInt(max);
    }
    
    static public int randInt(int min, int max){
        return RANDOM.nextInt(max - min) + min;
    }
}
