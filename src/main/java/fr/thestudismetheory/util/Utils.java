/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.util;

import java.util.Objects;

/**
 *
 * @author vincent
 */
final public class Utils {

    private Utils() {}
    
    static public<T> boolean arrayContains(T[] array, T elem){
        for(T e : array){
            if(Objects.equals(e, elem))
                return true;
        }
        
        return false;
    }
    
}
