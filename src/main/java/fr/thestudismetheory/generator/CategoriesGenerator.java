/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.generator;

import fr.thestudismetheory.data.Category;
import fr.thestudismetheory.util.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincent
 */
public class CategoriesGenerator {
    static public List<Category> generateDefaultCategories(){
        List<Category> categories = new ArrayList<>();
        
        categories.add(new Category(-1, "Informatique", Utils.randInt(15, 25)));
        categories.add(new Category(-1, "Mathématiques", Utils.randInt(5, 10)));
        categories.add(new Category(-1, "Mécanique", Utils.randInt(10, 15)));
        categories.add(new Category(-1, "Droit", Utils.randInt(15, 25)));
        categories.add(new Category(-1, "STAPS", Utils.randInt(25, 30)));
        categories.add(new Category(-1, "Psycho", Utils.randInt(30, 45)));
        
        return categories;
    }
}
